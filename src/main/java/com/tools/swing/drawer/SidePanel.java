package com.tools.swing.drawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel {

    private ButtonPanel btnPanel;
    private ListPanel panel;

    private CardLayout layout;

    private Container container;

    public SidePanel(){
        initUI();
    }

    private void initUI(){

        this.layout = new CardLayout();
        this.setLayout(layout);

        btnPanel = new ButtonPanel(layout, this, "p");
        this.add("a",btnPanel);

        panel = new ListPanel(layout, this, "a");
        this.add("p",panel);
    }

    private static class ButtonPanel extends JPanel{
        private JButton btn1;
        private CardLayout layout;

        public ButtonPanel(CardLayout layout, JPanel displayPanel,String display){
            this.setOpaque(false);
            this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

            btn1 = new JButton("s");
            btn1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    layout.show(displayPanel, display);
                }
            });
            this.add(btn1);

        }
    }

    private static class ListPanel extends JPanel{
        private JPanel header;
        private JPanel listObjects;

        private ListPanel(CardLayout card, JPanel displayPanel,String display){
            initUI(card, displayPanel, display);
        }

        private void initUI(CardLayout card, JPanel displayPanel,String display){
            this.header = new HeaderPanel(card, displayPanel, display);
            this.listObjects = new ObjectList();
            this.setBackground(Color.blue);

            GridBagLayout layout = new GridBagLayout();
            this.setLayout(layout);

            GridBagConstraints ctx = new GridBagConstraints();
            ctx.insets = new Insets(1,3,1,3);
            ctx.gridx = 0;
            ctx.gridy = 0;
            ctx.anchor = GridBagConstraints.WEST;
            this.add(this.header, ctx);

            ctx.insets = new Insets(1,3,3,3);
            ctx.gridy = 1;
            ctx.weighty = 1.0;
            ctx.fill = GridBagConstraints.HORIZONTAL;
            this.add(this.listObjects, ctx);
        }
    }

    private static class HeaderPanel extends JPanel{
        private JLabel label;

        private JButton closeBtn;
        private HeaderPanel(CardLayout card, JPanel displayPanel,String display){
            this.setBackground(Color.BLUE);
            this.label = new JLabel("Objects");

            Font font = this.label.getFont();
            Font fontUpdate = new Font(font.getFontName(), font.getStyle(), 10);
            this.label.setFont(fontUpdate);

            closeBtn = new JButton("-");
            closeBtn.setFont(fontUpdate);
            closeBtn.setMargin(new Insets(0,0,0,0));
            closeBtn.setPreferredSize(new Dimension(15,12));
            closeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    card.show(displayPanel, display);
                }
            });

            GridBagLayout layout1 = new GridBagLayout();
            this.setLayout(layout1);

            GridBagConstraints ctx = new GridBagConstraints();
            ctx.insets = new Insets(3,3,3,3);
            ctx.gridx = 0;
            ctx.gridy = 0;
            ctx.weightx = 1.0;
            this.add(this.label, ctx);


            ctx.gridx = 1;
            ctx.weightx = 0;
            ctx.anchor = GridBagConstraints.EAST;
            this.add(this.closeBtn, ctx);
        }
    }



    private static class ObjectList extends JPanel{
        private JList<String> list;
        private DefaultListModel<String> model;
        private ObjectList(){
            model = new DefaultListModel<>();
            model.addElement("Test 1");
            model.addElement("Test 2");
            model.addElement("Test 3");
            model.addElement("Test 4");
            model.addElement("Test 5");
            this.list = new JList<>(model);

//            GridBagLayout layout1 = new GridBagLayout();
//            this.setLayout(layout1);
//
//            GridBagConstraints ctx = new GridBagConstraints();
//            ctx.insets = new Insets(0,0,0,0);
//            ctx.gridx = 0;
//            ctx.gridy = 0;
//            ctx.anchor = GridBagConstraints.WEST;
//            ctx.fill = GridBagConstraints.VERTICAL;
            this.setLayout(new BorderLayout());

            JScrollPane scrollPane = new JScrollPane(this.list);
            scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(5,0));
            scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, 60));
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            this.add(scrollPane);
        }
    }
}
