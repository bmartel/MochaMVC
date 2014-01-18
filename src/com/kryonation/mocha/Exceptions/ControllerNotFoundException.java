package com.kryonation.mocha.Exceptions;

/**
 * Created by brandon on 18/01/14.
 */
public class ControllerNotFoundException extends MochaActionException {
    public ControllerNotFoundException(String msg) {
        super(msg);
    }
}
