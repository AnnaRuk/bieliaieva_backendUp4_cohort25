package de.ait.glossary;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.ait.glossary.controller.DefController;
import de.ait.glossary.dto.NewDefinitionDto;
import de.ait.glossary.repository.DefRepository;
import de.ait.glossary.repository.impl.DefRepositoryJdbcImplImpl;
import de.ait.glossary.services.DefService;
import de.ait.glossary.services.impl.DefServiceImpl;

import javax.sql.DataSource;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        HikariConfig config = getHikariConfig();
        DataSource dataSourceConfig = new HikariDataSource(config);

        DefRepository repository = new DefRepositoryJdbcImplImpl(dataSourceConfig);

        DefService service = new DefServiceImpl(repository);
        DefController controller = new DefController(service);
        Scanner scanner = new Scanner(System.in);

        NewDefinitionDto five = new NewDefinitionDto(
                "Basic", //topic
                "Debugging", //title
                "Отладка",//translation
                "Процесс поиска и устранения ошибок в программном коде.",//explanation
                "The process of finding and fixing errors in program code.",
                2                             //difficultyLevel

        );


        long before = System.currentTimeMillis();

        boolean isRun = true;

        while (isRun) {
            String command = scanner.nextLine(); // считываем команду

            switch (command) {
                case "/add Definition 5" ->  // если это команда на регистрацию
                        controller.addDefinition(five); // регистрируем пользователя
                case "/all" ->  // если это команда на получение пользователей
                        controller.getAllDefinitions();
                case "/exit" -> isRun = false;
            }
        }
        long after = System.currentTimeMillis();
        System.out.println(after - before);



    }

    private static HikariConfig getHikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/eventsU4_db");
        config.setUsername("postgres");
        config.setPassword("qwerty43!");
        config.setMaximumPoolSize(20);
        return config;
    }
}

