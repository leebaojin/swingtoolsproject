package com.tools.swing.smallpanel;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class TsArcProgressBarUI extends BasicProgressBarUI {

    private double availableAngle = 280;
    private double startAngle = 270 - (360 - availableAngle)/2;
    private double endAngle = 270 + (360 - availableAngle)/2;

    private double innerToOuterRatio = 0.7;
    private int nameSizeReduction = 5;

    @Getter
    @Setter
    private Color successColor = new Color(146,208,80);

    //217, 191
    @Getter
    @Setter
    private Color emptyColor = new Color(191,191,191);

    private JProgressBar progressBar;
    @Override
    public Dimension getPreferredSize(JComponent c) {
        Dimension d = super.getPreferredSize(c);
        int v = Math.max(d.width, d.height);
        d.setSize(v, v);
        return d;
    }

    @Override
    public Dimension getMinimumSize(JComponent c) {
        Dimension d = super.getMinimumSize(c);
        int v = Math.max(d.width, d.height);
        d.setSize(v, v);
        return d;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        if(!(c instanceof JProgressBar)){
            throw new RuntimeException("Invalid use of Progress Bar UI");
        }
        this.progressBar = (JProgressBar) c;

        Insets border = progressBar.getInsets(); // area for border
        int barRectWidth  = progressBar.getWidth()  - border.right - border.left;
        int barRectHeight = progressBar.getHeight() - border.top - border.bottom;
        if (barRectWidth <= 0 || barRectHeight <= 0) {
            return;
        }


        // draw the cells
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(progressBar.getForeground());
        double degree =availableAngle * progressBar.getPercentComplete();
        double size = Math.min(barRectWidth, barRectHeight);
        double centreX = border.left + barRectWidth  * .5;
        double centreY = border.top  + barRectHeight * .5;
        double outerRadius = size * .5;
        double innerRadius = outerRadius * innerToOuterRatio; //or - 20;
        Shape inner = new Ellipse2D.Double(centreX - innerRadius, centreY - innerRadius, innerRadius * 2, innerRadius *2);
        Shape outerSuccess = new Arc2D.Double(
                centreX - outerRadius,
                centreY - outerRadius,
                size,
                size,
                startAngle - degree,
                degree,
                Arc2D.PIE
        );

        double remainingDegree = availableAngle - degree;

        Shape outerNone = new Arc2D.Double(
                centreX - outerRadius,
                centreY - outerRadius,
                size,
                size,
                endAngle,
                remainingDegree,
                Arc2D.PIE
        );

        Area areaSuccess = new Area(outerSuccess);
        areaSuccess.subtract(new Area(inner));

        Area areaNone = new Area(outerNone);
        areaNone.subtract(new Area(inner));

        g2.setColor(successColor);
        g2.fill(areaSuccess);

        g2.setColor(emptyColor);
        g2.fill(areaNone);
        g2.dispose();

        // Deal with possible text painting
        if (progressBar.isStringPainted()) {
            paintString(g, border.left, border.top, barRectWidth, barRectHeight, 0, border);
            paintName(g, centreX, centreY, innerRadius, outerRadius, startAngle, border);
        }
    }

    protected void paintName(Graphics g, double centreX, double centreY, double innerRadius, double outerRadius, double initialAngle, Insets border){
        double angleFromBottom = 270 - initialAngle;
        double rad = Math.toRadians(angleFromBottom);
        double xlenFromMid = Math.sin(rad)*innerRadius;
        double ylenFromMid = Math.cos(rad)*innerRadius;

        int startX = (int) (centreX - xlenFromMid + 1);
        int startY = (int) (centreY + ylenFromMid);
        int xlen = (int) (2 * xlenFromMid);
        int ylen = (int) (outerRadius - innerRadius);
        paintStringName(g, startX, startY, xlen, ylen, 0, border);
    }

    protected void paintStringName(Graphics g, int x, int y,
                               int width, int height,
                               int amountFull, Insets b) {
        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {

            if (progressBar.getComponentOrientation().isLeftToRight()) {
                if (progressBar.isIndeterminate()) {
                    boxRect = getBox(boxRect);
                    paintStringName(g, x, y, width, height,
                            boxRect.x, boxRect.width, b);
                } else {
                    paintStringName(g, x, y, width, height, x, amountFull, b);
                }
            }
            else {
                paintStringName(g, x, y, width, height, x + width - amountFull,
                        amountFull, b);
            }
        }
        else {
            if (progressBar.isIndeterminate()) {
                boxRect = getBox(boxRect);
                paintStringName(g, x, y, width, height,
                        boxRect.y, boxRect.height, b);
            } else {
                paintStringName(g, x, y, width, height, y + height - amountFull,
                        amountFull, b);
            }
        }
    }

    private void paintStringName(Graphics g, int x, int y, int width, int height,
                             int fillStart, int amountFull, Insets b) {
        if (!(g instanceof Graphics2D)) {
            return;
        }

        Graphics2D g2 = (Graphics2D)g;
        String nameString = progressBar.getName();
        Font font = progressBar.getFont();
        Font updatedFont = font.deriveFont(font.getStyle(),font.getSize() - 5);
        g2.setFont(updatedFont);

        Point renderLocation = getStringPlacementName(g2, nameString,
                x, y, width, height);
        Rectangle oldClip = g2.getClipBounds();

//        g2.setFont(updatedFont);

        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            g2.setColor(getSelectionBackground());

            BasicGraphicsUtils.drawString(progressBar, g2, nameString,
                    renderLocation.x, renderLocation.y);
            g2.setColor(getSelectionForeground());
            g2.clipRect(fillStart, y, amountFull, height);

            BasicGraphicsUtils.drawString(progressBar, g2, nameString,
                    renderLocation.x, renderLocation.y);
        } else { // VERTICAL
            g2.setColor(getSelectionBackground());
            AffineTransform rotate =
                    AffineTransform.getRotateInstance(Math.PI/2);
            g2.setFont(progressBar.getFont().deriveFont(rotate));
            renderLocation = getStringPlacement(g2, nameString,
                    x, y, width, height);
            BasicGraphicsUtils.drawString(progressBar, g2, nameString,
                    renderLocation.x, renderLocation.y);
            g2.setColor(getSelectionForeground());
            g2.clipRect(x, fillStart, width, amountFull);
            BasicGraphicsUtils.drawString(progressBar, g2, nameString,
                    renderLocation.x, renderLocation.y);
        }
        g2.setClip(oldClip);
    }

    protected Point getStringPlacementName(Graphics g, String progressString,
                                       int x,int y,int width,int height) {
        FontMetrics fontSizer = progressBar.getFontMetrics(g.getFont());

        float stringWidth = BasicGraphicsUtils.getStringWidth(progressBar, fontSizer,
                progressString);

        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            return new Point(x + (int)Math.round(width/2.0 - stringWidth/2.0),
                    y + ((height +
                            fontSizer.getAscent() -
                            fontSizer.getLeading() -
                            fontSizer.getDescent()) / 2));
        } else { // VERTICAL
            return new Point(x + ((width - fontSizer.getAscent() +
                    fontSizer.getLeading() + fontSizer.getDescent()) / 2),
                    y + (int)Math.round(height/2.0 - stringWidth/2.0));
        }
    }



    public static TsArcProgressBarUI createUI(JComponent c){
        return new TsArcProgressBarUI();
    }


}
