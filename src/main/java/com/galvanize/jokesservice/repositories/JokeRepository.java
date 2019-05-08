package com.galvanize.jokesservice.repositories;

import com.galvanize.jokesservice.entities.Joke;
import com.galvanize.jokesservice.entities.JokeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JokeRepository extends JpaRepository<Joke, Long> {

    @Query(value = "SELECT * from jokes j order by RAND() LIMIT 1", nativeQuery = true)
    public List<Joke> findRandomJoke();

    @Query(value = "SELECT * from jokes j where CATEGORY = ?1 order by RAND() LIMIT 1", nativeQuery = true)
    public List<Joke> findRandomJokeForCategory(String category);

    @Query(value = "SELECT * from jokes j where SOURCE = ?1 order by RAND() LIMIT 1", nativeQuery = true)
    public List<Joke> findRandomJokeForSource(String source);

    @Query(value = "SELECT * from jokes j where CATEGORY = ?1 and SOURCE = ?2 order by RAND() LIMIT 1", nativeQuery = true)
    public List<Joke> findRandomJokeForCategoryAndSource(String category, String source);
}
