package com.elearn.app.services;

import com.elearn.app.dtos.CourseDto;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService {

    CourseDto create(CourseDto courseDto);

    List<CourseDto> getAll();

    CourseDto getCourse(String courseId);

    CourseDto update(CourseDto dto, String courseId);

    void delete(String courseid);

    List<CourseDto> searchByTitle(String titleKeyword);

    public CourseDto saveBanner(MultipartFile file, String courseId) throws IOException;

    Resource getCourseBannerById(String courseId);

}
