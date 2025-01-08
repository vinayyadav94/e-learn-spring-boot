package com.elearn.app.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String save(MultipartFile file, String outputPath) throws IOException {
        
        Path path = Paths.get(outputPath);
        //create output folder if not exists
        Files.createDirectories(path);
        //join filename with path
        Path filePath = Paths.get(path.toString(), file.getOriginalFilename());
        System.out.println(filePath);
        //write files
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return filePath.toString();
    }

}
