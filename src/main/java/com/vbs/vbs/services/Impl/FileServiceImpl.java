package com.vbs.vbs.services.Impl;

import com.vbs.vbs.repo.VenueRepo;
import com.vbs.vbs.services.FileService;
import com.vbs.vbs.utils.FileStorageUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl  implements FileService {

    private final FileStorageUtils fileStorageUtils;
    private final VenueRepo venueRepo;

    public FileServiceImpl(FileStorageUtils fileStorageUtils, VenueRepo venueRepo) {
        this.fileStorageUtils = fileStorageUtils;
        this.venueRepo = venueRepo;
    }

    @Override
    public String uploadImage(String email, MultipartFile file) throws IOException {
//        need to save this file
        String filepath=fileStorageUtils.storeFile(file);
        venueRepo.updateVenueImage(email,filepath);
        return filepath;
    }
}
