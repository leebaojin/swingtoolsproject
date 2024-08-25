import com.tools.swing.progressbar.TsArcProgressBar;
import com.tools.swing.progressbar.TsArcProgressBarUI;
import com.tools.swing.smallpanel.TsTextWButton;
import testframe.TestFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JProgressBar progress = new TsArcProgressBar();
        // use JProgressBar#setUI(...) method
        progress.setUI(new TsArcProgressBarUI());
        progress.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        progress.setStringPainted(true);
        progress.setFont(progress.getFont().deriveFont(16f));
        progress.setValue(50);
        progress.setPreferredSize(new Dimension(80,80));
        progress.setForeground(Color.ORANGE);
        progress.setName("XXXXX");

        JProgressBar progress2 = new TsArcProgressBar();
        // use JProgressBar#setUI(...) method
        progress2.setUI(new TsArcProgressBarUI());
        progress2.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        progress2.setStringPainted(true);
        progress2.setFont(progress.getFont().deriveFont(16f));
        progress2.setValue(50);
        progress2.setPreferredSize(new Dimension(80,80));
        progress2.setForeground(Color.ORANGE);
        progress2.setName("xxx");


        ActionListener listener = createActionForBtn(progress, btn.getTextField());
        btn.getButton().addActionListener(listener);

        // Create a container JPanel with BoxLayout to center outerPanel
        JPanel containerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints containerGbc = new GridBagConstraints();
        containerGbc.gridx = 0;
        containerGbc.gridy = 0;
        containerGbc.insets = new Insets(3,0,3,0);
        containerGbc.anchor = GridBagConstraints.CENTER;
        containerGbc.weightx = 1.0;
        containerGbc.weighty = 0.3;
        containerGbc.gridwidth = GridBagConstraints.REMAINDER;
        containerPanel.add(panel, containerGbc);
        containerGbc.gridx = 0;
        containerGbc.gridy = 1;
        containerGbc.weighty = 0.7;
        containerGbc.gridwidth = 1;
        containerPanel.add(progress, containerGbc);
        containerGbc.gridx = 1;
        containerGbc.gridy = 1;
        containerGbc.weighty = 0.7;
        containerPanel.add(progress2, containerGbc);



        testFrame.add(containerPanel);

        testFrame.setVisible(true);
    }

    private static ActionListener createActionForBtn(JProgressBar progressBar, JTextField field){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String val = field.getText();
                if(val == null || val.isEmpty()){
                    return;
                }
                try {
                    int progressVal = Integer.parseInt(val);
                    if(progressVal >= 0 && progressVal <= 100){
                        progressBar.setValue(progressVal);
                    }

                } catch (NumberFormatException ex){

                } finally {
                    field.setText("");

                }
                SwingUtilities.invokeLater(() -> {
                    progressBar.repaint();
                    field.repaint();
                });

            }
        };
    }
}
