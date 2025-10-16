package com.fleet.health.telemetry.model;


import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TelemetryPayload {

    @NotBlank
    @Size(min = 11, max = 17, message = "VIN must be 11-17 characters")
    private String vin;

    @NotBlank(message = "ts (ISO-8601) is required")
    private String ts;  // keep as string; producer just forwards to Kafka

    @PositiveOrZero
    private long odo_km;

    private Integer coolant_temp_c;
    private Integer fuel_pct;

    private Gps gps;

    @Data @NoArgsConstructor @AllArgsConstructor
    public static class Gps {
        private Double lat;
        private Double lon;
    }
}
