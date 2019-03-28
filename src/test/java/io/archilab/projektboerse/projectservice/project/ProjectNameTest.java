package io.archilab.projektboerse.projectservice.project;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ProjectNameTest {
    @Test
    public void equality() {
        String name = "name";
        ProjectName projectName1 = new ProjectName(name);
        ProjectName projectName2 = new ProjectName(name);
        assertThat(projectName1).isEqualTo(projectName2);
        assertThat(projectName1.hashCode()).isEqualTo(projectName2.hashCode());
    }
}