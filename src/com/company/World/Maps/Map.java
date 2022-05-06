package com.company.World.Maps;

import com.company.Organisms.Organism;
import com.company.World.Point;
import com.company.World.World;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public abstract class Map {
    protected int sleepBetweenMoves = 0;
    protected int mapSizeX;
    protected int mapSizeY;
    protected World world;

    public Map(int x, int y, World world) {
        this.mapSizeX = x;
        this.mapSizeY = y;
        this.world = world;
    }

    public int getMapSizeX() {
        return mapSizeX;
    }

    public int getMapSizeY() {
        return mapSizeY;
    }

    public boolean isInBounds(Point point) {
        if (point.getX() > 0 && point.getY() > 0 && point.getX() < world.getMap().getMapSizeX() - 1 && point.getY() < world.getMap().getMapSizeY() - 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isEmpty(Point point) {
        boolean occupied = false;
        for (Organism o : world.getOrganismArray()) {
            if (o.getPosition().equals(point)) {
                occupied = true;
                break;
            }
        }
        if (occupied == false) {
            //iterated through all organisms, this position is empty
            return true;
        } else {
            return false;
        }
    }

    public Point findRandomEmptyPoint() {
        Point new_position = new Point();
        Random rand = new Random();
        while (true) {
            new_position.setX(rand.nextInt(world.getMap().getMapSizeX()));
            new_position.setY(rand.nextInt(world.getMap().getMapSizeY()));
            if (isEmpty(new_position) && isInBounds(new_position)) {
                break;
            }
        }
        return new_position;
    }

    public Point findEmptyPointNearby(com.company.World.Point point) {
        for (int j = 0; j < 100; j++) {
            //try 50 times, if it fails return itself
            Point newPosition = findRandomPointNearby(point);
            if (isEmpty(newPosition)) {
                return newPosition;
            }
        }
        return point;
    }

    public JButton addOnPosition(Point p) {
        JButton b = createButton(null, p);
        for (Organism o : world.getOrganismArray()) {
            if (o.getPosition().equals(p)) {
                b = createButton(o, p);
                break;
            }
        }
        return b;
    }

    protected void createEmptyButton(JButton button, Point p) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField nameField = new JTextField(5);

                JPanel myPanel = new JPanel();
                myPanel.add(new JLabel("New organism's name:"));
                myPanel.add(nameField);

                int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Which organism would you like to add?", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    Organism o = world.addByName(nameField.getText());
                    o.setPosition(p);
                    world.addOrganism(o);
                }
            }
        });
    }

    public abstract Point findRandomPointNearby(Point point);
    public abstract Point pickPointNearby(Point point, char c);
    public abstract JButton createButton(Organism organism, Point p);
    public abstract void drawMap(JFrame frame, JPanel gamePanel);
}
