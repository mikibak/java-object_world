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
    public Point findRandomPointNearby(Point point) {
        Point newPosition = new Point();
        Random rand = new Random();
        //TODO fix this shit
        while(true) {
            int randInt = rand.nextInt(6);
            if (randInt == 0) {
                newPosition.setX(point.getX()+1);
            } else if(randInt == 1 || randInt == 2) {
                //does not change
            } else {
                newPosition.setX(point.getX()-1);
            }
            newPosition.setY((rand.nextInt(3)-1) + point.getY());
            if(!newPosition.equals(point) && isInBounds(newPosition)) {
                break;
            }
        }
        return newPosition;
    }
    public JButton createButton(Organism organism) {
        if(organism == null) {
            HexagonalButton button = new HexagonalButton();
            button.setBackground(Color.white);
            return button;
        }
        HexagonalButton b = new HexagonalButton(organism.getImagePath());
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
