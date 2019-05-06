package com.galvanize.jokesservice.controllers;

import com.galvanize.jokesservice.services.JokesService;
import org.springframework.beans.factory.annotation.Autowired;

public class JokeController {

    JokesService service;

    @Autowired
    public JokeController(JokesService service) {
        this.service = service;
    }
}
