package com.galvanize.jokesservice.services;

import com.galvanize.jokesservice.entities.Joke;
import com.galvanize.jokesservice.entities.JokeCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:test.properties")
public class JokesServiceTests {

    private List<Joke> jokes = new ArrayList<>();

    @Autowired
    private JokesService service;

    public JokesServiceTests() { }

    @Before
    @Transactional
    @Rollback
    public void setUp() throws Exception {
        Joke joke = null;
        for (int i = 0; i < 10; i++) {
            joke = new Joke();
            joke.setSource(String.format("email_$s@gmail.com"));
            if(i%2==0) {
                joke.setCategory(JokeCategory.DADJOKES);
                joke.setJoke(String.format("Dad Joke #%s", i));
                joke.setSource("EVENSOURCE");
            }else {
                joke.setCategory(JokeCategory.MOMJOKES);
                joke.setJoke(String.format("Mom Joke #%s", i));
                joke.setSource("ODDSOURCE");
            }
            jokes.add(joke);

        }
    }

    //Add a new joke
    @Test
    @Transactional
    @Rollback
    public void addNewJoke() {
        Joke expectedJoke = new Joke();
        expectedJoke.setSource("somebody@gmail.com");
        expectedJoke.setCategory(JokeCategory.KIDJOKES);
        expectedJoke.setJoke("Dirty Joke: The boy fell in the mud\nClean Joke: He took a shower");
        Joke actualJoke = service.addNewJoke(expectedJoke);

        assertTrue(actualJoke.getJokeId()!=null);
        assertTrue(actualJoke.getJoke().equals(expectedJoke.getJoke()));
        assertTrue((actualJoke.getSource().equals(expectedJoke.getSource())));
        assertTrue(actualJoke.getSource().equals(expectedJoke.getSource()));

    }

    //Get a joke by id
    @Test
    @Transactional
    @Rollback
    public void getJokeById() {
        jokes.stream().forEach(e -> service.addNewJoke(e));

        Joke joke = service.getJokeById(jokes.get(3).getJokeId());

        assertTrue(joke.getSource().equals(jokes.get(3).getSource()));
        assertTrue(joke.getCategory().equals(jokes.get(3).getCategory()));
        assertTrue(joke.getJoke().equals(jokes.get(3).getJoke()));

    }

    //Get a random joke

    @Test
    @Transactional
    @Rollback
    public void getRandomJoke() {
        jokes.stream().forEach(e -> service.addNewJoke(e));

        List<JokeCategory> actualCatagories = new ArrayList<>();
        List<String> actualJokes = new ArrayList<>();
        Joke randomJoke;
        for (int i = 0; i < 10; i++) {
            randomJoke = service.getRandomJoke();
            if(!actualCatagories.contains(randomJoke.getCategory())) {
                actualCatagories.add(randomJoke.getCategory());
            }
            if(!actualJokes.contains(randomJoke.getJoke())) {
                actualJokes.add(randomJoke.getJoke());
            }
        }
        //We should have at least two categories and two jokes
        assertTrue(actualCatagories.size()>1);
        assertTrue(actualJokes.size()>1);
    }

    //Get a random joke for category
    @Test
    @Transactional
    @Rollback
    public void getRandomForCategory() {
        jokes.stream().forEach(e -> service.addNewJoke(e));

        List<JokeCategory> actualCatagories = new ArrayList<>();
        List<String> actualJokes = new ArrayList<>();
        Joke randomJoke;
        for (int i = 0; i < 10; i++) {
            randomJoke = service.getRandomJokeForCategory(JokeCategory.DADJOKES);
            if(!actualCatagories.contains(randomJoke.getCategory())) {
                actualCatagories.add(randomJoke.getCategory());
            }
            if(!actualJokes.contains(randomJoke.getJoke())) {
                actualJokes.add(randomJoke.getJoke());
            }
        }
        //We should have at least two categories and two jokes
        assertFalse(actualCatagories.size()>1);
        assertTrue(actualJokes.size()>1);
    }


    //Get a random joke for source
    @Test
    @Transactional
    @Rollback
    public void getRandomForSource() {
        jokes.stream().forEach(e -> service.addNewJoke(e));

        List<JokeCategory> actualSource = new ArrayList<>();
        List<String> actualJokes = new ArrayList<>();
        Joke randomJoke;
        for (int i = 0; i < 10; i++) {
            randomJoke = service.getRandomJokeForCategory(JokeCategory.DADJOKES);
            if(!actualSource.contains(randomJoke.getCategory())) {
                actualSource.add(randomJoke.getCategory());
            }
            if(!actualJokes.contains(randomJoke.getJoke())) {
                actualJokes.add(randomJoke.getJoke());
            }
        }
        //We should have at least two categories and two jokes
        assertFalse(actualSource.size()>1);
        assertTrue(actualJokes.size()>1);
    }
}
