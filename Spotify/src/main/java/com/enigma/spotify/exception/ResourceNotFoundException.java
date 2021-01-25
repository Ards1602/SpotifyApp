package com.enigma.spotify.exception;

import com.enigma.spotify.constants.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String id){
        super(String.format(Constant.RESOURCENOTFOUND,id));
    }
}
