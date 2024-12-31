package com.elearn.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Video {

    @Id
    private String videoId;
    private String title;
    @Column(name="description", length = 500)
    private String desc;
    private String filePath;
    private String contentType;

    //course
    @ManyToOne
    private Course course;

}
