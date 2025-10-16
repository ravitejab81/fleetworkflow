package com.fleet.health.api.graphql;

import com.fleet.health.api.repo.IncidentRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.AnyKeyJavaClass;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller

public class MutationResolver {
    private final IncidentRepo incidentRepo;
MutationResolver(IncidentRepo repo){
    this.incidentRepo = repo;
}
    @MutationMapping
    public boolean closeIncident(@Argument String id) {
        return incidentRepo.findById(UUID.fromString(id)).map(i -> {
            i.setStatus("CLOSED");
            i.setClosedAt(java.time.Instant.now());
            incidentRepo.save(i);
            return true;
        }).orElse(false);
    }
}
