package com.fleet.health.processor.kafka;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.health.processor.domain.*;
import com.fleet.health.processor.model.TelemetryPayload;
import com.fleet.health.processor.repo.IncidentRepo;
import com.fleet.health.processor.repo.VehicleStatusRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class TelemetryListener {

    private final VehicleStatusRepo statusRepo;
    private final IncidentRepo incidentRepo;
    private final ObjectMapper om = new ObjectMapper();

    @Value("${app.rules.overheat-threshold:110}")
    private int overheatC;

    @KafkaListener(topics = "${app.topic.telemetry}")
    public void onMessage(ConsumerRecord<String, String> rec, Acknowledgment ack) {
        try {
            TelemetryPayload p = om.readValue(rec.value(), TelemetryPayload.class);

            // 1) idempotent upsert (PK = (vin, ts))
            Instant ts = Instant.parse(p.getTs());
            VehicleStatusKey key = new VehicleStatusKey(p.getVin(), ts);
            if (!statusRepo.existsById(key)) {
                VehicleStatus status = VehicleStatus.builder()
                        .vin(p.getVin()).ts(ts)
                        .odoKm(p.getOdo_km())
                        .coolantTempC(p.getCoolant_temp_c())
                        .fuelPct(p.getFuel_pct())
                        .lat(p.getGps() != null ? p.getGps().getLat() : null)
                        .lon(p.getGps() != null ? p.getGps().getLon() : null)
                        .build();
                statusRepo.save(status);
            }

            // 2) rule: open incident on overheat
            if (p.getCoolant_temp_c() != null && p.getCoolant_temp_c() >= overheatC) {
                Incident inc = Incident.builder()
                        .incidentId(UUID.randomUUID())
                        .vin(p.getVin())
                        .kind("OVERHEAT")
                        .severity("CRITICAL")
                        .status("OPEN")
                        .openedAt(ts)
                        .build();
                incidentRepo.save(inc);
            }

            ack.acknowledge(); // commit only after success
        } catch (Exception e) {
            log.error("Failed to process record offset={} partition={} key={} err={}",
                    rec.offset(), rec.partition(), rec.key(), e.toString(), e);
            // no ack -> will be retried; you can add DLQ logic here if desired
        }
    }
}

