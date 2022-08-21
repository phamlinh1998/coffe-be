package com.example.coffeebe.app.controllers;

import com.example.coffeebe.app.dtos.responses.FileResponse;
import com.example.coffeebe.domain.services.impl.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FileController {

    @Autowired
    FileStorageService fileStorageService;

    @PostMapping
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file){
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = "/resources/" +fileName;

        return new FileResponse(fileName, fileDownloadUri);
    }

}
