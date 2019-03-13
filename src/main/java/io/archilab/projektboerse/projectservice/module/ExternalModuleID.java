package io.archilab.projektboerse.projectservice.module;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExternalModuleID implements Serializable {

    private String selfRef;


    public ExternalModuleID(String selfRef) {
        this.selfRef = selfRef;
    }

    public String toString() {
        return selfRef;
    }
}
