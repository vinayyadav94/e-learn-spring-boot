package com.elearn.app.services;

import com.elearn.app.dtos.CourseDto;
import com.elearn.app.entities.Course;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepo courseRepo;

    private ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper) {
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDto create(CourseDto courseDto) {
        Course savedCourse = courseRepo.save(dtoToEntity(courseDto));
        return entityTODto(savedCourse);
    }

    @Override
    public List<CourseDto> getAll() {
        List<Course> courses = courseRepo.findAll();

        //convert all course i.e. list into course dto

        List<CourseDto> courseListDto = courses
                .stream().
                map(course ->
                        entityTODto(course)).
                collect(Collectors.toList());

        return courseListDto;
    }

    @Override
    public CourseDto update(CourseDto dto, String courseId) {
        return null;
    }

    @Override
    public void delete(String courseid) {
        Course course = courseRepo
                .findById(courseid)
                .orElseThrow(() -> new ResourceNotFoundException("course not found!"));
        courseRepo.delete(course);
    }

    @Override
    public List<CourseDto> searchByTitle(String titleKeyword) {
        return null;
    }

    public CourseDto entityTODto(Course course){
//        CourseDto courseDto = new CourseDto();
//        courseDto.setTitle(course.getTitle());
//        courseDto.setShortDesc(course.getShortDesc());
        // ....
        //set like same for all the remaining fields

        CourseDto courseDto = modelMapper.map(course, CourseDto.class);
        return courseDto;
    }

    public Course dtoToEntity(CourseDto dto){
//        Course course = new Course();
//        course.setTitle(dto.getTitle());
//        course.setShortDesc(dto.getShortDesc());
        // ....
        //set like same for all the remaining fields

        Course course = modelMapper.map(dto, Course.class);
        return course;
    }
}
