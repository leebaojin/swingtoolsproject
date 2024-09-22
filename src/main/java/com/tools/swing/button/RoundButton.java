package com.tools.swing.button;

import javax.swing.*;
import javax.swing.border.Border;

public class RoundButton extends JButton {

    public RoundButton(String name){
        this.setBorder(new RoundedBorder(10));
    }
}
