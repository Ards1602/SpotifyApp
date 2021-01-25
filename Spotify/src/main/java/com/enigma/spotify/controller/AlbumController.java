package com.enigma.spotify.controller;

import com.enigma.spotify.constants.Constant;
import com.enigma.spotify.entity.Album;
import com.enigma.spotify.service.AlbumService;
import com.enigma.spotify.service.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @Autowired
    FileUtil fileUtil;

    @GetMapping("/album")
    public Page<Album> getAllAlbum(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return albumService.getAllAlbum(PageRequest.of(page,size));
    }

    @GetMapping("/album/{id}")
    public Album getAlbumById(@PathVariable String id){
        return albumService.getAlbumById(id);
    }

    @GetMapping("/albums")
    public Page<Album> getAlbumByField(@RequestBody Album album,@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return albumService.getAlbumByField(PageRequest.of(page,size),album);
    }

    @GetMapping("/album/photo/{path}")
    public ResponseEntity<Resource> getAlbumPhoto(@PathVariable String path, HttpServletRequest request){
        Album album=albumService.getAlbumById(path);
        if (album==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(Constant.FILENOTFOUNDMESSAGE));

        Resource resource=fileUtil.read(album.getId());
        String contentType=null;
        try{
            contentType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException ex){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(Constant.FILENOTFOUNDMESSAGE));
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }

    @PostMapping("/album")
    public Album saveAlbum(@RequestPart MultipartFile file,@RequestPart String album) throws IOException {
        return albumService.addAlbum(file,album);
    }

    @DeleteMapping("/album/{id}")
    public void deleteAlbum(@PathVariable String id){
        albumService.deleteAlbum(id);
    }

    @PutMapping("/album")
    public Album updateAlbum(@RequestPart MultipartFile file,@RequestPart String album) throws IOException {
        return albumService.addAlbum(file,album);
    }
}
