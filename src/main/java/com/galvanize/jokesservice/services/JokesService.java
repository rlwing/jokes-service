package com.galvanize.jokesservice.services;

import com.galvanize.jokesservice.repositories.JokesRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class JokeService {

    JokesRepository repo;

    @Autowired
    public JokeService(JokesRepository repo) {
        this.repo = repo;
    }
}
