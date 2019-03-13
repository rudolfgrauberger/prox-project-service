package io.archilab.projektboerse.projectservice.module;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends PagingAndSortingRepository<Module, UUID> {
    Optional<Module> findByExternalModuleID(ExternalModuleID externalModuleID);
}
