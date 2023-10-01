package de.ait.glossary.examples;

import de.ait.glossary.dto.NewDefinitionDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ModelExamples {
//1
   public NewDefinitionDto one = new NewDefinitionDto(
            "Basic", //topic
            "Software Development", //title
            "Разработка программного обеспечения",//translation
            "Процесс создания программного обеспечения, " +
                    "включая планирование, проектирование, " +
                    "кодирование, тестирование и развертывание",//explanation
            "The process of creating software, including planning, designing, " +
                    "coding, testing and deployment",
            1                             //difficultyLevel

   );
//2
    public NewDefinitionDto two = new NewDefinitionDto(
            "Basic", //topic
            "Programming", //title
            " Программирование",//translation
            "Процесс написания кода с использованием конкретных языков программирования",//explanation
            "The process of writing code using specific programming languages",
            1                             //difficultyLevel



    );

//3
    public NewDefinitionDto three = new NewDefinitionDto(
            "Basic", //topic
            "Code", //title
            "Код",//translation
            "Серия инструкций, написанных программистом на языке программирования, которые выполняют определенные задачи",//explanation
            "A series of instructions written by a programmer in a programming language that perform specific tasks",
            1                             //difficultyLevel

    );

//4
 NewDefinitionDto fore = new NewDefinitionDto(
        "Basic", //topic
        " Algorithm", //title
        "Алгоритм",//translation
        "Последовательность шагов или инструкций, используемых для решения конкретной задачи",//explanation
        "A sequence of steps or instructions used to solve a specific problem",
        1                             //difficultyLevel

);

    //5
    NewDefinitionDto five = new NewDefinitionDto(
            "Basic", //topic
            "Debugging", //title
            "Отладка",//translation
            "Процесс поиска и устранения ошибок в программном коде.",//explanation
            "The process of finding and fixing errors in program code.",
            2                             //difficultyLevel

    );

    //TODO 6
    NewDefinitionDto six = new NewDefinitionDto(
            "Basic", //topic
            "Testing", //title
            "Тестирование",//translation
            " Процесс проверки программного кода на наличие ошибок и соответствие требованиям.",//explanation
            "Process of checking software code for errors and compliance.",
            1                             //difficultyLevel

    );






}
