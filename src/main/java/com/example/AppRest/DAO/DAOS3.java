package com.example.AppRest.DAO;

import org.springframework.web.multipart.MultipartFile;

public interface DAOS3 {
    void uploadFile(long id, MultipartFile file);
    String getLinkFromS3 (long id, String fileName);
}