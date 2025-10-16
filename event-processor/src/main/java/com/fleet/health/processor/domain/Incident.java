package com.fleet.health.processor.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "incident")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Incident {
    @Id private UUID incidentId;
    private String vin;
    private String kind;       // OVERHEAT, LOW_FUEL, etc.
    private String severity;   // INFO/WARN/CRITICAL
    private String status;     // OPEN/ACK/CLOSED
    private Instant openedAt;
    private Instant closedAt;


}
