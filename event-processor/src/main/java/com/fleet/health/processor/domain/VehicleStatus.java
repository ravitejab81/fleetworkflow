package com.fleet.health.processor.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

// VehicleStatus.java
@Entity @Table(name = "vehicle_status")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@IdClass(VehicleStatusKey.class)
public class VehicleStatus {
    @Id private String vin;
    @Id private Instant ts;

    @Column(name = "odo_km")           private Long odoKm;
    @Column(name = "coolant_temp_c")   private Integer coolantTempC; // <-- important
    @Column(name = "fuel_pct")         private Integer fuelPct;
    @Column(name = "lat")              private Double lat;
    @Column(name = "lon")              private Double lon;
}

