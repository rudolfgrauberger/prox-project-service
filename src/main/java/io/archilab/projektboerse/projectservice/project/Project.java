package io.archilab.projektboerse.projectservice.project;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.archilab.projektboerse.projectservice.core.AbstractEntity;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Project extends AbstractEntity {

    @Setter
    @JsonUnwrapped
    private ProjectName name;

    @Setter
    @JsonUnwrapped
    private ProjectDescription description;

    @Setter
    private ProjectStatus status;

    public Project(ProjectName name, ProjectDescription description, ProjectStatus status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }
}
