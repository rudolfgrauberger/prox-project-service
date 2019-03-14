package io.archilab.projektboerse.projectservice.module;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.net.URL;

@Embeddable
@Data
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExternalModuleID implements Serializable {

    private String selfRef;


    public ExternalModuleID(URL selfRef) {
        this.selfRef = selfRef.getFile();
    }

    public String toString() {
        return selfRef;
    }
}
