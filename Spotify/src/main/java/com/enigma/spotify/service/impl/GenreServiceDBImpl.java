package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Genre;
import com.enigma.spotify.exception.ResourceIsExistException;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.GenreRepository;
import com.enigma.spotify.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceDBImpl implements GenreService {
    @Autowired
    GenreRepository genreRepository;


    @Override
    public Genre addGenre(Genre genre) {
        if (genreRepository.findAllByNameEquals(genre.getName()).size()==0) {
            return genreRepository.save(genre);
        }else throw new ResourceIsExistException(genre.getName());
    }

    @Override
    public void deleteGenre(Integer id) {
        if (genreRepository.findById(id).isPresent()) {
            genreRepository.deleteById(id);
        }else throw new ResourceNotFoundException(String.valueOf(id));

    }

    @Override
    public Page<Genre> getAllGenre(Pageable pageable) {
        return genreRepository.findAll(pageable);
    }

    @Override
    public Genre getGenreById(Integer id) {
        Genre genre=null;
        if (genreRepository.findById(id).isPresent()) {
            genre= genreRepository.findById(id).get();
        }else throw new ResourceNotFoundException(String.valueOf(id));
        return genre;
    }

    @Override
    public Page<Genre> getGenreByField(Pageable pageable, Genre genre) {
        return genreRepository.findAllByNameContains(genre.getName(),pageable);
    }
}
