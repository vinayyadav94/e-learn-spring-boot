package com.elearn.app.services;

import com.elearn.app.dtos.CourseDto;

import java.util.List;

public interface CourseService {

    CourseDto create(CourseDto courseDto);

    List<CourseDto> getAll();

    CourseDto getCourse(String courseId);

    CourseDto update(CourseDto dto, String courseId);

    void delete(String courseid);

    List<CourseDto> searchByTitle(String titleKeyword);

}
