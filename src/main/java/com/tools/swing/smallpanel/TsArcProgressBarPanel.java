package com.tools.swing.smallpanel;

import javax.swing.*;
import java.awt.*;

public class TsArcProgressBarPanel extends JPanel {

    @Override
    public Dimension getPreferredSize() {
        Dimension d = super.getPreferredSize();
        int v = Math.max(d.width, d.height);
        d.setSize(v, v);
        JProgressBar p;
        return d;
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
    }


}
