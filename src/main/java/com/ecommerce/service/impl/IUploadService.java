package com.ecommerce.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.ServletContext;

@Service
@RequiredArgsConstructor
public class IUploadService implements UploadService {
    private final ServletContext servletContext;


    public List<String> handleSaveUploadFile(List<MultipartFile> files, String targetFolder) {
        List<String> rs = new ArrayList<String>();
        if ( files == null || files.isEmpty() ) return rs;

        String relativePath = "src/main/resources/images";
        String rootPath = System.getProperty("user.dir") + File.separator + relativePath;
        String finalName = "";
        try {
            for ( MultipartFile file : files ) {
                byte[] bytes = file.getBytes();

                File dir = new File(rootPath + File.separator + targetFolder);
                if (!dir.exists())
                    dir.mkdirs();

                finalName = System.currentTimeMillis() + "-" + file.getOriginalFilename();

                File serverFile = new File(dir.getAbsolutePath() + File.separator + finalName);

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                rs.add(finalName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }

}