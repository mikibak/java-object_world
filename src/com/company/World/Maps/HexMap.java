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
        while(true) {
            int randInt = rand.nextInt(6);
            int x = point.getX();
            int y = point.getY();
            //based on https://www.redblobgames.com/grids/hexagons/
            if(y % 2 == 0) {
                //even row
                switch (randInt) {
                    case 0 -> newPosition.setXY(x - 1, y - 1);
                    case 1 -> newPosition.setXY(x, y - 1);
                    case 2 -> newPosition.setXY(x + 1, y);
                    case 3 -> newPosition.setXY(x, y + 1);
                    case 4 -> newPosition.setXY(x - 1, y + 1);
                    case 5 -> newPosition.setXY(x - 1, y);
                }
            } else {
                //odd row
                switch (randInt) {
                    case 0 -> newPosition.setXY(x, y - 1);
                    case 1 -> newPosition.setXY(x + 1, y - 1);
                    case 2 -> newPosition.setXY(x + 1, y);
                    case 3 -> newPosition.setXY(x + 1, y + 1);
                    case 4 -> newPosition.setXY(x, y + 1);
                    case 5 -> newPosition.setXY(x - 1, y);
                }
            }
            if(!newPosition.equals(point) && isInBounds(newPosition)) {
                break;
            }
        }
        return newPosition;
    }
    public Point pickPointNearby(Point position, char direction) {
        int x = position.getX();
        int y = position.getY();
        Point newPosition = new Point(x, y);
        //based on https://www.redblobgames.com/grids/hexagons/
        if(position.getY() % 2 == 0) {
            //even row
            switch (direction) {
                case 'w' -> newPosition.setXY(x - 1, y - 1);
                case 'e' -> newPosition.setXY(x, y - 1);
                case 'd' -> newPosition.setXY(x + 1, y);
                case 'x' -> newPosition.setXY(x, y + 1);
                case 'z' -> newPosition.setXY(x - 1, y + 1);
                case 'a' -> newPosition.setXY(x - 1, y);
            }
        } else {
            //odd row
            switch (direction) {
                case 'w' -> newPosition.setXY(x, y - 1);
                case 'e' -> newPosition.setXY(x + 1, y - 1);
                case 'd' -> newPosition.setXY(x + 1, y);
                case 'x' -> newPosition.setXY(x + 1, y + 1);
                case 'z' -> newPosition.setXY(x, y + 1);
                case 'a' -> newPosition.setXY(x - 1, y);
            }
        }
        if(isInBounds(newPosition)) {
            return newPosition;
        } else return position;
    }
    public JButton createButton(Organism organism, Point p) {
        if(organism == null) {
            HexagonalButton button = new HexagonalButton();
            createEmptyButton(button,p);
            button.setBackground(Color.white);
            return button;
        }
        HexagonalButton b = new HexagonalButton(organism.getImagePath());
        b.addActionListener((ActionEvent event) -> {
            JOptionPane.showMessageDialog(null,"Name: " + organism.getName() + "\nPower: " + organism.getPower() +
                    "\nInitiative: " + organism.getInitiative() + "\nPosition: " + organism.getPosition().getX() + ", "+ organism.getPosition().getY());
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
