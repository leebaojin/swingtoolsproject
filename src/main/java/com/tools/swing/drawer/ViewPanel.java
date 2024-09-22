package com.tools.swing.drawer;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {

    private InfoBar infoBar;
    private LowerPanel lowerPanel;
    private SidePanel sidePanel;

    public ViewPanel(){
        initUI();
    }

    private void initUI(){
        this.setOpaque(false);

        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        GridBagConstraints ctx = new GridBagConstraints();
        ctx.gridx = 0;
        ctx.gridy = 0;
        ctx.insets = new Insets(3,3,3,3);

        infoBar = new InfoBar();
        this.add(infoBar,ctx);

        ctx.gridy = 1;
        ctx.weighty = 1.0;
        ctx.anchor = GridBagConstraints.NORTH;
        lowerPanel = new LowerPanel();
        this.add(lowerPanel, ctx);

        ctx.gridx = 1;
        ctx.gridy = 0;
        ctx.gridheight = 2;
        ctx.anchor = GridBagConstraints.NORTHWEST;
        sidePanel = new SidePanel();
        this.add(sidePanel, ctx);

    }
}
