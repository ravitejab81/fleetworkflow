package com.fleet.health.processor.repo;

import com.fleet.health.processor.domain.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IncidentRepo extends JpaRepository<Incident, UUID> {}
