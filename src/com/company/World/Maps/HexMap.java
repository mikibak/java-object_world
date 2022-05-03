package com.company.World.Maps;

import com.company.GUI.Components.HexagonalButton;
import com.company.GUI.Components.HexagonalLayout;
import com.company.Organisms.Organism;
import com.company.World.Point;
import com.company.World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class HexMap extends Map{
    public HexMap(int x, int y, World world) {
        super(x,y,world);
    }
    public com.company.World.Point findRandomPointNearby(com.company.World.Point point) {
        com.company.World.Point newPosition = new com.company.World.Point();
        Random rand = new Random();
        while(true) {
            newPosition.setX((rand.nextInt(3)-1) + point.getX());
            newPosition.setY((rand.nextInt(3)-1) + point.getY());
            if(!newPosition.equals(point) && isInBounds(newPosition)) {
                break;
            }
        }
        return newPosition;
    }
    public com.company.World.Point findEmptyPointNearby(com.company.World.Point point) {
        for (int j = 0; j < 100; j++) {
            //try 50 times, if it fails return itself
            com.company.World.Point newPosition = findRandomPointNearby(point);
            if (isEmpty(newPosition)) {
                return newPosition;
            }
        }
        return point;
    }
    public JButton createButton(Organism organism) {
        if(organism == null) {
            HexagonalButton button = new HexagonalButton();
            button.setBackground(Color.white);
            return button;
        }
        HexagonalButton b = new HexagonalButton(organism.getImage());
        b.addActionListener((ActionEvent event) -> {
            JOptionPane.showMessageDialog(null,"Name: " + organism.getName() + "\nPower: " + organism.getPower() + "\nInitiative: " + organism.getInitiative());
        });
        return b;
    }
    public void drawMap(JFrame frame, JPanel gamePanel) {
        gamePanel.setLayout(new HexagonalLayout(world.getMap().getMapSizeX(), world.getMap().getMapSizeY(), new Insets(2, 2, 2, 2), false));
        int numberOfHexes = world.getMap().getMapSizeX() * world.getMap().getMapSizeY();
        for (int i = 0; i < numberOfHexes; i++) {
            Point position = new Point(i % world.getMap().getMapSizeX(), i / world.getMap().getMapSizeX());
            HexagonalButton b = (HexagonalButton)addOnPosition(position);
            gamePanel.add(b);
        }
    }
}
