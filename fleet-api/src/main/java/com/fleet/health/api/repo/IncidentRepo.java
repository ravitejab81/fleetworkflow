package com.fleet.health.api.repo;

import com.fleet.health.api.domain.Incident;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface IncidentRepo extends JpaRepository<Incident, UUID> {

    @Query("select i from Incident i where i.status = 'OPEN' order by i.openedAt desc")
    Page<Incident> pageOpen(Pageable pageable);

    Page<Incident> findByVinOrderByOpenedAtDesc(String vin, Pageable pageable);
}
