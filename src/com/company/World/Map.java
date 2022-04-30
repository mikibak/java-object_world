package com.company.World;

import com.company.Organisms.Organism;

public class Map {
    private int sleepBetweenMoves = 0;
    private World world;

    public void isInBounds(Point point) {

    }
    public Boolean is_empty(Point point) {
        Boolean occupied = false;
        for (Organism o : world.getOrganismArray()) {
            if (o.getPosition().equals(point)) {
                occupied = true;
                break;
            }
        }
        if (occupied == false) {
            //iterated through all organisms, this position is empty
            return true;
        }
        else {
            return false;
        }
    }
}
