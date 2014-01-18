package com.kryonation.mocha.components;

import com.kryonation.mocha.interfaces.DataUpdate;

import javax.swing.JTextField;

/**
 * Created by brandon on 17/01/14.
 */
public class MTextField extends JTextField implements DataUpdate {

    public MTextField(int columnSize, boolean editable){
        super();

        if (columnSize > -1) this.setColumns(columnSize);
        this.setEditable(editable);
    }
    @Override
    public void update(Object value) {
        setText(value.toString());
    }
}
