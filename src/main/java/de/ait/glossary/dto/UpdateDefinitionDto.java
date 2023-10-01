package de.ait.glossary.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

//TODO Validation
public class UpdateDefinitionDto {
    private int difficultyLevel; ///1-10

}
