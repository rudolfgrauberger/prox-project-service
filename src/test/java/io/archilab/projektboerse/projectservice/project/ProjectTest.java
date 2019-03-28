package io.archilab.projektboerse.projectservice.project;

import io.archilab.projektboerse.projectservice.module.Module;
import io.archilab.projektboerse.projectservice.module.ModuleName;
import io.archilab.projektboerse.projectservice.module.ModuleRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectTest {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void equality() {
        // Create Modules for Project
        List<Module> modules = new ArrayList<>();
        modules.add(new Module(new ModuleName("Module 1")));
        modules.add(new Module(new ModuleName("Module 2")));
        modules.add(new Module(new ModuleName("Module 3")));
        moduleRepository.saveAll(modules);
        assertThat(moduleRepository.count()).isEqualTo(3);

        // Create Project
        Project project = new Project(new ProjectName("Testprojekt"),
                new ProjectDescription("Bestes Projekt"),
                ProjectStatus.LAUFEND,
                new CreatorID(UUID.randomUUID()),
                new CreatorName("Jann"),
                new SupervisorName("Jann"),
                modules);

        projectRepository.save(project);
        assertThat(projectRepository.findById(project.getId()).isPresent()).isEqualTo(true);
    }
}