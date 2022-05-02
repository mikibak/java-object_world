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
            JOptionPane.showMessageDialog(null,"Name: " + organism.getName() + "\nPower: " + organism.getPower() + "\nInitiative: " + organism.getInitiative());
        });
        return b;
    }
    public void drawMap(JFrame frame, JPanel gamePanel) {
        gamePanel.setLayout(new GridBagLayout());
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
    }
    private GridBagConstraints setupGridBag() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        return gbc;
    }
}
