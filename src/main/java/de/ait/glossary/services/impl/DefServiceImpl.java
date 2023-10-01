package de.ait.glossary.services.impl;

import de.ait.glossary.dto.DefinitionDto;
import de.ait.glossary.dto.NewDefinitionDto;
import de.ait.glossary.models.Definition;
import de.ait.glossary.repository.DefRepository;
import de.ait.glossary.services.DefService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DefServiceImpl implements DefService {

  //  private final DefRepository defRepositoryJdbcImplImpl;
   private final DefRepository defRepositoryJdbcTemplateImplImpl;


    @Override
    public DefinitionDto addDefinition(NewDefinitionDto newDefinition) {
        Definition existDefinition = defRepositoryJdbcTemplateImplImpl.findByTitleAndTopic(newDefinition.getTopic(), newDefinition.getTitle());

        if (existDefinition != null) {
            throw new IllegalArgumentException("exists");
        }

        Definition definition = Definition.builder()
                .title(newDefinition.getTitle())
                .topic(newDefinition.getTopic())
                .translation(newDefinition.getTranslation())
                .explanation(newDefinition.getExplanation())
                .explanationEN(newDefinition.getExplanationEn())
                .role(Definition.Role.PROCESS)
                .difficultyLevel(newDefinition.getDifficultyLevel())
                .build();


        /// save
        defRepositoryJdbcTemplateImplImpl.save(definition);
        // return

        return DefinitionDto.from(definition);

    }

    @Override
    public List<DefinitionDto> getAllDefinitions() {
     List<Definition> definitions =  defRepositoryJdbcTemplateImplImpl.findAll();
        return DefinitionDto.from(definitions);
    }

    @Override
    public DefinitionDto deleteById(Long id) {
       Definition definition = defRepositoryJdbcTemplateImplImpl.deleteById(id);
        return DefinitionDto.from(definition);
    }

    @Override
    public DefinitionDto updateDifficultLevelById(Long id, int level) {
        Definition updateDefinition = defRepositoryJdbcTemplateImplImpl.findById(id);
        updateDefinition.setDifficultyLevel(level);
        Definition definition = defRepositoryJdbcTemplateImplImpl.update(updateDefinition);
        return DefinitionDto.from(definition);
    }
}
