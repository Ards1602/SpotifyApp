package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Genre;
import com.enigma.spotify.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class GenreController {
    @Autowired
    GenreService genreService;

    @GetMapping("/genre")
    public Page<Genre> getAllGenre(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return genreService.getAllGenre(PageRequest.of(page,size));
    }

    @GetMapping("/genre/{id}")
    public Genre getGenreById(@PathVariable Integer id){
        return genreService.getGenreById(id);
    }

    @GetMapping("/genres")
    public Page<Genre> getGenreByField(@RequestBody Genre genre, @RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return genreService.getGenreByField(PageRequest.of(page,size),genre);
    }

    @PostMapping("/genre")
    public Genre saveGenre(@RequestBody Genre genre) {
        return genreService.addGenre(genre);
    }

    @DeleteMapping("/genre/{id}")
    public void deleteGenre(@PathVariable Integer id){
        genreService.deleteGenre(id);
    }

    @PutMapping("/genre")
    public Genre updateGenre(@RequestBody Genre genre) {
        return genreService.addGenre(genre);
    }

}
