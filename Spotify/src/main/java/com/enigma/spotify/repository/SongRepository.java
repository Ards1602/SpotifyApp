package com.enigma.spotify.repository;

import com.enigma.spotify.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song,String> {
    public Page<Song> findAllByTitleContainsOrReleaseYearEqualsOrDurationEquals(String title, Integer year, Integer duration, Pageable pagable);
    public List<Song> findAllByTitleEquals(String title);
}
