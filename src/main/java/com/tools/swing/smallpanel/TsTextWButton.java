package com.tools.swing.smallpanel;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;


public class TsTextWButton extends JPanel {

    @Getter
    private final JTextField textField;

    @Getter
    private final JButton button;

    @Getter
    private int horizontalSeperation;

    public TsTextWButton(){
        this(new JTextField(), new JButton());
    }

    public TsTextWButton(JTextField textField, JButton button){
        this(textField, button, 6);
    }

    public TsTextWButton(JTextField textField, JButton button, int horizontalSeperation){
        this.textField = textField;
        this.button = button;
        this.horizontalSeperation = horizontalSeperation;
        setConstraints();
    }

    private void setConstraints() {
        GridBagLayout gridBag = new GridBagLayout();
        this.setLayout(gridBag);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
//        c.anchor = GridBagConstraints.LINE_START; //start of the line
//        c.gridwidth = GridBagConstraints.RELATIVE; //fill up all remaining space
        c.insets = new Insets(0,0,0,0);
        this.add(textField, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 0.0;
        c.fill = GridBagConstraints.VERTICAL;
//        c.anchor = GridBagConstraints.LINE_END;
//        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(0,horizontalSeperation,0,0);
        this.add(button, c);
    }



}
