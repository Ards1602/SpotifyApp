package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Artis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtisRepository extends JpaRepository<Artis,String> {
    public Page<Artis> findAllByNameContains(String name,Pageable pagable);
    public List<Artis> findAllByNameEquals(String name);
}
