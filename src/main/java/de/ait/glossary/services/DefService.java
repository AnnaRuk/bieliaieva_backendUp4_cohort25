package de.ait.glossary.services;

import de.ait.glossary.dto.DefinitionDto;
import de.ait.glossary.dto.NewDefinitionDto;

import java.util.List;

public interface DefService {
    DefinitionDto addDefinition(NewDefinitionDto definition);

    List<DefinitionDto> getAllDefinitions();

    DefinitionDto deleteById(Long id);

    DefinitionDto updateDifficultLevelById(Long id, int level);
}
