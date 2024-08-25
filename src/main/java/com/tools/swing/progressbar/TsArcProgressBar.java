package com.tools.swing.progressbar;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TsArcProgressBar extends JProgressBar {

    private static TsArcProgressBarUI basicUI = new TsArcProgressBarUI();

    static {
        UIManager.put("TsArcProgressBarUI", TsArcProgressBarUI.class.getName());
        UIManager.put("TsArcProgressBar.Foreground", new Color(146,208,80));
        UIManager.put("TsArcProgressBar.Background", new Color(191,191,191));
//        String prefix = UIManager.getLookAndFeel().getID();
//        UIManager.getLookAndFeelDefaults().put("TsArcProgressBarUI")
    }

    @Getter
    @Setter
    private String label;

    private List<Color> colorData;
    private List<Double> percentageData;

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
