package io.archilab.projektboerse.projectservice.project;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreatorID {

  private UUID creatorID;

  public CreatorID(UUID creatorID) {
    this.creatorID = creatorID;
  }
}
