package com.elearn.app.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String id;
    private String title;
    private String desc;
    private Date addedDate;

}
