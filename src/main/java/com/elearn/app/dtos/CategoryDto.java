package com.elearn.app.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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
    //the below validation will happen in 3 steps.
    //1. add dependency "validation"
    //2. validation apply with annotation on bean
    //3. enable with @Valid annotation in controller
    @NotEmpty(message = "title is required!")
    @Size(min=3, max=50, message = "the title length should between 3 to 50 chars")
    //@Email
    //@Pattern(regexp="")
    private String title;
    private String desc;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/mm/yyyy")
    private Date addedDate;

}
