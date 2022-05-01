package com.company.GUI;

import com.company.World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.company.World.Point;

public class App {
    private World world;
    private JFrame frame;
    private JButton showMessageButton;
    private JPanel panel1;
    private JPanel optionsPanel;
    private JPanel gamePanel;
    private JButton nextTurnButton;
    private JPanel logsPanel;

    public App(World world) {
        frame = new JFrame("App");
        this.world = world;
        showMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Hello world!");
            }
        });
        nextTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.playTurn();
                drawMap(frame);
            }
        });
    }
    public void createWindow() {
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.drawMap(frame);
    }
    public void drawMap(JFrame frame) {
        gamePanel.removeAll();
        JPanel[] rows = new JPanel[world.getMap().getMapSizeY()];
        GridBagConstraints gbc = setupGridBag();
        for(int i = 0; i < world.getMap().getMapSizeY(); i++) {
            rows[i] = new JPanel();
            rows[i].setLayout(gamePanel.getLayout());
            gamePanel.add(rows[i],gbc);
            for(int j = 0; j < world.getMap().getMapSizeX(); j++) {
                Point p = new Point(j,i);
                JButton b = world.getMap().addOnPosition(p);
                b.setMinimumSize(new Dimension(30,30));
                rows[i].add(b);
            }
        }
        gamePanel.revalidate();
        gamePanel.repaint();
        frame.pack();
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    private GridBagConstraints setupGridBag() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        return gbc;
    }
}
