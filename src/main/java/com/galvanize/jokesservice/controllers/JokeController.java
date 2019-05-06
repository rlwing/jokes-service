package com.galvanize.jokesservice.controllers;

import com.galvanize.jokesservice.entities.Joke;
import com.galvanize.jokesservice.repositories.JokeRepository;
import com.galvanize.jokesservice.services.JokesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JokeController {

    JokesService service;
    JokeRepository repo;

    @Autowired
    public JokeController(JokesService service, JokeRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping("/")
    public List<Joke> getAllJokes(){
        List<Joke> jokeList = repo.findAll();
        return jokeList;
    }

    @PostMapping("/")
    public Joke postNewJoke(@RequestBody Joke joke){
        Joke newJoke = repo.save(joke);
        return newJoke;
    }
}
