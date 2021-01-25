package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Playlist;
import com.enigma.spotify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlaylistController {
    @Autowired
    PlaylistService playlistService;

    @GetMapping("/playlist")
    public Page<Playlist> getAllArtis(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return playlistService.getAllPlaylist(PageRequest.of(page,size));
    }

    @GetMapping("/playlist/{id}")
    public Playlist getPlaylistById(@PathVariable String id){
        return playlistService.getPlaylistById(id);
    }

    @GetMapping("/playlists")
    public Page<Playlist> getPlaylistByName(@RequestBody Playlist playlist, @RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return playlistService.getPlaylistByField(PageRequest.of(page,size),playlist);
    }

    @PostMapping("/playlist")
    public Playlist savePlaylist(@RequestBody Playlist playlist) {
        return playlistService.addPlaylist(playlist);
    }

    @DeleteMapping("/playlist/{id}")
    public void deletePlaylist(@PathVariable String id){
        playlistService.deletePlaylist(id);
    }

    @PutMapping("/playlist")
    public Playlist updatePlaylist(@RequestBody Playlist playlist) {
        return playlistService.addPlaylist(playlist);
    }

}
