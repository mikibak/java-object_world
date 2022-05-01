package com.company.World.Maps;

import com.company.Organisms.Organism;
import com.company.World.Point;
import com.company.World.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class SquareMap extends Map{
    public SquareMap(int x, int y, World world) {
        super(x,y,world);
    }
    public Point findRandomPointNearby(Point point) {
        Point newPosition = new Point();
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
    public Point findEmptyPointNearby(Point point) {
        for (int j = 0; j < 100; j++) {
            //try 50 times, if it fails return itself
            Point newPosition = findRandomPointNearby(point);
            if (isEmpty(newPosition)) {
                return newPosition;
            }
        }
        return point;
    }
    public JButton createButton(Organism organism) {
        if(organism == null) {
            JButton button = new JButton("");
            button.setPreferredSize(new Dimension(40, 40));
            return button;
        }
        JButton b = new JButton(organism.getImage());
        b.setPreferredSize(new Dimension(40, 40));
        b.addActionListener((ActionEvent event) -> {
            JOptionPane.showMessageDialog(null,"Power: " + organism.getPower() + "\nInitiative: " + organism.getInitiative());
        });
        return b;
    }
}
