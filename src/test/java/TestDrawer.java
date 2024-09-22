import com.tools.swing.button.RoundButton;
import com.tools.swing.drawer.ViewPanel;
import testframe.TestFrame;
import testframe.TestFrameWithBorder;

import java.awt.*;

public class TestDrawer {

    public static void main(String[] args){
        TestFrameWithBorder testFrame = new TestFrameWithBorder();

//        RoundButton btn = new RoundButton("...");
        ViewPanel viewPanel = new ViewPanel();
        testFrame.add(viewPanel, BorderLayout.CENTER);

        testFrame.setVisible(true);

    }
}
