package com.galvanize.jokesservice.controllers;

import com.galvanize.jokesservice.entities.Category;
import com.galvanize.jokesservice.entities.Joke;
import com.galvanize.jokesservice.entities.JokeCategory;
import com.galvanize.jokesservice.repositories.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JokeController {

    JokeRepository jokeRepository;

    public JokeController(JokeRepository jokeRepository) {
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

    @GetMapping("/category")
    public List<Category> getCategories(){
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(JokeCategory.DADJOKES.toString(), JokeCategory.DADJOKES.getType()));
        categories.add(new Category(JokeCategory.MOMJOKES.toString(), JokeCategory.MOMJOKES.getType()));
        categories.add(new Category(JokeCategory.KIDJOKES.toString(), JokeCategory.KIDJOKES.getType()));
        categories.add(new Category(JokeCategory.KNOCKKNOCK.toString(), JokeCategory.KNOCKKNOCK.getType()));
        categories.add(new Category(JokeCategory.TECHNOLOGY.toString(), JokeCategory.TECHNOLOGY.getType()));
        return categories;
    }
}
