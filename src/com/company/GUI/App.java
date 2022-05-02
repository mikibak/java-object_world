package com.company.GUI;

import com.company.GUI.Components.HexagonalButton;
import com.company.GUI.Components.HexagonalLayout;
import com.company.World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.company.World.Point;

public class App {
    private World world;
    private JFrame frame;
    private JPanel panel1;
    private JPanel optionsPanel;
    private JPanel gamePanel;
    private JButton nextTurnButton;
    private JPanel logsPanel;

    public App(World world) {
        frame = new JFrame("App");
        this.world = world;
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
        frame.setVisible(true);
        this.drawHexMap(frame);
        frame.pack();
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
        this.writeLogs(frame);
    }
    public void drawHexMap(JFrame frame) {
        gamePanel.removeAll();
        gamePanel.setLayout(new HexagonalLayout(world.getMap().getMapSizeX(), new Insets(5, 5, 5, 5), false));
        int numberOfHexes = world.getMap().getMapSizeX() * world.getMap().getMapSizeY() - 1/2 * world.getMap().getMapSizeY();
        for (int i = 0; i < numberOfHexes; i++) {
            HexagonalButton b = new HexagonalButton();
            b.setBackground(Color.blue);
            gamePanel.add(b);
        }
        gamePanel.revalidate();
        gamePanel.repaint();
        this.writeLogs(frame);
    }
    public void writeLogs(JFrame frame) {
        logsPanel.removeAll();
        String logs = world.getLogs();
        logsPanel.add(new JTextArea(logs));
        logsPanel.revalidate();
        logsPanel.repaint();
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
