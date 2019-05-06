package com.galvanize.jokesservice.services;

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
}
