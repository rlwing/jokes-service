package com.galvanize.jokesservice.controllers;

import com.galvanize.jokesservice.entities.Joke;
import com.galvanize.jokesservice.repositories.CategoryRepository;
import com.galvanize.jokesservice.repositories.JokeRepository;
import com.galvanize.jokesservice.services.JokesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class JokeController {

    CategoryRepository categoryRepository;
    JokeRepository jokeRepository;

    public JokeController(CategoryRepository categoryRepository, JokeRepository jokeRepository) {
        this.categoryRepository = categoryRepository;
        this.jokeRepository = jokeRepository;
    }

    @Autowired


    @GetMapping("/all")
    public List<Joke> getAllJokes(){
        List<Joke> jokeList = jokeRepository.findAll();
        return jokeList;
    }

    @PostMapping("/")
    public Joke postNewJoke(@RequestBody Joke joke){
        Joke newJoke = jokeRepository.save(joke);
        return newJoke;
    }

    @GetMapping("/")
    public Joke getRandomJoke(@RequestParam(required = false) String category,
                                    @RequestParam(required = false) String source){
        if(category != null && source != null){
            return jokeRepository.findRandomJokeForCategoryAndSource(category, source).get(0);
        }
        if(category != null && source == null){
            return jokeRepository.findRandomJokeForCategory(category).get(0);
        }
        if(source != null && category == null){
            return jokeRepository.findRandomJokeForSource(source).get(0);
        }
        if(source == null && category == null){
            return jokeRepository.findRandomJoke().get(0);
        }

        return null;
    }

    @GetMapping("/{id}")
    public Joke getJoke(@PathVariable Long id){
        return jokeRepository.findById(id).get();
    }
}
