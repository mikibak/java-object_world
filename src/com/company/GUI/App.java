package com.company.GUI;

import com.company.World.World;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private World world;
    private JButton showMessageButton;
    private JPanel panel1;
    private JPanel optionsPanel;
    private JPanel gamePanel;
    private JPanel logsPanel;
    private JButton button1;

    public App(World world) {
        this.world = world;
        showMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Hello world!");
            }
        });
    }

    public void createWindow() {
        JFrame frame = new JFrame("App");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.drawMap(frame);
    }

    public void drawMap(JFrame frame) {
        for(int i = 0; i < world.getMapSizeY(); i++) {
            for(int j = 0; j < world.getMapSizeY(); j++) {
                //optionsPanel.add(button);
                optionsPanel.add(new JTextField("Bla"));
            }
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}