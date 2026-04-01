package edu.hei.school.ingredients.controller;

import edu.hei.school.ingredients.entity.Ingredient;
import edu.hei.school.ingredients.exception.NotFoundException;
import edu.hei.school.ingredients.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientController {
    IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/ingredients")
    public ResponseEntity<?> getIngredients() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredientService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/ingredients/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable(name = "id") Integer ingredientId) {
        try {
            Ingredient ingredient = ingredientService.getById(ingredientId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ingredient);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
