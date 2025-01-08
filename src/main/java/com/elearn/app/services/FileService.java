package com.elearn.app.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    //outputPath = path of the folder where file will get saved
    String save(MultipartFile file, String outputPath) throws IOException;

}
