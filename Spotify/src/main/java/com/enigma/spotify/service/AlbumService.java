package com.enigma.spotify.service;

import com.enigma.spotify.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AlbumService {
    public Album addAlbum(MultipartFile file,String requestBody) throws IOException;
    public void deleteAlbum(String id);
    public Page<Album> getAllAlbum(Pageable pageable);
    public Album getAlbumById(String id);
    public Page<Album> getAlbumByField(Pageable pageable,Album album);
}
