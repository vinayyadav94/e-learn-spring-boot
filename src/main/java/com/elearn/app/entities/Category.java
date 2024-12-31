package com.elearn.app.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="categories")
@Data
public class Category {

    @Id
    private String id;
    private String title;
    @Column(name="description")
    private String desc;
    private Date addedDate;

    //relationship with course
    @ManyToMany(mappedBy = "categoryList")
    private List<Course> courses = new ArrayList<>();

}
