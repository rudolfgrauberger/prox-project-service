package io.archilab.projektboerse.projectservice.project;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Data
@Setter(AccessLevel.NONE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectName {
    private static final int MAX_LENGTH = 255;

    private String name;

    public ProjectName(String name) {
        if (!ProjectName.isValid(name)) {
            throw new IllegalArgumentException(String
                    .format("Name %s exceeded maximum number of %d allowed characters", name,
                            ProjectName.MAX_LENGTH));
        }
        this.name = name;
    }

    public static boolean isValid(String name) {
        return name != null && name.length() <= ProjectName.MAX_LENGTH;
    }
}
