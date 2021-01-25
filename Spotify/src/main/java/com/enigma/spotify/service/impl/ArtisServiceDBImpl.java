package com.enigma.spotify.service.impl;

import com.enigma.spotify.constants.Constant;
import com.enigma.spotify.entity.Artis;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.ArtisRepository;
import com.enigma.spotify.service.ArtisService;
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
public class ArtisServiceDBImpl implements ArtisService {
    @Autowired
    ArtisRepository artisRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    FileUtil fileUtil;

    @Override
    public Artis addArtis(MultipartFile file, String requestBody) throws IOException {
        Artis artis=artisRepository.save(objectMapper.readValue(requestBody,Artis.class));
        artis.setPhoto(String.format(Constant.PATHARTIST, fileUtil.upload(artis.getUuid(), file)));
        artisRepository.save(artis);
        return artis;


    }

    @Override
    public void deleteArtis(String id) {
        if (artisRepository.findById(id).isPresent()){
            artisRepository.deleteById(id);
        }else throw new ResourceNotFoundException(id);
    }

    @Override
    public Page<Artis> getAllArtis(Pageable pageable) {
        return artisRepository.findAll(pageable);
    }

    @Override
    public Artis getArtisById(String id) {
        Artis artis=null;
        if (artisRepository.findById(id).isPresent()) {
            artis = artisRepository.findById(id).get();
        }else throw new ResourceNotFoundException(id);
        return artis;
    }

    @Override
    public Page<Artis> getArtisByName(Pageable pageable, Artis artis) {
        return artisRepository.findAllByNameContains(artis.getName(),pageable);
    }
}
