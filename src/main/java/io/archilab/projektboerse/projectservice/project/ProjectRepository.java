package io.archilab.projektboerse.projectservice.project;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends PagingAndSortingRepository<Project, UUID> {

  Set<Project> findByStatus(ProjectStatus status);
  List<Project> findByCreator(@Param(value="uuid") UUID uuid);
}
