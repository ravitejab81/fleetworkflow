package com.fleet.health.processor.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryPayload {
    private String vin;
    private String ts;             // ISO-8601
    private long odo_km;
    private Integer coolant_temp_c;
    private Integer fuel_pct;
    private Gps gps;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public long getOdo_km() {
        return odo_km;
    }

    public void setOdo_km(long odo_km) {
        this.odo_km = odo_km;
    }

    public Integer getCoolant_temp_c() {
        return coolant_temp_c;
    }

    public void setCoolant_temp_c(Integer coolant_temp_c) {
        this.coolant_temp_c = coolant_temp_c;
    }

    public Integer getFuel_pct() {
        return fuel_pct;
    }

    public void setFuel_pct(Integer fuel_pct) {
        this.fuel_pct = fuel_pct;
    }

    public Gps getGps() {
        return gps;
    }

    public void setGps(Gps gps) {
        this.gps = gps;
    }

    @Data
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Gps {
        private Double lat;
        private Double lon;

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }
    }
}

