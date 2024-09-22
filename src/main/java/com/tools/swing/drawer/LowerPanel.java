package com.tools.swing.drawer;

import javax.swing.*;
import java.awt.*;

public class LowerPanel extends JPanel {

    private JButton btn1;

    public LowerPanel(){
        initUI();
    }

    private void initUI(){
        CardLayout layout = new CardLayout();
        this.setLayout(layout);

        btn1 = new JButton("...");
        this.add("a",btn1);
    }
}
