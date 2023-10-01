package de.ait.glossary.controller;

import de.ait.glossary.dto.DefinitionDto;
import de.ait.glossary.dto.NewDefinitionDto;
import de.ait.glossary.services.DefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;;

import java.util.List;
import java.util.Scanner;

@Component
public class DefController {

    private final DefService defService;

    public DefController(DefService defService) {
        this.defService = defService;
    }

    public DefinitionDto addDefinition(NewDefinitionDto newDefinition){
        DefinitionDto definition = defService.addDefinition(newDefinition);
        System.out.println("Definition: " + definition + " was added successful");
        return definition;
    }

    public List<DefinitionDto> getAllDefinitions(){
        List<DefinitionDto> defs = defService.getAllDefinitions();
        System.out.println(defs);
        return defs;
}

     public DefinitionDto removeDefinition(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("input id: ");
         Long id = scanner.nextLong();

         DefinitionDto dto = defService.deleteById(id);
         System.out.println("Definition with title: <" + dto.getTitle() + "> from topic <" + dto.getTopic() + "> was delete successful");
        return dto;
     }

     public DefinitionDto updateDefinition(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("input id: ");
         Long id = scanner.nextLong();
         System.out.println("input new difficult level: ");
         int level = scanner.nextInt();
         DefinitionDto dto = defService.updateDifficultLevelById(id,level);
         System.out.println("Difficult level from definition with title: <" + dto.getTitle() + "> from topic <" + dto.getTopic() + "> was update successful");
         return dto;

     }







}
