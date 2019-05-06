package com.galvanize.jokesservice.services;

import com.galvanize.jokesservice.repositories.JokesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JokesService {

    JokesRepository repo;

    @Autowired
    public JokesService(JokesRepository repo) {
        this.repo = repo;
    }
}
