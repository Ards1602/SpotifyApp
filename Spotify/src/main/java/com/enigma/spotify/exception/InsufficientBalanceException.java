package com.enigma.spotify.exception;

import com.enigma.spotify.constants.Constant;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(Double balance){
        super(String.format(Constant.INSUFFICIENTBALANCE,balance));
    }
}
