package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
    public Page<Genre> findAllByNameContains(String name, Pageable pagable);
    public List<Genre> findAllByNameEquals(String name);
}
