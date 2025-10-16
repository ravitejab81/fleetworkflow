package com.fleet.health.api.domain;


import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity @Table(name="incident")
public class Incident {
    @Id private UUID incidentId;
    private String vin;
    private String kind;
    private String severity;
    private String status;
    private Instant openedAt;
    private Instant closedAt;

    public UUID getIncidentId(){ return incidentId; }
    public void setIncidentId(UUID id){ this.incidentId=id; }
    public String getVin(){ return vin; }
    public void setVin(String v){ this.vin=v; }
    public String getKind(){ return kind; }
    public void setKind(String k){ this.kind=k; }
    public String getSeverity(){ return severity; }
    public void setSeverity(String s){ this.severity=s; }
    public String getStatus(){ return status; }
    public void setStatus(String s){ this.status=s; }
    public Instant getOpenedAt(){ return openedAt; }
    public void setOpenedAt(Instant t){ this.openedAt=t; }
    public Instant getClosedAt(){ return closedAt; }
    public void setClosedAt(Instant t){ this.closedAt=t; }
}
