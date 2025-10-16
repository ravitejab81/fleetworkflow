package com.fleet.health.processor.domain;

import lombok.*;
import java.io.Serializable;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class VehicleStatusKey implements Serializable {
    private String vin;
    private Instant ts;
}
