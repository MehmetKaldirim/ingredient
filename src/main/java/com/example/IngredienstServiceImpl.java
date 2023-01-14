package com.example;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredienstServiceImpl implements IngredienstService{

    private final IngredienstRepository ingredienstRepository;
    private final MapperUtil mapperUtil;

    public IngredienstServiceImpl(IngredienstRepository ingredienstRepository, MapperUtil mapperUtil) {
        this.ingredienstRepository = ingredienstRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<IngredienstDTO> listAllIngredienst() {
        List<IngredienstDTO> ingredienstDTO = ingredienstRepository.findAll().stream()
                .map(ingredienst->mapperUtil.convert(ingredienst,new IngredienstDTO()))
                .filter(a->a.getIsDeleted()== false)
                .collect(Collectors.toList());
        return ingredienstDTO;
    }



    @Override
    public void save(IngredienstDTO dto) {
        ingredienstRepository.save(mapperUtil.convert(dto,new Ingredienst()));
    }

    @Override
    public Ingredienst update(IngredienstDTO dto) {
        Ingredienst ingredienst = ingredienstRepository.findById(dto.getId()).get();
        //System.out.println("here is ingredienst code and  id " +  ingredienst.getId());


        Ingredienst convertedIngredienst = mapperUtil.convert(dto, new Ingredienst());
        //set id to converted object which we found in DB by Email
        convertedIngredienst.setId(ingredienst.getId());
        ingredienstRepository.save(convertedIngredienst);
        return convertedIngredienst;
    }

    @Override
    public void delete(Long id) {
        Ingredienst ingredienst = ingredienstRepository.findById(id).get();
        ingredienst.setIsDeleted(true);
        ingredienstRepository.save(ingredienst);
}
    @Override
    public List<IngredienstDTO> getByTitle(String  title){
        List<Ingredienst> ingredients = ingredienstRepository.getByTitle(title);
        List<IngredienstDTO> listDto = ingredients.stream().map(every->mapperUtil.convert(every, new IngredienstDTO())).collect(Collectors.toList());
        return  listDto;
    }
}
