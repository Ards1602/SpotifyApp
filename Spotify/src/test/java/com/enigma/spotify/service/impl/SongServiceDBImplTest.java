package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Song;
import com.enigma.spotify.exception.ResourceIsExistException;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.SongRepository;
import com.enigma.spotify.service.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SongServiceDBImplTest {

    @Autowired
    SongRepository songRepository;

    @Autowired
    SongService songServiceDB;

    @BeforeEach
    public void cleanUp(){
        songRepository.deleteAll();
    }

    @Test
    void saveSong_shouldAdd_1Data_inDB_whenSongSaved() {
        Song song=new Song("7-Elements",2003,420,42000.0);
        songServiceDB.addSong(song);
        assertEquals(1,songRepository.findAll().size());
    }

    @Test
    void saveSong_shouldThrowException_whenSongIsExist() {
        Song song=new Song("7-Elements",2003,420,42000.0);
        songRepository.save(song);
        assertThrows(ResourceIsExistException.class,()->{
           songServiceDB.addSong(song);
        });
    }

    @Test
    void deleteSong_shouldDelete_1Data_inDB_whenSongDeleted() {
        Song song=new Song("7-Elements",2003,420,42000.0);
        Song song2=new Song("Smile",2002,420,44000.0);
        songRepository.save(song);
        songRepository.save(song2);
        songServiceDB.deleteSong(song2.getId());
        assertEquals(1,songRepository.findAll().size());
    }

    @Test
    void getAllSong_shouldBe_2InDB_whenDataInDBIs_2() {
        Song song=new Song("7-Elements",2003,420,42000.0);
        Song song2=new Song("Smile",2002,420,44000.0);
        songRepository.save(song);
        songRepository.save(song2);
        assertEquals(2, songServiceDB.getAllSong(PageRequest.of(0,5)).getTotalElements());
    }

    @Test
    void getSongById_shouldGetSong_whenGivenCorrectId() {
        Song song=new Song("7-Elements",2003,420,42000.0);
        Song song2=new Song("Smile",2002,420,44000.0);
        song2.setLength(songServiceDB.convertDuration(song2.getDuration()));
        songRepository.save(song);
        songRepository.save(song2);
        assertEquals(song2, songServiceDB.getSongById(song2.getId()));
    }

    @Test
    void getSongById_shouldThrowException_whenGivenIncorrectId() {
        assertThrows(ResourceNotFoundException.class,()->{
            songServiceDB.getSongById("asdasd");
                });
    }

    @Test
    void getSongByField_shouldGetSong_whenGivenSearchValue() {
        Song song=new Song("7-Elements",2003,420,42000.0);
        Song song2=new Song("Smile",2002,420,44000.0);
        songRepository.save(song);
        songRepository.save(song2);
        Song song3=new Song();
        song3.setTitle("le");
        song3.setReleaseYear(2002);
        song3.setDuration(420);
        assertEquals(2, songServiceDB.getSongByField(PageRequest.of(0,5),song3).getTotalElements());
    }

    @Test
    void convertDuration_shouldBeConvertDuration_whenGivenTime() {
        String expectResult="1 Minute,0 Second";
        String actual= songServiceDB.convertDuration(60);
        assertEquals(expectResult,actual);
    }
}