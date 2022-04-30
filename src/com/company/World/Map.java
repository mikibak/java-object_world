package com.company.World;

import com.company.Organisms.Organism;

import java.util.Random;

public class Map {
    public int sleepBetweenMoves = 0;
    private World world;

    public boolean isInBounds(Point point) {
        if (point.getX() > 0 && point.getY() > 0 && point.getX() < world.getMapSizeX()-1 && point.getY() < world.getMapSizeY()-1) {
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
            new_position.setX(rand.nextInt(world.getMapSizeX()));
            new_position.setY(rand.nextInt(world.getMapSizeY()));
            if (isEmpty(new_position) && isInBounds(new_position)) {
                break;
            }
        }
        return new_position;
    }
}
