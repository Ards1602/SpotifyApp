package com.enigma.spotify.service.impl;

import com.enigma.spotify.constants.Constant;
import com.enigma.spotify.entity.Album;
import com.enigma.spotify.exception.ResourceIsExistException;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.AlbumRepository;
import com.enigma.spotify.service.AlbumService;
import com.enigma.spotify.service.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Service
public class AlbumServiceDBImpl implements AlbumService {
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FileUtil fileUtil;

    @Override
    public Album addAlbum(MultipartFile file, String requestBody) throws IOException {
        List<Album> albums=albumRepository.findAllByTitleEquals(objectMapper.readValue(requestBody,Album.class).getTitle());
        Album album=objectMapper.readValue(requestBody,Album.class);
        if (albums.size()==0) {
            album=albumRepository.save(album);
            album.setImage(String.format(Constant.PATHALBUM, fileUtil.upload(album.getId(), file)));
            albumRepository.save(album);
        }else throw new ResourceIsExistException(album.getTitle());
        return album;
    }

    @Override
    public void deleteAlbum(String id) {
        if (albumRepository.findById(id).isPresent()) {
            albumRepository.deleteById(id);
        }else throw new ResourceNotFoundException(id);
    }

    @Override
    public Page<Album> getAllAlbum(Pageable pageable) {
        return albumRepository.findAll(pageable);
    }

    @Override
    public Album getAlbumById(String id) {
        Album album=null;
        if (albumRepository.findById(id).isPresent()){
            return albumRepository.findById(id).get();
        }else throw new ResourceNotFoundException(id);
    }

    @Override
    public Page<Album> getAlbumByField(Pageable pageable, Album album) {
        return albumRepository.findAllByTitleContainsOrReleaseYearEquals(album.getTitle(),album.getReleaseYear(),pageable);
    }
}
