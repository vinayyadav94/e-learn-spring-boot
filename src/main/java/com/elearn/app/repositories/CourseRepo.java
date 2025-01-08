package com.elearn.app.repositories;

import com.elearn.app.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course, String> {

    Optional<Course> findByTitle(String title);

    List<Course> findByLive(boolean live);

    //List<Course> findByCategoryId(String id);

    //search course
    List<Course> findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(String keyword, String keyword1);


}
