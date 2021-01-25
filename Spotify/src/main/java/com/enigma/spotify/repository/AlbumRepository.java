package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album,String> {
    public Page<Album> findAllByTitleContainsOrReleaseYearEquals(String title, Integer year, Pageable pagable);
    public List<Album> findAllByTitleEquals(String title);
}
