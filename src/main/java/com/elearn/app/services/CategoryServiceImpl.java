package com.elearn.app.services;

import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CustomPaginationResponse;
import com.elearn.app.entities.Category;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CategoryRepo;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepo categoryRepo;

    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
        this.categoryRepo = categoryRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) {
        //create category id
        String catId = UUID.randomUUID().toString();
        categoryDto.setId(catId);
        //set date
        categoryDto.setAddedDate(new Date());
        //convert dto to entity
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepo.save(category);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CustomPaginationResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy) {
        //sorting
        Sort sort = Sort.by(sortBy).ascending();
        //page request for pagination
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        
        Page<Category> categoryPage = categoryRepo.findAll(pageRequest);

        List<Category> categoryList = categoryPage.getContent();

        List<CategoryDto> categoryListDto = categoryList.stream().map(category-> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

        CustomPaginationResponse<CategoryDto> customPaginationResponse = new CustomPaginationResponse<>();

        customPaginationResponse.setContent(categoryListDto);
        customPaginationResponse.setPageNumber(categoryPage.getNumber());
        customPaginationResponse.setPageSize(categoryPage.getSize());
        customPaginationResponse.setTotalElements(categoryPage.getTotalElements());
        customPaginationResponse.setTotalPages(categoryPage.getTotalPages());
        customPaginationResponse.setLast(categoryPage.isLast());

        return customPaginationResponse;
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found"));
        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        Category categoryById = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found"));
        categoryRepo.delete(categoryById);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found"));
        category.setTitle(categoryDto.getTitle());
        category.setDesc(categoryDto.getDesc());
        Category updatedcategory = categoryRepo.save(category);
        return modelMapper.map(updatedcategory, CategoryDto.class);
    }
}
