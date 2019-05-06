package com.galvanize.jokesservice.repositories;

import com.galvanize.jokesservice.entities.Joke;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokeRepository extends CrudRepository<Joke, Long> {
}