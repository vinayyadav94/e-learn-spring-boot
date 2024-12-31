package com.elearn.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    private String id;
    private String title;
    private String shortDesc;
    private String longDesc;
    private double price;
    private boolean live = false;
    private double discount;
    private Date createddate;
    private String banner;

    //videos
    private List<VideoDto> videos = new ArrayList<>();

    //category relationship
    private List<CategoryDto> categoryList = new ArrayList<>();

}
