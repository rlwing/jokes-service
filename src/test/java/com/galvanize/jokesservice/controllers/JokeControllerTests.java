package com.galvanize.jokesservice.controllers;

import com.galvanize.jokesservice.entities.Category;
import com.galvanize.jokesservice.entities.Joke;
import com.galvanize.jokesservice.entities.JokeCategory;
import com.galvanize.jokesservice.repositories.JokeRepository;
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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JokeControllerTests {

    @Autowired
    JokeRepository jokeRepository;

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
            jokeRepository.save(joke);
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
    @Test
    @Transactional
    @Rollback
    public void getJokeRandom() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.joke").exists())
                .andExpect(jsonPath("$.source").exists())
                .andExpect(jsonPath("$.category").exists())
                .andExpect(jsonPath("$.jokeId").exists());
    }

    //Get a random joke for source
    @Test
    @Transactional
    @Rollback
    public void getJokeRandomForSource() throws Exception {
        mvc.perform(get("?source=EVENSOURCE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.joke").exists())
                .andExpect(jsonPath("$.source", is("EVENSOURCE")))
                .andExpect(jsonPath("$.category").exists())
                .andExpect(jsonPath("$.jokeId").exists());

    }

    //Get a random joke for category
    @Test
    @Transactional
    @Rollback
    public void getRandomForCategory() throws Exception{
        mvc.perform(get("?category=MOMJOKES"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.joke").exists())
                .andExpect(jsonPath("$.source").exists())
                .andExpect(jsonPath("$.category", is("MOMJOKES")))
                .andExpect(jsonPath("$.jokeId").exists());
    }

    //Get a random joke for source and category
    @Test
    @Transactional
    @Rollback
    public void getRandomForCategoryAndSource() throws Exception{
        mvc.perform(get("?category=MOMJOKES&source=MODTHREESOURCE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.joke").exists())
                .andExpect(jsonPath("$.source", is("MODTHREESOURCE")))
                .andExpect(jsonPath("$.category", is("MOMJOKES")))
                .andExpect(jsonPath("$.jokeId").exists());
    }

    //Get a joke by id
    @Test
    @Transactional
    @Rollback
    public void getJokeById() throws Exception{
        Joke joke = jokeRepository.findRandomJoke().get(0);
        String s = String.format("/%s", joke.getJokeId());
        mvc.perform(get(s))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.joke", is(joke.getJoke())))
                .andExpect(jsonPath("$.source", is(joke.getSource())))
                .andExpect(jsonPath("$.category", is(joke.getCategory().toString())))
                .andExpect(jsonPath("$.jokeId", is(joke.getJokeId().intValue())));
    }


    //Get the categories and descriptions
    @Test
    @Transactional
    @Rollback
    public void getCategories() throws Exception{
        mvc.perform(get("/category"))
                .andExpect(status().isOk());
    }
   
    private String getJSON(String path) throws Exception {
        URL url = this.getClass().getResource(path);
        return new String(Files.readAllBytes(Paths.get(url.getFile())));
    }
}
