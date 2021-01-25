package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist,String> {
    public Page<Playlist> findAllByNameContainsOrIsPublicEquals(String name, Boolean active, Pageable pageable);
    public List<Playlist> findAllByNameEquals(String name);
}
