package com.company.GUI;

import com.company.GUI.Components.HexagonalButton;
import com.company.GUI.Components.HexagonalLayout;
import com.company.World.Maps.HexMap;
import com.company.World.Maps.SquareMap;
import com.company.World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import com.company.World.Point;

public class App {
    private World world;
    private JFrame frame;
    private JPanel panel1;
    private JPanel optionsPanel;
    private JPanel gamePanel;
    private JMenuItem nextTurnButton;
    private JPanel logsPanel;
    private JMenuBar menuBar;

    public App(World world) {
        frame = new JFrame("App");
        this.world = world;
    }
    public void createWindow() {
        setupMenuBar();
        optionsPanel.add(menuBar);
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
    public void drawMap() {
        gamePanel.removeAll();
        world.getMap().drawMap(frame, gamePanel);
        gamePanel.revalidate();
        gamePanel.repaint();
        this.writeLogs(frame);
        frame.pack();
    }
    public void setupMenuBar() {
        JMenu fileMenu;
        JMenuItem save;
        JMenuItem load;
        JMenu newMenu;
        JMenuItem square;
        JMenuItem hex;
        menuBar = new JMenuBar();

        //file menu
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_A);
        fileMenu.getAccessibleContext().setAccessibleDescription("file menu");
        menuBar.add(fileMenu);

        //save game
        save = new JMenuItem("Save",KeyEvent.VK_T);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        save.getAccessibleContext().setAccessibleDescription("save game");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO reference save function in world
            }
        });
        fileMenu.add(save);

        //load game
        load = new JMenuItem("load",KeyEvent.VK_T);
        load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        load.getAccessibleContext().setAccessibleDescription("load game");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO reference load function in world
            }
        });
        fileMenu.add(load);

        //next turn
        nextTurnButton = new JMenuItem("Next Turn");
        nextTurnButton.setMnemonic(KeyEvent.VK_N);
        nextTurnButton.getAccessibleContext().setAccessibleDescription("Next Turn");
        menuBar.add(nextTurnButton);
        nextTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.playTurn();
                drawMap();
            }
        });

        //start new game
        newMenu = new JMenu("New");
        newMenu.setMnemonic(KeyEvent.VK_A);
        newMenu.getAccessibleContext().setAccessibleDescription("new game menu");
        menuBar.add(newMenu);

        //new square map
        square = new JMenuItem("square map",KeyEvent.VK_T);
        chooseMap(square, "square map");
        newMenu.add(square);

        //new hex map
        hex = new JMenuItem("hexagonal map",KeyEvent.VK_T);
        chooseMap(hex, "hexagonal map");
        newMenu.add(hex);
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
    private void chooseMap(JMenuItem item, String description) {
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        item.getAccessibleContext().setAccessibleDescription(description);
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sizeX = (String)JOptionPane.showInputDialog(
                        null,
                        "Size X:",
                        "Choose size X",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "");
                String sizeY = (String)JOptionPane.showInputDialog(
                        null,
                        "Size Y:",
                        "Choose size Y",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "");
                if(sizeX == "" || sizeY == "" || sizeX == null || sizeY == null) {
                    sizeX = "20";
                    sizeY = "20";
                }
                if(description == "square map") {
                    world.setMap(new SquareMap(Integer.parseInt(sizeX), Integer.parseInt(sizeY), world));
                } else world.setMap(new HexMap(Integer.parseInt(sizeX), Integer.parseInt(sizeY), world));
                drawMap();
            }
        });
    }
}
