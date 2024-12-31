package com.elearn.app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomMessage;
import com.elearn.app.services.CourseService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto courseDto){
        CourseDto savedCourse = courseService.create(courseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCourse);
    }

    @GetMapping
    public List<CourseDto> getAllCourse(){
        return courseService.getAll();
    }

    @GetMapping("/{courseId}")
    public CourseDto getCourse(@PathVariable String courseId){
       return courseService.getCourse(courseId);
    }

    @PutMapping("/{courseId}")
    public CourseDto updateCourse(@RequestBody CourseDto courseDto, @PathVariable String courseId){
        return courseService.update(courseDto, courseId);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<CustomMessage> deleteCourse(@PathVariable String courseId){
        courseService.delete(courseId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("course has been deleted");
        customMessage.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);
    }

}
