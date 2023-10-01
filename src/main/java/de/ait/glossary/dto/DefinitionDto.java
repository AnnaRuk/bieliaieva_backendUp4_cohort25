package de.ait.glossary.dto;


import de.ait.glossary.models.Definition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefinitionDto {

    private String topic;
    private String title;

    public static DefinitionDto from(Definition definition){
        return DefinitionDto.builder()
                .title(definition.getTitle())
                .topic(definition.getTopic())
                .build();
    }

    public static List<DefinitionDto> from(List<Definition> definitions){
        return definitions.stream()
                .map(DefinitionDto::from)
                .collect(Collectors.toList());
    }

}
