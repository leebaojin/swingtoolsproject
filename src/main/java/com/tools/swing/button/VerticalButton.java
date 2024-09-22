package com.tools.swing.button;

import javax.swing.*;
import java.awt.*;

public class VerticalButton extends JButton {

    private TempButton template;
    private boolean clockwise;

    public VerticalButton(String text, boolean clockwise) {
        template = new TempButton(text);
        this.clockwise = clockwise;

        Dimension d = template.getPreferredSize();
        setPreferredSize(new Dimension(d.height, d.width));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        Dimension d = getSize();
        template.setSize(d.height, d.width);

        if (clockwise) {
            g2.rotate(Math.PI / 2.0);
            g2.translate(0, -getSize().width);
        } else {
            g2.translate(0, getSize().height);
            g2.rotate(- Math.PI / 2.0);
        }
        template.setSelected(this.getModel().isPressed());
        template.paintComponent(g2);
        g2.dispose();
    }

    private class TempButton extends JButton {
        private TempButton(String text) {
            super(text);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
        }
    }
}
