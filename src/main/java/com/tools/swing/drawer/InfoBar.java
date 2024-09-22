package com.tools.swing.drawer;

import com.tools.swing.button.RoundButton;

import javax.swing.*;
import java.awt.*;

public class InfoBar extends JPanel {

    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;

    private int gap = 3;

    public InfoBar(){
        initUI();
    }

    private void initUI(){

        setLayout(new FlowLayout());
        setBackground(Color.CYAN);
        this.add(Box.createHorizontalStrut(this.gap));

        btn1 = new RoundButton("A");
        this.add(btn1);
        this.add(Box.createHorizontalStrut(this.gap));

        btn2 = new RoundButton("B");
        this.add(btn2);
        this.add(Box.createHorizontalStrut(this.gap));

        btn3 = new RoundButton("C");
        this.add(btn3);
        this.add(Box.createHorizontalStrut(this.gap));

        btn4 = new RoundButton("D");
        this.add(btn4);
        this.add(Box.createHorizontalStrut(this.gap));

        btn5 = new RoundButton("E");
        this.add(btn5);
        this.add(Box.createHorizontalStrut(this.gap));

        btn6 = new RoundButton("F");
        this.add(btn6);
        this.add(Box.createHorizontalStrut(this.gap));

    }


}
