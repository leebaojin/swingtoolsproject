package com.tools.swing.smallpanel;

import javax.swing.*;
import javax.swing.plaf.ProgressBarUI;

public class TsArcProgressBar extends JProgressBar {

    private static TsArcProgressBarUI basicUI = new TsArcProgressBarUI();

    static {
        UIManager.put("TsArcProgressBarUI", TsArcProgressBarUI.class.getName());
//        String prefix = UIManager.getLookAndFeel().getID();
//        UIManager.getLookAndFeelDefaults().put("TsArcProgressBarUI")
    }

    public TsArcProgressBar(){
        super();
        setOrientation(JProgressBar.HORIZONTAL);
    }


    @Override
    public void setOrientation(int newOrientation) {

    }

    @Override
    public void updateUI() {
        super.updateUI();
//        setUI((TsArcProgressBarUI)UIManager.getUI(this));
    }

    @Override
    public String getUIClassID() {
        return "TsArcProgressBarUI";
    }
}
