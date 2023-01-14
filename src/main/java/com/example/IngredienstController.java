package com.example;




import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ingredients/api/v3")
public class IngredienstController {

    private final IngredienstService ingredienstService;

    public IngredienstController(IngredienstService ingredienstService) {
        this.ingredienstService = ingredienstService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllPrograms(){
        List<IngredienstDTO> ingredienstDTOList = ingredienstService.listAllIngredienst();

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("Version", "Math.V3")
                .body(new ResponseWrapper("All program are successfully retrieved",ingredienstDTOList));
    }


    @GetMapping("/{title}")
    public ResponseEntity<ResponseWrapper> getIngredientByTitle(@PathVariable("title") String title) {
        List<IngredienstDTO> dto = ingredienstService.getByTitle(title);
        return ResponseEntity.ok(new ResponseWrapper("Program is successfully retrieved", dto, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createIngredient(@RequestBody IngredienstDTO ingredienst) {
        ingredienstService.save(ingredienst);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Program is successfully created", HttpStatus.CREATED));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateProgram(@RequestBody IngredienstDTO ingredienstDTO) {
        Ingredienst ing = ingredienstService.update(ingredienstDTO);
        return ResponseEntity.ok(new ResponseWrapper("Program is successfully updated", ing, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper> deleteProgram(@PathVariable("id") Long id) {
        ingredienstService.delete(id);
        return ResponseEntity.ok(new ResponseWrapper("Program is successfully deleted", HttpStatus.OK));

    }

}
