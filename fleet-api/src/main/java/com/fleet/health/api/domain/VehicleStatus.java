// src/main/java/com/fleet/health/api/domain/VehicleStatus.java
package com.fleet.health.api.domain;

import jakarta.persistence.*;

@Entity @Table(name="vehicle_status")
public class VehicleStatus {
    @EmbeddedId
    private VehicleStatusId id;

    @Column(name="odo_km")         private Long odoKm;
    @Column(name="coolant_temp_c") private Integer coolantTempC;
    @Column(name="fuel_pct")       private Integer fuelPct;
    private Double lat;
    private Double lon;

    // === Add these two transient getters ===
    @Transient
    public String getVin() { return id != null ? id.getVin() : null; }

    @Transient
    public String getTs()  { return (id != null && id.getTs()!=null) ? id.getTs().toString() : null; }

    // existing getters/setters...
    public VehicleStatusId getId(){ return id; }
    public void setId(VehicleStatusId id){ this.id=id; }
    public Long getOdoKm(){ return odoKm; }
    public void setOdoKm(Long v){ this.odoKm=v; }
    public Integer getCoolantTempC(){ return coolantTempC; }
    public void setCoolantTempC(Integer v){ this.coolantTempC=v; }
    public Integer getFuelPct(){ return fuelPct; }
    public void setFuelPct(Integer v){ this.fuelPct=v; }
    public Double getLat(){ return lat; }
    public void setLat(Double v){ this.lat=v; }
    public Double getLon(){ return lon; }
    public void setLon(Double v){ this.lon=v; }
}
