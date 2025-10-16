package com.fleet.health.api.repo;


import com.fleet.health.api.domain.VehicleStatus;
import com.fleet.health.api.domain.VehicleStatusId;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VehicleStatusRepo extends JpaRepository<VehicleStatus, VehicleStatusId> {

    @Query("""
    select vs from VehicleStatus vs
    where vs.id.vin = :vin
    order by vs.id.ts desc
  """)
    Page<VehicleStatus> pageByVin(String vin, Pageable pageable);

    @Query("""
    select vs from VehicleStatus vs
    where vs.id.vin = :vin
    order by vs.id.ts desc
  """)
    default Optional<VehicleStatus> latest(String vin){
        var page = pageByVin(vin, PageRequest.of(0,1));
        return page.isEmpty() ? Optional.empty() : Optional.of(page.getContent().get(0));
    }
}

