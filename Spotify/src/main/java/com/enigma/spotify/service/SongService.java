package com.enigma.spotify.service;

import com.enigma.spotify.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SongService {
    public Song addSong(Song song);
    public void deleteSong(String id);
    public Page<Song> getAllSong(Pageable pageable);
    public Song getSongById(String id);
    public Page<Song> getSongByField(Pageable pageable, Song song);
    public String convertDuration(Integer second);
}
