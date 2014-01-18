package com.kryonation.mocha.components;

import com.kryonation.mocha.interfaces.DataUpdate;

import javax.swing.JLabel;

/**
 * Created by brandon on 17/01/14.
 */
public class MLabel extends JLabel implements DataUpdate {

    public MLabel(String text){
        super(text);
    }

    @Override
    public void update(Object value) {
        setText(value.toString());
    }

}
