package com.vbs.vbs.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadImage( Integer id , MultipartFile file) throws IOException;
}
