package com.enigma.spotify.service;

import com.enigma.spotify.entity.Playlist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlaylistService {
    public Playlist addPlaylist(Playlist playlist);
    public void deletePlaylist(String id);
    public Page<Playlist> getAllPlaylist(Pageable pageable);
    public Playlist getPlaylistById(String id);
    public Page<Playlist> getPlaylistByField(Pageable pageable, Playlist playlist);
}
