package com.galvanize.jokesservice.repositories;

import com.galvanize.jokesservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
