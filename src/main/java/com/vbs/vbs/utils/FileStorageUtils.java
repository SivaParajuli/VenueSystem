package com.vbs.vbs.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileStorageUtils {

    @Value("${venue.file.storage.directory}")
    private String venueFileStoragePath;

    //it gives users home
    private final String userHome = System.getProperty("user.home");



    /**
     * This function takes multipart file as input parameter and
     * saves it and then returns the file location.
     *
     * @param multipartFile
     * @return
     */

    public String storeFile(MultipartFile multipartFile) throws IOException {
        String directoryPath = userHome + venueFileStoragePath;
        File directoryFile = new File(directoryPath);
        if (!directoryFile.exists()) {
            directoryFile.mkdirs();
        } else {
            System.out.println("********** file already exists **********");
        }
        String fileStorageLocation = directoryPath + File.separator + UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();
        File fileToSave = new File(fileStorageLocation);
        multipartFile.transferTo(fileToSave);
        return fileStorageLocation;
    }

//    public String getBase64FileFromFilePath(String filePath) {
//        File readingFile = new File(filePath);
//        if (readingFile.exists()) {
//            try {
//                //i will get byte array and convert it to base64
//                byte[] bytes = Files.readAllBytes(readingFile.toPath());
//                String base64String = Base64.getEncoder().encodeToString(bytes);
//                return "data:image/jpeg;base64," + base64String;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
}

