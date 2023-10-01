package de.ait.glossary.repository.impl;

import de.ait.glossary.models.Definition;
import de.ait.glossary.repository.DefRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
//@ActiveProfiles("test")
public class DefRepositoryJdbcTemplateImplImpl implements DefRepository {

    private final JdbcTemplate jdbcTemplate;

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

    private static final RowMapper<Definition> DEFINITION_ROW_MAPPER = (resultSet, rowNum) -> Definition.builder()
            .id(resultSet.getLong("id"))
            .topic(resultSet.getString("topic"))
            .title(resultSet.getString("title"))
            .translation(resultSet.getString("translation"))
            .explanation(resultSet.getString("explanation"))
            .explanationEN(resultSet.getString("explanationen"))
            .role(Definition.Role.valueOf(resultSet.getString("role").toUpperCase()))
            .difficultyLevel(resultSet.getInt("difficultyLevel"))
            .build();

        @Override
        public Definition findById(Long id) {
            try{
                return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, DEFINITION_ROW_MAPPER, id);
            } catch (EmptyResultDataAccessException e){
                return null;
            }
        }

        @Override
        public List<Definition> findAll() {
            try{
                return jdbcTemplate.query(SELECT_ALL_SQL, DEFINITION_ROW_MAPPER);
            } catch (EmptyResultDataAccessException e){
                return null;
            }
        }

        @Override
        public void save(Definition model) {
            SimpleJdbcInsert jdbcInsert  = new SimpleJdbcInsert(jdbcTemplate)
                    .withTableName("definitions")
                    .usingGeneratedKeyColumns("id");

            Map<String, Object> row = new HashMap<>();
            row.put("topic",model.getTopic());
            row.put("title",model.getTitle());
            row.put("translation",model.getTranslation());
            row.put("explanation",model.getExplanation());
            row.put("explanationEn",model.getExplanationEN());
            row.put("role",model.getRole());
            row.put("difficultyLevel",model.getDifficultyLevel());

            Long generatedId =  jdbcInsert.executeAndReturnKey(row).longValue();

            model.setId(generatedId);

        }

        @Override
        public Definition deleteById(Long id) {
            try{
                jdbcTemplate.update(DELETE_BY_ID_SQL, id);
            } catch (EmptyResultDataAccessException e){
                return null;
            }
            return findById(id);

        }

        @Override
        public Definition update(Definition model) {
            try {
                jdbcTemplate.update(UPDATE_SQL, model.getDifficultyLevel());
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
            return model;
        }

        @Override
        public Definition findByTitleAndTopic(String topic, String title) {
            try{
                return jdbcTemplate.queryForObject(SELECT_BY_TOPIC_AND_TITLE_SQL,DEFINITION_ROW_MAPPER, topic,title);
            } catch (EmptyResultDataAccessException e){
                return null;
            }

        }
    }
