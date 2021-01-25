package com.enigma.spotify.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUtil {
    public Resource read(String filename);
    public String upload(String id,MultipartFile file) throws IOException;
}
