package com.enigma.spotify.service.impl;

import com.enigma.spotify.entity.Song;
import com.enigma.spotify.exception.ResourceIsExistException;
import com.enigma.spotify.exception.ResourceNotFoundException;
import com.enigma.spotify.repository.SongRepository;
import com.enigma.spotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SongServiceDBImpl implements SongService {
    @Autowired
    SongRepository songRepository;

    @Override
    public Song addSong(Song song) {
        if (songRepository.findAllByTitleEquals(song.getTitle()).size()==0) {
            return songRepository.save(song);
        }else throw new ResourceIsExistException(song.getTitle());
    }

    @Override
    public void deleteSong(String id) {
        if (songRepository.findById(id).isPresent()) {
            songRepository.deleteById(id);
        }else throw new ResourceNotFoundException(id);
    }

    @Override
    public Page<Song> getAllSong(Pageable pageable) {
        Page<Song> songs=songRepository.findAll(pageable);
        songs.forEach(song -> song.setLength(convertDuration(song.getDuration())));
        return songs;
    }

    @Override
    public Song getSongById(String id) {
        if (songRepository.findById(id).isPresent()) {
            Song song = songRepository.findById(id).get();
            song.setLength(convertDuration(song.getDuration()));
            return song;
        }else throw new ResourceNotFoundException(id);
    }

    @Override
    public Page<Song> getSongByField(Pageable pageable, Song song) {
        Page<Song> songs= songRepository.findAllByTitleContainsOrReleaseYearEqualsOrDurationEquals(song.getTitle(),song.getReleaseYear()
                ,song.getDuration(),pageable);
        songs.forEach(song1 ->song1.setLength(convertDuration(song1.getDuration())));
        return songs;
    }

    @Override
    public String convertDuration(Integer second) {
        Integer hour,minute,rest;
        hour=second/3600;
        rest=second%3600;
        minute=rest/60;
        rest=rest%60;
        if (hour!=0){
            return hour+" Hour,"+minute+" Minute,"+rest+" Second";
        }else if (minute!=0){
            return minute+" Minute,"+rest+" Second";
        }else {
            return second+" Second";
        }
    }
}
