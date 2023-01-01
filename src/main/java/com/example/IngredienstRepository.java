package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredienstRepository extends JpaRepository<Ingredienst, Long> {


    List<Ingredienst> getByTitle(String title);

}
