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

    public App(World world) {
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
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
