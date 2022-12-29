package com.example.ingredient;

import java.util.List;

public interface IngredienstService {

    List<IngredienstDTO> listAllIngredienst();


    void save(IngredienstDTO dto);
    Ingredienst update(IngredienstDTO dto);
    void delete(Long id);

    IngredienstDTO getById(Long id);
}
