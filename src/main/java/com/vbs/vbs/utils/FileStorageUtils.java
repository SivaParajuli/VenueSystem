package com.vbs.vbs.utils;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;
import java.util.Objects;

@Component
public class FileStorageUtils {

    public FileStorageUtils(){
    }

    public String storeFile(MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        if(fileName.contains("..")){
            throw new IOException("not valid file");
        }
        String image = Base64.getEncoder().encodeToString(multipartFile.getBytes());
        return image;
    }

}

