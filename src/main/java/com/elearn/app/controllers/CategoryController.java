package com.elearn.app.controllers;

import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CustomMessage;
import com.elearn.app.services.CategoryService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //create category
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> create(
            @RequestBody CategoryDto categoryDto
    ){
        CategoryDto createdDto = categoryService.insert(categoryDto);
        //we have to return http status as well hence we are returning ResponseEntity
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdDto);
    }

    //get all category
    @GetMapping
    public List<CategoryDto> getAllCategory(@RequestBody String entity) {
        List<CategoryDto> categoryList = categoryService.getAll();
        return categoryList;
    }

    //get single category
    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable String id) {
        CategoryDto categoryDto = categoryService.get(id);
        return categoryDto;
    } 

    //delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomMessage> deleteCategory(@PathVariable String id){
        categoryService.delete(id);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("category deleted...");
        customMessage.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }

    //update category
    @PutMapping("/{categoryId}")
    public CategoryDto updateCategory(
        @PathVariable String categoryId, 
        @RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategoryDto = categoryService.update(categoryDto, categoryId);
        return updatedCategoryDto;
    }

}
