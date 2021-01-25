package com.enigma.spotify.service;

import com.enigma.spotify.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreService {
    public Genre addGenre(Genre genre);
    public void deleteGenre(Integer id);
    public Page<Genre> getAllGenre(Pageable pageable);
    public Genre getGenreById(Integer id);
    public Page<Genre> getGenreByField(Pageable pageable, Genre genre);

}
