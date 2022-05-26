package com.example.AppRest.controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.AppRest.DAO.DAOS3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class S3Controller implements DAOS3 {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Override
    public void uploadFile(long id, MultipartFile file) {
        File mainFile = new File(file.getOriginalFilename());
        try (FileOutputStream stream = new FileOutputStream(mainFile)) {
            stream.write(file.getBytes());
            String newFileName = id + "_" + mainFile.getName();
            PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, mainFile).withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getLinkFromS3(long id, String fileName) {
        URL url = amazonS3.getUrl(bucketName, id + "_" + fileName);
        String sharableLink = url.toExternalForm();
        return sharableLink;
    }
}