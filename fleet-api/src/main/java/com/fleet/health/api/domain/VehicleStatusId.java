package com.fleet.health.api.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Embeddable
public class VehicleStatusId implements Serializable {
    private String vin;
    private Instant ts;

    public VehicleStatusId() {}
    public VehicleStatusId(String vin, Instant ts) { this.vin = vin; this.ts = ts; }

    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }
    public Instant getTs() { return ts; }
    public void setTs(Instant ts) { this.ts = ts; }

    @Override public boolean equals(Object o){ if(this==o)return true; if(!(o instanceof VehicleStatusId v))return false; return Objects.equals(vin,v.vin)&&Objects.equals(ts,v.ts); }
    @Override public int hashCode(){ return Objects.hash(vin,ts); }
}
