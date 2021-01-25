package com.enigma.spotify.controller;

import com.enigma.spotify.constants.Constant;
import com.enigma.spotify.entity.Artis;
import com.enigma.spotify.service.ArtisService;
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
public class ArtisController {
    @Autowired
    ArtisService artisService;

    @Autowired
    FileUtil fileUtil;

    @GetMapping("/artist")
    public Page<Artis> getAllArtis(@RequestParam(name = "page") Integer page,@RequestParam(name = "size") Integer size){
        Page<Artis> artists=artisService.getAllArtis(PageRequest.of(page,size));
        return artists;
    }

    @GetMapping("/artist/{id}")
    public Artis getArtisById(@PathVariable String id){
        return artisService.getArtisById(id);
    }

    @GetMapping("/artists")
    public Page<Artis> getArtisByName(@RequestBody Artis artis,@RequestParam(name = "page") Integer page,@RequestParam(name = "size") Integer size){
        return artisService.getArtisByName(PageRequest.of(page,size),artis);
    }

    @GetMapping("/artist/photo/{path}")
    public ResponseEntity<Resource> getArtisPhoto(@PathVariable String path, HttpServletRequest request){
        Artis artis=artisService.getArtisById(path);
        if (artis==null)throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(Constant.FILENOTFOUNDMESSAGE));

        Resource resource=fileUtil.read(artis.getUuid());
        String contentType=null;
        System.out.println(resource.getFilename());
        try{
            contentType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format(Constant.FILENOTFOUNDMESSAGE));
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"")
                .body(resource);
    }

    @PostMapping("/artist")
    public Artis saveArtis(@RequestPart MultipartFile file,@RequestPart String artis) throws IOException{
        return artisService.addArtis(file,artis);
    }

    @DeleteMapping("/artist/{id}")
    public void deleteArtis(@PathVariable String id){
        artisService.deleteArtis(id);
    }

    @PutMapping("/artist")
    public Artis updateArtis(@RequestPart MultipartFile file,@RequestPart String artis) throws IOException{
        return artisService.addArtis(file,artis);
    }
}
