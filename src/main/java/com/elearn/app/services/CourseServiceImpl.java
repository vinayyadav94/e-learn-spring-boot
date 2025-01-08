package com.elearn.app.services;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.entities.Course;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CourseRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService{

    private CourseRepo courseRepo;

    private ModelMapper modelMapper;

    @Autowired
    private FileService fileService;

    public CourseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper) {
        this.courseRepo = courseRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CourseDto create(CourseDto courseDto) {
        String courseId = UUID.randomUUID().toString();
        courseDto.setId(courseId);
        courseDto.setCreateddate(new Date());
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
        Course course = courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("course not found"));
        return entityTODto(course);
    }

    @Override
    public void delete(String courseid) {
        Course course = courseRepo
                .findById(courseid)
                .orElseThrow(() -> new ResourceNotFoundException("course not found!"));
        courseRepo.delete(course);
    }

    @Override
    public List<CourseDto> searchByTitle(String keyword) {
        List<Course> courses = courseRepo.findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(keyword, keyword);
        return courses.stream().map(course-> entityTODto(course)).collect(Collectors.toList());
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

    @Override
    public CourseDto getCourse(String courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("course not found"));
        return entityTODto(course);
    }

    @Override
    public CourseDto saveBanner(MultipartFile file, String courseId) throws IOException {
        Course course = courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("course not found"));
        String filePath = fileService.save(file, AppConstants.COURSE_BANNER_UPLOAD_DIR);
        course.setBanner(filePath);
        return modelMapper.map(courseRepo.save(course), CourseDto.class);
    }

    @Override
    public Resource getCourseBannerById(String courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("course not found"));
        String bannerPath = course.getBanner();
        Path path = Paths.get(bannerPath);
        System.out.println(bannerPath);
        Resource resource = new FileSystemResource(path);
        return resource;
    }
}
