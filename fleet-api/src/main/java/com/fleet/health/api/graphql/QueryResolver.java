package com.fleet.health.api.graphql;



import com.fleet.health.api.domain.Incident;
import com.fleet.health.api.domain.VehicleStatus;
import com.fleet.health.api.repo.IncidentRepo;
import com.fleet.health.api.repo.VehicleStatusRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QueryResolver {

    private final VehicleStatusRepo statusRepo;
    private final IncidentRepo incidentRepo;

    @QueryMapping
    public VehicleStatus latestStatus(@Argument String vin) {
        return statusRepo.latest(vin).orElse(null);
    }

    @QueryMapping
    public List<VehicleStatus> statusHistory(@Argument String vin, @Argument int page, @Argument int size) {
        return statusRepo.pageByVin(vin, PageRequest.of(page, size)).getContent();
    }

    @QueryMapping
    public List<Incident> openIncidents(@Argument int page, @Argument int size) {
        return incidentRepo.pageOpen(PageRequest.of(page, size)).getContent();
    }

    @QueryMapping
    public List<Incident> incidentsByVin(@Argument String vin, @Argument int page, @Argument int size) {
        return incidentRepo.findByVinOrderByOpenedAtDesc(vin, PageRequest.of(page, size)).getContent();
    }
}

