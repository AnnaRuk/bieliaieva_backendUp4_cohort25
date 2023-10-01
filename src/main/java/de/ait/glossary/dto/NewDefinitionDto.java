package de.ait.glossary.dto;


import de.ait.glossary.models.Definition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//TODO Validation

public class NewDefinitionDto {

    private String topic;
    private String title;
    private String translation;
    private String explanation; ///
    private String explanationEn;
    private int difficultyLevel; ///1-10


}
