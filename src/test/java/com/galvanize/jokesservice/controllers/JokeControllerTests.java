package com.galvanize.jokesservice.controllers;

import com.galvanize.jokesservice.entities.Joke;
import com.galvanize.jokesservice.entities.JokeCategory;
import com.galvanize.jokesservice.repositories.JokeRepository;
import com.galvanize.jokesservice.services.JokesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.transaction.Transactional;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JokeControllerTests {

    @Autowired
    JokeRepository repository;

    @Autowired
    MockMvc mvc;

    @Before
    @Transactional
    @Rollback
    public void setUp() throws Exception {
        Joke joke = null;
        for (int i = 0; i < 20; i++) {
            joke = new Joke();
            joke.setSource(String.format("email_$s@gmail.com"));
            if(i%2==0) {
                joke.setCategory(JokeCategory.DADJOKES);
                joke.setJoke(String.format("Dad Joke #%s", i));
                joke.setSource("EVENSOURCE");
            }else if (i%3==0) {
                joke.setCategory(JokeCategory.MOMJOKES);
                joke.setJoke(String.format("Mom Joke #%s", i));
                joke.setSource("MODTHREESOURCE");
            }else{
                joke.setCategory(JokeCategory.DADJOKES);
                joke.setJoke(String.format("Dad Joke #%s", i));
                joke.setSource("OTHERSOURCE");
            }
            repository.save(joke);
        }
    }

    //Add a new joke
    @Test
    @Transactional
    @Rollback
    public void addNewJoke() throws Exception{
        String json = getJSON("/joke.json");
        MockHttpServletRequestBuilder request = post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.joke", is("Why did the chicken cross the road?\nTo get to the other side!")))
                .andExpect(jsonPath("$.source", is("JsonFile")))
                .andExpect(jsonPath("$.category", is("KIDJOKES")))
                .andExpect(jsonPath("$.jokeId").exists());
    }

    //Get a random joke

    //Get a random joke by source

    //Get a random joke by category

    //Get a joke by id

   
    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}
