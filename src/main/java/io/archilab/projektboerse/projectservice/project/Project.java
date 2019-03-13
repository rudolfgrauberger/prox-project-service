package io.archilab.projektboerse.projectservice.project;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import io.archilab.projektboerse.projectservice.core.AbstractEntity;

import io.archilab.projektboerse.projectservice.module.Module;

import javax.persistence.CascadeType;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

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

  @ManyToMany
  private List<Module> modules = new ArrayList<>();


  public Project(ProjectName name, ProjectDescription description, ProjectStatus status) {
    this.name = name;
    this.description = description;
    this.status = status;
  }
}
