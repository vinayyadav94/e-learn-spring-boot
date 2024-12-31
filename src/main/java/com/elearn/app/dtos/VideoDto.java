package com.elearn.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {

    private String videoId;
    private String title;
    private String desc;
    private String filePath;
    private String contentType;

}
