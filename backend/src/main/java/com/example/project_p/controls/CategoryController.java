package com.example.project_p.controls;


import com.example.project_p.models.Category;
import com.example.project_p.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Component
@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){this.categoryService = categoryService;}

    @GetMapping(value = "/",params = "id",produces = "application/json")
    public ResponseEntity<Optional<Category>> getCategory(@RequestParam Long id){
        Optional<Category> category = Optional.ofNullable(categoryService.findById(id));
        return ResponseEntity.ok(category);
    }
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Optional<List<Category>>> getCategories(){
        Optional<List<Category>> categories = Optional.ofNullable(categoryService.findAll());
        return ResponseEntity.ok(categories);
    }

}
