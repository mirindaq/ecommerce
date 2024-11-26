package com.ecommerce.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {
    List<String> handleSaveUploadFile(List<MultipartFile> file, String targetFolder);
}
