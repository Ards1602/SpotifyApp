package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Song;
import com.enigma.spotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class SongController {
    @Autowired
    SongService songService;

    @GetMapping("/song")
    public Page<Song> getAllArtis(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return songService.getAllSong(PageRequest.of(page,size));
    }

    @GetMapping("/song/{id}")
    public Song getSongById(@PathVariable String id){
        return songService.getSongById(id);
    }

    @GetMapping("/songs")
    public Page<Song> getSongByField(@RequestBody Song song, @RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return songService.getSongByField(PageRequest.of(page,size),song);
    }

    @PostMapping("/song")
    public Song saveSong(@RequestBody Song song) {
        return songService.addSong(song);
    }

    @DeleteMapping("/song/{id}")
    public void deleteSong(@PathVariable String id){
        songService.deleteSong(id);
    }

    @PutMapping("/song")
    public Song updateSong(@RequestBody Song song) {
        return songService.addSong(song);
    }
}
