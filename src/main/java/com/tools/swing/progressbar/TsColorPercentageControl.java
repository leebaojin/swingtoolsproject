package com.tools.swing.progressbar;

import lombok.Getter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TsColorPercentageControl {

    //146, 208, 80
    public static final Color DEFAULT_MAXCOLOR = new Color(105, 179, 7);
    public static final Color DEFAULT_EMPTYCOLOR = new Color(191,191,191);

    private static final Color[] DEFAULT_COLOR_SCHEME = {
      new Color(255, 13, 13), new Color(255, 78, 1), new Color(255, 142, 21),
            new Color(250, 183, 51), new Color(172, 179, 52)
    };

    private static final Double[] DEFAULT_VALUE_SCHEME = {0.2, 0.4, 0.6, 0.8, 1.0};

    private java.util.List<Color> colorData;
    private List<Double> valueData;

    @Getter
    private Color maxColor;

    @Getter
    private Color emptyColor;

    public TsColorPercentageControl(){
        this(false);
    }

    public TsColorPercentageControl(boolean setDefault){
        this.colorData = new ArrayList<>();
        this.valueData = new ArrayList<>();
        this.maxColor = DEFAULT_MAXCOLOR;
        this.emptyColor = DEFAULT_EMPTYCOLOR;
        if(setDefault){
            restoreDefauls();
        }
    }


    public Color getColorWithValue(double val){
        if(valueData.isEmpty()) {
            return maxColor;
        }
        int dataSize = valueData.size();
        for(int i=0; i<dataSize; i++) {
            if(val < valueData.get(i)){
                return colorData.get(i);
            }
        }
        return maxColor;
    }

    public void resetColorData(){
        this.colorData.clear();
        this.valueData.clear();
    }

    public void setMaxColor(Color color){
        if(color != null) {
            this.maxColor = color;
        }
    }

    public void setEmptyColor(Color color){
        if(color != null) {
            this.emptyColor = color;
        }
    }

    public void addColorForValueLessThan(double val, Color color){
        setColorValue(val, color);
    }

    private void restoreDefauls(){
        for(int i=0; i<DEFAULT_VALUE_SCHEME.length; i++){
            setColorValue(DEFAULT_VALUE_SCHEME[i], DEFAULT_COLOR_SCHEME[i]);
        }
    }

    private void setColorValue(double val, Color color){
        if(valueData.isEmpty()) {
            valueData.add(val);
            colorData.add(color);
            return;
        }
        int dataSize = valueData.size();
        for(int i=0; i<dataSize; i++) {
            if(val < valueData.get(i)){
                valueData.add(i, val);
                colorData.add(i, color);
                return;
            } else if (val == valueData.get(i)) {
                colorData.set(i, color);
                return;
            }
        }
        valueData.add(val);
        colorData.add(color);
    }


}
