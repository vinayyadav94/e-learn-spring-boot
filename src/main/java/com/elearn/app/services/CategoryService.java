package com.elearn.app.services;

import java.util.List;

import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomPaginationResponse;

public interface CategoryService {

    CategoryDto insert(CategoryDto categoryDto);

    CustomPaginationResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy);

    CategoryDto get(String categoryId);

    void delete(String categoryId);

    CategoryDto update(CategoryDto categoryDto, String categoryId);

    //mapping
    void addCourseTocategory(String catId, String course);

    List<CourseDto> getCoursesOfCat(String catId);

}
