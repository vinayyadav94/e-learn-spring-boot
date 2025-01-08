package com.elearn.app.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="courses")
@Data // @Data --> it will include getter setter and toString method everything
public class Course {

    @Id
    private String id;
    private String title;
    private String shortDesc;
    @Column(length = 1000)
    private String longDesc;
    private double price;
    private boolean live = false;
    private double discount;
    private Date createddate;
    private String banner;

    //videos
    @OneToMany(mappedBy = "course")
    private List<Video> videos = new ArrayList<>();

    //category relationship
    @ManyToMany
    private List<Category> categoryList = new ArrayList<>();

    public void addCategory(Category category){
        categoryList.add(category);
        category.getCourses().add(this); //this --> current course
    }

    public void removeCategory(Category category){
        categoryList.remove(category);
        category.getCourses().remove(this); //this --> current course
    }

}
