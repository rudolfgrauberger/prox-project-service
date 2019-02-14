package io.archilab.projektboerse.projectservice.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Data
@Setter(AccessLevel.NONE)
public class AbstractEntity {

    @Id
    @JsonIgnore
    private UUID id;

    protected AbstractEntity() {
        this.id = UUID.randomUUID();
    }
}
