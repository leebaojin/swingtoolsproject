package testframe;

import javax.swing.*;
import java.awt.*;

public class TestFrameWithBorder extends JFrame {

    public TestFrameWithBorder(){
        super();
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
    }
}
