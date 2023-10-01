package de.ait.glossary.repository.impl;

import de.ait.glossary.models.Definition;
import de.ait.glossary.repository.DefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor ///final fields
public class DefRepositoryJdbcImplImpl implements DefRepository {

    private final DataSource dataSource;
    //language=sql
    private static final String INSERT_SQL = "insert into DEFINITIONS(topic,title,translation,explanation,explanationEn,role,difficultyLevel) values(?,?,?,?,?,?,?);";
    //language=sql
    private static final String SELECT_ALL_SQL = "select * from DEFINITIONS order by id;";
    //language=sql
    private static final String DELETE_BY_ID_SQL = "delete from  DEFINITIONS where id = ?";

    //language=sql
    private static final String UPDATE_SQL = "update DEFINITIONS set difficultylevel = ? where id = ?";


    //language=sql
    private static final String SELECT_BY_TOPIC_AND_TITLE_SQL = "select * from DEFINITIONS where topic = ? and title = ? limit 1";
    //language=sql
    private static final String SELECT_BY_ID_SQL = "select * from DEFINITIONS where id = ? limit 1";

    @Override
    public void save(Definition definition) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_SQL)
        )
        {
            statement.setString(1, definition.getTopic());
            statement.setString(2, definition.getTitle());
            statement.setString(3, definition.getTranslation());
            statement.setString(4, definition.getExplanation());
            statement.setString(5, definition.getExplanationEN());
            statement.setString(6, Definition.Role.PROCESS.name());
            statement.setInt(7, definition.getDifficultyLevel());

            int result = statement.executeUpdate();
            if(result != 1){
                throw new SQLException("cannot insert definition");
            }


        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }


    }

    @Override
    public Definition findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()){

                if (resultSet.next()) {

                    return Definition.builder()
                            .id(resultSet.getLong("id"))
                            .topic(resultSet.getString("topic"))
                            .title(resultSet.getString("title"))
                            .translation(resultSet.getString("translation"))
                            .explanation(resultSet.getString("explanation"))
                            .explanationEN(resultSet.getString("explanationen"))
                            .role(Definition.Role.valueOf(resultSet.getString("role").toUpperCase()))
                            .difficultyLevel(resultSet.getInt("difficultyLevel"))
                            .build();
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    @Override
    public List<Definition> findAll() {
        List<Definition> definitions = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){

                Definition definition = Definition.builder()
                        .id(resultSet.getLong("id"))
                        .topic(resultSet.getString("topic"))
                        .title(resultSet.getString("title"))
                        .translation(resultSet.getString("translation"))
                        .explanation(resultSet.getString("explanation"))
                        .explanationEN(resultSet.getString("explanationen"))
                        .role(Definition.Role.valueOf(resultSet.getString("role").toUpperCase()))
                        .difficultyLevel(resultSet.getInt("difficultyLevel"))
                        .build();

                definitions.add(definition);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return definitions;
    }


    @Override
    public Definition deleteById(Long id) {
        Definition definition =  findById(id);
        if (definition == null) {
            return null;
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            statement.setLong(1, id);
            int resultSet = statement.executeUpdate();
            if (resultSet == 1){
                return definition;
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return null;
    }


    @Override
    public Definition update(Definition updateDefinition) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {

            statement.setLong(1, updateDefinition.getDifficultyLevel());
            statement.setLong(2, updateDefinition.getId());

            int resultSet = statement.executeUpdate();
                if (resultSet == 1) {
                    return updateDefinition;

            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;

    }


    @Override
    public Definition findByTitleAndTopic(String topic, String title) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_TOPIC_AND_TITLE_SQL)) {

            statement.setString(1,topic);
            statement.setString(2,title);

            try (ResultSet resultSet = statement.executeQuery()){

           if (resultSet.next()) {

               return Definition.builder()
                       .id(resultSet.getLong("id"))
                       .topic(resultSet.getString("topic"))
                       .title(resultSet.getString("title"))
                       .translation(resultSet.getString("translation"))
                       .explanation(resultSet.getString("explanation"))
                       .explanationEN(resultSet.getString("explanationen"))
                       .role(Definition.Role.valueOf(resultSet.getString("role").toUpperCase()))
                       .difficultyLevel(resultSet.getInt("difficultyLevel"))
                       .build();
           }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }
}
