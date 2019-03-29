package io.archilab.projektboerse.projectservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.archilab.projektboerse.projectservice.module.AcademicDegree;
import io.archilab.projektboerse.projectservice.module.ModuleName;
import io.archilab.projektboerse.projectservice.module.ModuleRepository;
import io.archilab.projektboerse.projectservice.module.StudyCourse;
import io.archilab.projektboerse.projectservice.module.StudyCourseName;
import io.archilab.projektboerse.projectservice.module.StudyCourseRepository;
import io.archilab.projektboerse.projectservice.project.CreatorID;
import io.archilab.projektboerse.projectservice.project.CreatorName;
import io.archilab.projektboerse.projectservice.project.Project;
import io.archilab.projektboerse.projectservice.project.ProjectDescription;
import io.archilab.projektboerse.projectservice.project.ProjectName;
import io.archilab.projektboerse.projectservice.project.ProjectRepository;
import io.archilab.projektboerse.projectservice.project.ProjectStatus;
import io.archilab.projektboerse.projectservice.project.SupervisorName;
import io.archilab.projektboerse.projectservice.module.Module;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DatabaseTest {

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  ModuleRepository moduleRepository;
  
  @Autowired
  StudyCourseRepository studyCourseRepository;

  @Test
  public void creation() {
    StudyCourse computerScience = new StudyCourse(new StudyCourseName("Computer Science"),
        AcademicDegree.MASTER);
    StudyCourse softwareEngineering = new StudyCourse(new StudyCourseName("Software Engineering"),
        AcademicDegree.MASTER);
    StudyCourse informationSystems = new StudyCourse(new StudyCourseName("Information Systems"),
        AcademicDegree.MASTER);
   
    Module am = new Module(new ModuleName("Anforderungsmanagement"));
    Module fae = new Module(new ModuleName("Fachspezifischer Architekturentwurf"));
    Module bi = new Module(new ModuleName("Business Intelligence"));
    Module eam = new Module(new ModuleName("Enterprise Architecture Management"));

    computerScience.addStudyDirection(softwareEngineering);
    computerScience.addStudyDirection(informationSystems);

    softwareEngineering.addModule(am);
    softwareEngineering.addModule(fae);

    informationSystems.addModule(bi);
    informationSystems.addModule(eam);

    this.studyCourseRepository.save(computerScience);

    assertThat(this.studyCourseRepository.findAll())
        .contains(computerScience, softwareEngineering, informationSystems);
    assertThat(this.moduleRepository.findAll()).contains(am, fae, bi, eam);
    assertThat(
        this.studyCourseRepository.findById(computerScience.getId()).get().getStudyDirections())
        .contains(softwareEngineering, informationSystems);
    assertThat(this.studyCourseRepository.findById(softwareEngineering.getId()).get()
        .getParentStudyCourse()).isEqualTo(computerScience);
    assertThat(this.studyCourseRepository.findById(informationSystems.getId()).get()
        .getParentStudyCourse()).isEqualTo(computerScience);
    
    
    ArrayList<Module> modules = new ArrayList<Module>();
    modules.add(am);
    Project p1 = new Project(new ProjectName("Projekt 1"), new ProjectDescription("Ein neues Projekt 1"), ProjectStatus.VERFÜGBAR, new CreatorID(UUID.randomUUID()), new CreatorName("Creator 1"), new SupervisorName("Supervisor Professor 1"), modules);
    
    modules = new ArrayList<Module>();
    modules.add(fae);
    Project p2 =  new Project(new ProjectName("Projekt 1"), new ProjectDescription("Ein neues Projekt 2"), ProjectStatus.VERFÜGBAR, new CreatorID(UUID.randomUUID()), new CreatorName("Creator 3"), new SupervisorName("Supervisor Professor 3"), modules);   
    
    modules = new ArrayList<Module>();
    modules.add(eam);
    Project p3 =  new Project(new ProjectName("Projekt 1"), new ProjectDescription("Ein neues Projekt 3"), ProjectStatus.VERFÜGBAR, new CreatorID(UUID.randomUUID()), new CreatorName("Creator 4"), new SupervisorName("Supervisor Professor 4"), modules);
    

    projectRepository.save(p1);
    projectRepository.save(p2);
    projectRepository.save(p3);
    
   // projectRepository.findAll().
    
    
  }

}
