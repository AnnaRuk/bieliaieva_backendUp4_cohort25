package de.ait.glossary.models;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Definition {


    public enum Role {
        DONE, PROCESS
    }

    private Long id;
    private String topic;
    private String title;
    private String translation;
    private String explanation; ///
    private String explanationEN; ///
    private Role role; // DONE, PROCESS
    private int difficultyLevel; ///1-10


}
