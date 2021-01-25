package com.enigma.spotify.exception;

import com.enigma.spotify.constants.Constant;


public class PhotoReachMaxSizeException extends RuntimeException{

    public PhotoReachMaxSizeException(){
        super(String.format(Constant.PHOTOMAXSIZE));
    }
}
