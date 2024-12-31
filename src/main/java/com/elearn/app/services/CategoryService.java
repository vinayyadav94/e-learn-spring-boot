package com.elearn.app.services;

import com.elearn.app.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto insert(CategoryDto categoryDto);

    List<CategoryDto> getAll();

    CategoryDto get(String categoryId);

    void delete(String categoryId);

    CategoryDto update(CategoryDto categoryDto, String categoryId);

}
