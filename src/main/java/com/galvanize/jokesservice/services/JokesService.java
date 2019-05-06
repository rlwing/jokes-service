package com.galvanize.jokesservice.services;

import com.galvanize.jokesservice.entities.Joke;
import com.galvanize.jokesservice.repositories.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokesService {

    JokeRepository repo;

    @Autowired
    public JokesService(JokeRepository repo) {
        this.repo = repo;
    }

    public Joke addNewJoke(Joke joke) {
        return repo.save(joke);
    }

    public Joke getJokeById(Long id){
        return repo.findById(id).get();
    }

    public Joke getRandomJoke(){
        return repo.findRandomJoke().get(0);
    }
}
