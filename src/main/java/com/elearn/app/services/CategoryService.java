package com.elearn.app.services;

import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CustomPaginationResponse;

public interface CategoryService {

    CategoryDto insert(CategoryDto categoryDto);

    CustomPaginationResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy);

    CategoryDto get(String categoryId);

    void delete(String categoryId);

    CategoryDto update(CategoryDto categoryDto, String categoryId);

}
