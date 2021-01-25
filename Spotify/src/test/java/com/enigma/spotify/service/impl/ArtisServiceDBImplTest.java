package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Artis;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.ArtisRepository;
import com.enigma.spotify.service.ArtisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArtisServiceDBImplTest {

    @Autowired
    ArtisService artisServiceDB;

    @Autowired
    ArtisRepository artisRepository;

    @BeforeEach
    public void cleanUp(){
        artisRepository.deleteAll();
    }

    @Test
    void saveArtis_shouldAdd_1Data_inDB_whenArtisSaved() {
    }

    @Test
    void saveArtis_shouldThrowException_whenArtisIsExist() {

    }

    @Test
    void deleteArtis_shouldDelete_1Data_inDB_whenArtisDeleted() {
        Artis artis=new Artis("Maul",2004,"--");
        Artis artis2=new Artis("Luhung",2040,"--");
        artisRepository.save(artis);
        artisRepository.save(artis2);
        artisServiceDB.deleteArtis(artis2.getUuid());
        assertEquals(1,artisRepository.findAll().size());
    }

    @Test
    void getAllArtis_shouldBe_2InDB_whenDataInDBIs_2() {
        Artis artis=new Artis("Maul",2004,"--");
        Artis artis2=new Artis("Luhung",2040,"--");
        artisRepository.save(artis);
        artisRepository.save(artis2);
        assertEquals(2, artisServiceDB.getAllArtis(PageRequest.of(0,5)).getTotalElements());
    }

    @Test
    void getArtisById_shouldGetArtis_whenGivenCorrectId() {
        Artis artis=new Artis("Maul",2004,"--");
        Artis artis2=new Artis("Luhung",2040,"--");
        artisRepository.save(artis);
        artisRepository.save(artis2);
        assertEquals(artis2, artisServiceDB.getArtisById(artis2.getUuid()));
    }

    @Test
    void getArtisById_shouldThrowException_whenGivenIncorrectId() {
        assertThrows(ResourceNotFoundException.class,()->{
            artisServiceDB.getArtisById("asd");
        });
    }

    @Test
    void getArtisByField_shouldGetArtis_whenGivenSearchValue() {
        Artis artis=new Artis("Maul",2004,"--");
        Artis artis2=new Artis("Luhung",2040,"--");
        Artis artis3=new Artis("u",2040,"--");
        artisRepository.save(artis);
        artisRepository.save(artis2);
        assertEquals(2, artisServiceDB.getArtisByName(PageRequest.of(0,5),artis3).getTotalElements());
    }
}