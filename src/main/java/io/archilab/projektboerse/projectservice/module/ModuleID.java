package io.archilab.projektboerse.projectservice.module;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ModuleID implements Serializable {

    private String selfRef;


    public ModuleID(String selfRef) {
        this.selfRef = selfRef;
    }
}
