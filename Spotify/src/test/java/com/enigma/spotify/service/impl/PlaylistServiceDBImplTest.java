package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Playlist;
import com.enigma.spotify.exception.ResourceIsExistException;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.PlaylistRepository;
import com.enigma.spotify.service.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PlaylistServiceDBImplTest {

    @Autowired
    PlaylistService playlistServiceDB;

    @Autowired
    PlaylistRepository playlistRepository;

    @BeforeEach
    public void cleanUp(){
        playlistRepository.deleteAll();
    }

    @Test
    void addPlaylist_shouldAdd_1Data_inDB_whenPlaylistSaved() {
        Playlist playlist=new Playlist("Rock",Boolean.TRUE);
        playlistServiceDB.addPlaylist(playlist);
        assertEquals(1,playlistRepository.findAll().size());
    }

    @Test
    void addPlaylist_shouldThrowException_whenPlaylistIsExist() {
        Playlist playlist=new Playlist("Rock",Boolean.TRUE);
        playlistServiceDB.addPlaylist(playlist);
        assertThrows(ResourceIsExistException.class,()->{
            playlistServiceDB.addPlaylist(playlist);
        });
    }

    @Test
    void deletePlaylist_shouldDelete_1Data_inDB_whenPlaylistDeleted() {
        Playlist playlist=new Playlist("Rock",Boolean.TRUE);
        Playlist playlist2=new Playlist("Pop",Boolean.TRUE);
        playlistRepository.save(playlist);
        playlistRepository.save(playlist2);
        playlistServiceDB.deletePlaylist(playlist2.getId());
        assertEquals(1,playlistRepository.findAll().size());
    }

    @Test
    void getAllPlaylist_shouldBe_2InDB_whenDataInDBIs_2() {
        Playlist playlist=new Playlist("Rock",Boolean.TRUE);
        Playlist playlist2=new Playlist("Pop",Boolean.TRUE);
        playlistRepository.save(playlist);
        playlistRepository.save(playlist2);
        assertEquals(2,playlistServiceDB.getAllPlaylist(PageRequest.of(0,5)).getTotalElements());
    }

    @Test
    void getPlaylistById_shouldGetPlaylist_whenGivenCorrectId() {
        Playlist playlist=new Playlist("Rock",Boolean.TRUE);
        Playlist playlist2=new Playlist("Pop",Boolean.TRUE);
        playlistRepository.save(playlist);
        playlistRepository.save(playlist2);
        assertEquals(playlist2, playlistServiceDB.getPlaylistById(playlist2.getId()));
    }

    @Test
    void getPlaylistById_shouldThrowException_whenGivenIncorrectId() {
        assertThrows(ResourceNotFoundException.class,()->{
            playlistServiceDB.getPlaylistById("asdasd");
        });
    }

    @Test
    void getPlaylistByField_shouldGetPlaylist_whenGivenSearchValue() {
        Playlist playlist=new Playlist("Rock",Boolean.TRUE);
        Playlist playlist2=new Playlist("Pop",Boolean.TRUE);
        Playlist playlist3=new Playlist("o",Boolean.TRUE);
        playlistRepository.save(playlist);
        playlistRepository.save(playlist2);
        assertEquals(2,playlistServiceDB.getPlaylistByField(PageRequest.of(0,5),playlist3).getTotalElements());
    }
}