package com.galvanize.jokesservice.services;

import com.galvanize.jokesservice.entities.Joke;
import com.galvanize.jokesservice.entities.JokeCategory;
import com.galvanize.jokesservice.repositories.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Joke getRandomJokeForCategory(JokeCategory category) {
        List<Joke> jokeList = repo.findRandomJokeForCategory(category.toString());
        return jokeList.size()>0 ? jokeList.get(0) : null;
    }

    public Joke getRandomeJokeForSource(String source){
        List<Joke> jokeList =  repo.findRandomJokeForSource(source);
        return jokeList.size()>0 ? jokeList.get(0) : null;
    }

    public List<Joke> findAllJokes() {
        return repo.findAll();
    }
}
