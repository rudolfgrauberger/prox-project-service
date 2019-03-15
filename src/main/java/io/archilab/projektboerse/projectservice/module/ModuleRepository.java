package io.archilab.projektboerse.projectservice.module;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@CrossOrigin // TODO only for development purposes
@RepositoryRestResource(collectionResourceRel = "projectModules", path = "projectModules")
public interface ModuleRepository extends PagingAndSortingRepository<Module, UUID> {

    Optional<Module> findByExternalModuleID(ExternalModuleID externalModuleID);
}

