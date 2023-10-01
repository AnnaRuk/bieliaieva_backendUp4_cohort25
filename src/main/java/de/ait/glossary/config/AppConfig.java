package de.ait.glossary.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.ait.glossary.controller.DefController;
import de.ait.glossary.repository.impl.DefRepositoryJdbcImplImpl;
import de.ait.glossary.services.impl.DefServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Scanner;

@Configuration
//@PropertySource("classpath:application.properties")

@ComponentScan(basePackages = "de.ait.glossary")
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public HikariConfig hikariConfig(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/eventsU4_db");
        config.setUsername("postgres");
        config.setPassword("qwerty43!");
        config.setMaximumPoolSize(20);

        return config;
    }

    @Bean
    public DataSource dataSource(HikariConfig config){
        return new HikariDataSource(config);
    }

    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }


//
//    @Bean
//    public DefController controller(DefServiceImpl service){
//        return new DefController(service);  //+sevrice
//    }

//    @Bean
//    public DefServiceImpl service(DefRepositoryJdbcImplImpl repositoryJdbcImpl){
//        return new DefServiceImpl(repositoryJdbcImpl);  ///+repos
//    }

//    @Bean
//    public DefRepositoryJdbcImplImpl repositoryJdbcImpl(DataSource dataSource){
//        return new DefRepositoryJdbcImplImpl(dataSource); //+ data source
//    }


}
