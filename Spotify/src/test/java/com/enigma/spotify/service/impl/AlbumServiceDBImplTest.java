package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Album;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.AlbumRepository;
import com.enigma.spotify.service.AlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlbumServiceDBImplTest {
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    AlbumService albumServiceDB;

//    @MockBean
//    AlbumService albumService;

    @BeforeEach
    public void cleanUp(){
        albumRepository.deleteAll();
    }

    @Test
    void addAlbum_shouldAdd_1Data_whenAlbumSaved() throws IOException {

    }

    @Test
    void saveAlbum_shouldThrowException_whenAlbumSavedIsExist() {
//        Album album2=new Album("Rock","-",2000,4.4);
//        albumRepository.save(album2);
//        MultipartFile file=null;
//        String album="{""title"":""Rock"",""releaseYear"":2000,""discount"":2.4}";
//        albumServiceDB.addAlbum(file,album);
//        assertThrows(ResourceIsExistException.class,()->{
//            albumServiceDB.addAlbum(file,album2);
//        });
    }

    @Test
    void deleteAlbum_shouldDelete_1Data_whenAlbumDelete() {
        Album album=new Album("Vitas","-",2000,4.4);
        Album album2=new Album("Rock","-",2000,4.4);
        albumRepository.save(album);
        albumRepository.save(album2);
        albumServiceDB.deleteAlbum(album2.getId());
        assertEquals(1,albumRepository.findAll().size());
    }

    @Test
    void getAllAlbum_shouldBe_2InDB_whenDataInDBIs_2() {
        Album album=new Album("Vitas","-",2000,4.4);
        Album album2=new Album("Rock","-",2000,4.4);
        albumRepository.save(album);
        albumRepository.save(album2);
        assertEquals(2,albumServiceDB.getAllAlbum(PageRequest.of(0,5)).getTotalElements());
    }

    @Test
    void getAlbumById_shouldGetAlbum_whenGivenCorrecId() {
        Album album=new Album("Vitas","-",2000,4.4);
        Album album2=new Album("Rock","-",2000,4.4);
        albumRepository.save(album);
        albumRepository.save(album2);
        assertEquals(album2, albumServiceDB.getAlbumById(album2.getId()));
    }

    @Test
    void getAlbumById_shouldThowException_whenGivenIncorrecId() {
        assertThrows(ResourceNotFoundException.class,()->{
            albumServiceDB.getAlbumById("asdasd");
        });
    }

    @Test
    void getAlbumByField_shouldGetAlbum_whenGivenSearchValue() {
        Album album=new Album("Vitas","-",2000,4.4);
        Album album2=new Album("Rock","-",2000,4.4);
        albumRepository.save(album);
        albumRepository.save(album2);
        Album album3=new Album("tas","",2000,4.4);
        assertEquals(2, albumServiceDB.getAlbumByField(PageRequest.of(0,5),album3).getTotalElements());
    }
}