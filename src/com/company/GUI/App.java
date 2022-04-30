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
    private JButton button1;
    private JPanel logsPanel;

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
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
        this.drawMap(frame);
    }

    public void drawMap(JFrame frame) {
        JPanel[] rows = new JPanel[world.getMapSizeY()];
        for(int i = 0; i < 3; i++) {
            rows[i] = new JPanel();
            rows[i].setLayout(gamePanel.getLayout());
            gamePanel.add(rows[i]);
            for(int j = 0; j < 3; j++) {
                JButton b = new JButton("O");
                b.addActionListener((ActionEvent event) -> {
                    JOptionPane.showMessageDialog(null,"Hello world!");
                });
                rows[i].add(b);
            }
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
