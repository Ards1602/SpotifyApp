package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Song;
import com.enigma.spotify.service.SongService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class SongControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SongService songServiceDB;

    @Autowired
    ObjectMapper objectMapper;

//    @Test
//    void saveSong_shouldResponse_OK200() throws Exception {
//        Song song=new Song("7-Elements",2003,420,42000.0);
//        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/song")
//                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(song));
//        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
//    }

    @Test
    void deleteSong_should_response_OK200() throws Exception {
        Song song=new Song("7-Elements",2003,420,42000.0);
        RequestBuilder requestBuilder= MockMvcRequestBuilders.delete("/song/kjij");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

//    @Test
//    void updateSong()throws Exception {
//        Song song=new Song("7-Elements",2003,420,42000.0);
//        song=songServiceDB.addSong(song);
//        song.setTitle("asd");
//        RequestBuilder requestBuilder= MockMvcRequestBuilders.put("/song")
//                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(song));
//        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
//
//    }
}