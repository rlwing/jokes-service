package com.galvanize.jokesservice.repositories;

import com.galvanize.jokesservice.entities.Joke;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeRepository extends CrudRepository<Joke, Long> {

    @Query(value = "SELECT * from JOKE j order by RAND() LIMIT 1", nativeQuery = true)
    public List<Joke> findRandomJoke();

}
