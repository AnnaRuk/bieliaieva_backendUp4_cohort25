package de.ait.glossary.repository;

import de.ait.glossary.models.Definition;

public interface DefRepository extends CrudRepository<Definition>{

 Definition findByTitleAndTopic(String topic, String title);

}
