import com.tools.swing.smallpanel.TsTextWButton;
import org.junit.jupiter.api.Test;
import testframe.TestFrame;

import javax.swing.*;
import java.awt.*;

public class TestMain {

    public static void main(String[] args){
        TestFrame testFrame = new TestFrame();
        TsTextWButton btn = new TsTextWButton();
        btn.getButton().setText("OK");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
//        panel.setSize(new Dimension(150, 50));
        panel.setPreferredSize(new Dimension(200, 50));
        panel.add(btn, BorderLayout.CENTER);

        // Create a container JPanel with BoxLayout to center outerPanel
        JPanel containerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints containerGbc = new GridBagConstraints();
        containerGbc.gridx = 0;
        containerGbc.gridy = 0;
        containerGbc.anchor = GridBagConstraints.CENTER;
        containerGbc.weightx = 1.0;
        containerGbc.weighty = 1.0;
        containerPanel.add(panel, containerGbc);



        testFrame.add(containerPanel);

        testFrame.setVisible(true);
    }
}
