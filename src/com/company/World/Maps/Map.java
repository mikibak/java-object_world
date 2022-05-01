package com.company.World.Maps;

import com.company.Organisms.Organism;
import com.company.World.Point;
import com.company.World.World;

import javax.swing.*;
import java.awt.event.ActionEvent;
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
        if (point.getX() > 0 && point.getY() > 0 && point.getX() < world.getMap().getMapSizeX()-1 && point.getY() < world.getMap().getMapSizeY()-1) {
            return true;
        }
        else {
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
        if(!occupied) {
            //iterated through all organisms, this position is empty
            return true;
        }
        else {
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
    public abstract Point findRandomPointNearby(Point point);
    public abstract Point findEmptyPointNearby(Point point);
    public JButton addOnPosition(Point p) {
        JButton b = createButton(null);
        for (Organism o : world.getOrganismArray()) {
            if (o.getPosition().equals(p)) {
                b = createButton(o);
                break;
            }
        }
        return b;
    }
    public abstract JButton createButton(Organism organism);
}
