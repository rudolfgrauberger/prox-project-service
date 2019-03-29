package io.archilab.projektboerse.projectservice.project;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class ProjectDescriptionTest {

    @Test
    public void equality() {
        String description = "Lorem ipsum";
        ProjectDescription projectDescription1 = new ProjectDescription(description);
        ProjectDescription projectDescription2 = new ProjectDescription(description);
        assertThat(projectDescription1).isEqualTo(projectDescription2);
        assertThat(projectDescription1.hashCode()).isEqualTo(projectDescription2.hashCode());
    }
}