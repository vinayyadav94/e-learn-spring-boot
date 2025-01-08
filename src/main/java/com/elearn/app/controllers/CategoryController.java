package com.elearn.app.controllers;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomMessage;
import com.elearn.app.dtos.CustomPaginationResponse;
import com.elearn.app.services.CategoryService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<?> create(
            @Valid @RequestBody CategoryDto categoryDto
           // BindingResult result
    ){
        // if(result.hasErrors()){
        //     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data!");
        // }

        CategoryDto createdDto = categoryService.insert(categoryDto);
        //we have to return http status as well hence we are returning ResponseEntity
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdDto);
    }

    //get all category
    @GetMapping
    public CustomPaginationResponse<CategoryDto> getAllCategory(
        @RequestParam(value="pageNumber", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
        @RequestParam(value="pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
        @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy
    ) {
        return categoryService.getAll(pageNumber, pageSize, sortBy);
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

    @PostMapping("/{categoryId}/courses/{courseid}")
    public ResponseEntity<CustomMessage> addCourseToCategory(
        @PathVariable String categoryId,
        @PathVariable String courseid
    ){
        categoryService.addCourseTocategory(categoryId, courseid);
        
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("category course relationship updated");
        customMessage.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }

    @GetMapping("/{categoryId}/courses")
    public ResponseEntity<List<CourseDto>> getCoursesOfCatefory(@PathVariable String categoryId){
        return ResponseEntity.ok(categoryService.getCoursesOfCat(categoryId));
    }

}
