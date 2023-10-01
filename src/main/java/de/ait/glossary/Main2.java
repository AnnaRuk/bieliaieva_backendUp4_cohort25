package de.ait.glossary;

import de.ait.glossary.config.AppConfig;
import de.ait.glossary.controller.DefController;
import de.ait.glossary.dto.NewDefinitionDto;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Main2 {
    public static void main(String[] args) {
       AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        DefController controller = applicationContext.getBean(DefController.class);
        Scanner scanner = applicationContext.getBean(Scanner.class);

         NewDefinitionDto two = new NewDefinitionDto(
                "Basic", //topic
                "Programming", //title
                " Программирование",//translation
                "Процесс написания кода с использованием конкретных языков программирования",//explanation
                "The process of writing code using specific programming languages",
                1                             //difficultyLevel

        );

        boolean isRun = true;

        while (isRun) {
            String command = scanner.nextLine(); // считываем команду

            switch (command) {
                case "/add Definition" ->
                        controller.addDefinition(two);
                case "/all" ->
                        controller.getAllDefinitions();
                case "/delete" ->
                        controller.removeDefinition();
                case "/update" ->
                        controller.updateDefinition();
                case "/exit" -> isRun = false;
            }
        }


    }






}



