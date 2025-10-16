package com.fleet.health.telemetry.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleet.health.telemetry.model.TelemetryPayload;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/telemetry")
@RequiredArgsConstructor
public class TelemetryController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper om = new ObjectMapper();

    @Value("${app.topic.telemetry}")
    private String telemetryTopic;

    @PostMapping
    public ResponseEntity<Void> publish(@Valid @RequestBody TelemetryPayload payload) throws Exception {
        String key = payload.getVin();
        String value = om.writeValueAsString(payload);
        // fire-and-forget is ok; for demo you can .get() with timeout to ensure delivery
        kafkaTemplate.send(telemetryTopic, key, value);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/ping")
    public String ping() { return "ok"; }
}

