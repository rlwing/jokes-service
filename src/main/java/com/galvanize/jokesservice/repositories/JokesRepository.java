package com.galvanize.jokesservice.repositories;

import com.galvanize.jokesservice.entities.Jokes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokesRepository extends CrudRepository<Jokes, Long> {
}
