package de.ait.glossary.repository;

import de.ait.glossary.models.Definition;

import java.util.List;

public interface CrudRepository <T> {

    T findById(Long id);

    List<T> findAll();
    void save(T model);
    Definition deleteById(Long id);

    Definition update(T model);
}
