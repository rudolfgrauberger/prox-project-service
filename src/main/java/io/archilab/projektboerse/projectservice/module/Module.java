package io.archilab.projektboerse.projectservice.module;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import io.archilab.projektboerse.projectservice.module.StudyCourse;
import io.archilab.projektboerse.projectservice.core.AbstractEntity;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Module extends AbstractEntity {

  @JsonIgnore
  private ExternalModuleID externalModuleID;

  @JsonUnwrapped
  private ModuleName name;


  public Module(ModuleName name) {
    this.name = name;
  }
}