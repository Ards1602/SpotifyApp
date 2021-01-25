package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Playlist;
import com.enigma.spotify.exception.ResourceIsExistException;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.PlaylistRepository;
import com.enigma.spotify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlaylistServiceDBImpl implements PlaylistService {
    @Autowired
    PlaylistRepository playlistRepository;

    @Override
    public Playlist addPlaylist(Playlist playlist) {
        if (playlistRepository.findAllByNameEquals(playlist.getName()).size()==0) {
            return playlistRepository.save(playlist);
        }else throw new ResourceIsExistException(playlist.getName());
    }

    @Override
    public void deletePlaylist(String id) {
        if (playlistRepository.findById(id).isPresent()) {
            playlistRepository.deleteById(id);
        }else throw new ResourceNotFoundException(id);
    }

    @Override
    public Page<Playlist> getAllPlaylist(Pageable pageable) {
        return playlistRepository.findAll(pageable);
    }

    @Override
    public Playlist getPlaylistById(String id) {
        if (playlistRepository.findById(id).isPresent()) {
            return playlistRepository.findById(id).get();
        }else throw new ResourceNotFoundException(id);
    }

    @Override
    public Page<Playlist> getPlaylistByField(Pageable pageable, Playlist playlist) {
        return playlistRepository.findAllByNameContainsOrIsPublicEquals(playlist.getName(),
                playlist.getPublic(),pageable);
    }
}
