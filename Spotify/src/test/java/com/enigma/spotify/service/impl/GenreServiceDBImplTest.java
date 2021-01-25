package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Genre;
import com.enigma.spotify.exception.ResourceIsExistException;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.GenreRepository;
import com.enigma.spotify.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GenreServiceDBImplTest {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    GenreService genreService;

    @BeforeEach
    public void cleanUp(){
        genreRepository.deleteAll();
    }

    @Test
    void addGenre_shouldAdd_1Data_inDB_whenGenreSaved() {
        Genre genre=new Genre("Rock");
        genreService.addGenre(genre);
        assertEquals(1,genreRepository.findAll().size());
    }

    @Test
    void saveGenre_shouldThrowException_whenGenreExist() {
        Genre genre=new Genre("Rock");
        genreService.addGenre(genre);
        assertThrows(ResourceIsExistException.class,()->{
            genreService.addGenre(genre);
        });
    }

    @Test
    void deleteGenre_shouldDelete_1Data_inDB_whenGenreDeleted() {
        Genre genre=new Genre("Rock");
        Genre genre2=new Genre("Pop");
        genreRepository.save(genre);
        genreRepository.save(genre2);
        genreService.deleteGenre(genre2.getId());
        assertEquals(1,genreRepository.findAll().size());
    }

    @Test
    void getAllGenre_shouldBe_2InDB_whenDataInDBIs_2() {
        Genre genre=new Genre("Rock");
        Genre genre2=new Genre("Pop");
        genreRepository.save(genre);
        genreRepository.save(genre2);
        assertEquals(2,genreService.getAllGenre(PageRequest.of(0,5)).getTotalElements());
    }

    @Test
    void getGenreById_shouldGetGenre_whenGivenCorrectId() {
        Genre genre=new Genre("Rock");
        Genre genre2=new Genre("Pop");
        genreRepository.save(genre);
        genreRepository.save(genre2);
        assertEquals(genre2,genreService.getGenreById(genre2.getId()));
    }

    @Test
    void getSongById_shouldThrowException_whenGivenIncorrectId() {
        assertThrows(ResourceNotFoundException.class,()->{
            genreService.getGenreById(123);
        });
    }

    @Test
    void getGenreByField_shouldGetGenre_whenGivenSearchValue() {
        Genre genre=new Genre("Rock");
        Genre genre2=new Genre("Pop");
        Genre genre3=new Genre("o");
        genreRepository.save(genre);
        genreRepository.save(genre2);
        assertEquals(2,genreService.getGenreByField(PageRequest.of(0,5),genre3).getTotalElements());
    }
}