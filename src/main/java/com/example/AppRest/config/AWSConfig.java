package com.example.AppRest.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {

    @Value("${aws.access_key_id}")
    private String accessKeyId;

    @Value("${aws.secret_access_key}")
    private String accessSecretKey;

    @Value("${aws_session_token}")
    private String SessionToken;

    @Value("${aws.s3.region}")
    private String region;

    @Bean
    public AmazonS3 getS3Client() {
        BasicSessionCredentials sessionCredentials = new BasicSessionCredentials(accessKeyId, accessSecretKey, SessionToken);
        return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(region)).withCredentials(new AWSStaticCredentialsProvider(sessionCredentials)).build();
    }
}
