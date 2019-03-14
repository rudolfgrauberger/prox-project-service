package io.archilab.projektboerse.projectservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.archilab.projektboerse.projectservice.project.Project;
import io.archilab.projektboerse.projectservice.project.ProjectDescription;
import io.archilab.projektboerse.projectservice.project.ProjectName;
import io.archilab.projektboerse.projectservice.project.ProjectRepository;
import io.archilab.projektboerse.projectservice.project.ProjectStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceApplicationTests 
{
	
	
	@Autowired ProjectRepository repo;
	
	  @Test
	  public void contextLoads() {
	  }
}

