package com.enigma.spotify.service;

import com.enigma.spotify.entity.Artis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface ArtisService {
    public Artis addArtis(MultipartFile file,String requestBody) throws IOException;
    public void deleteArtis(String id);
    public Page<Artis> getAllArtis(Pageable pageable);
    public Artis getArtisById(String id);
    public Page<Artis> getArtisByName(Pageable pageable, Artis artis);
}
