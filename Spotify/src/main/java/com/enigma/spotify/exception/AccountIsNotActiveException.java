package com.enigma.spotify.exception;

import com.enigma.spotify.constants.Constant;

public class AccountIsNotActiveException extends RuntimeException{
    public AccountIsNotActiveException(String id){
        super(String.format(Constant.ACCOUNTISNOTACTIVE,id));
    }
}
