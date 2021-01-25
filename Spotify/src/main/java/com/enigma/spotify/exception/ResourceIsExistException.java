package com.enigma.spotify.exception;


import com.enigma.spotify.constants.Constant;

public class ResourceIsExistException extends RuntimeException{

    public ResourceIsExistException(String name){
        super(String.format(Constant.RESOURCEISEXIST,name));
    }
}
