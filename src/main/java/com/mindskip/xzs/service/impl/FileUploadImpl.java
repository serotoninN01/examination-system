package com.mindskip.xzs.service.impl;

import com.mindskip.xzs.configuration.property.MinioConfig;
import com.mindskip.xzs.service.FileUpload;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class FileUploadImpl implements FileUpload {
    private final Logger logger = LoggerFactory.getLogger(FileUpload.class);
    private final MinioConfig minioConfig;
    private final MinioClient minioClient;

    @Autowired
    public FileUploadImpl(MinioConfig minioConfig, MinioClient minioClient) {
        this.minioConfig = minioConfig;
        this.minioClient = minioClient;
    }

    @Override
    public String uploadFile(InputStream inputStream, long size, String extName) {
        try {
            String objectName = "uploads/" + System.currentTimeMillis() + extName;
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioConfig.getBucketName())
                    .object(objectName)
                    .stream(inputStream, size, -1)
                    .build());
            return minioConfig.getUrl() + "/" + minioConfig.getBucketName() + "/" + objectName;
        } catch (MinioException e) {
            logger.error("Error occurred while uploading file to MinIO: {}", e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage(), e);
        }
        return null;
    }


}
