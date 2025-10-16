package com.fleet.health.processor.repo;

import com.fleet.health.processor.domain.VehicleStatus;
import com.fleet.health.processor.domain.VehicleStatusKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleStatusRepo extends JpaRepository<VehicleStatus, VehicleStatusKey> {}
