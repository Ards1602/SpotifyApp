package com.enigma.spotify.controller;

import com.enigma.spotify.entity.Genre;
import com.enigma.spotify.service.GenreService;
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
class GenreControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GenreService genreServiceDB;

    @Autowired
    ObjectMapper objectMapper;

//    @Test
//    void saveGenre_shouldResponse_OK200() throws Exception {
//        Genre genre=new Genre("Rock");
//        RequestBuilder requestBuilder= MockMvcRequestBuilders.post("/genre")
//                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(genre));
//        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
//    }

    @Test
    void deleteGenre_should_response_OK200() throws Exception {
        Genre genre=new Genre("Rock");
        RequestBuilder requestBuilder= MockMvcRequestBuilders.delete("/genre/4");
        mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }
}