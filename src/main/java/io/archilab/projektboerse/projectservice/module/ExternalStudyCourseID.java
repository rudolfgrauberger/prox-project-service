package io.archilab.projektboerse.projectservice.module;

import java.io.Serializable;
import java.net.URL;

import javax.persistence.Embeddable;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Data
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExternalStudyCourseID implements Serializable {

    private String selfRef;


    public ExternalStudyCourseID(URL selfRef) {
        this.selfRef = selfRef.getFile();
    }

    public String toString() {
        return selfRef;
    }
}
