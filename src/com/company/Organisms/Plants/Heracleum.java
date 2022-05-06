package com.company.Organisms.Plants;

import com.company.Organisms.Organism;
import com.company.Organisms.Plant;
import com.company.World.Point;
import com.company.World.World;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Heracleum extends Plant {
    public Heracleum(Point position, World world) {
        super(position, "Heracleum", 0, 10, 0, world, "src\\com\\company\\img\\heracleum.png");
    }

    @Override
    public Point action() {
        ArrayList<Organism> reference = world.getOrganismArray();
        ArrayList<Organism> copied = new ArrayList<>();
        int numberOfOrganisms = reference.size();
        for(int i = 0; i < numberOfOrganisms; i++) {
            copied.add(i, reference.get(i));
        }
        for(int i = 0; i < numberOfOrganisms; i++) {
            Organism other = copied.get(i);
            if (Math.abs(other.getPosition().getX() - this.getPosition().getX()) <= 1 && Math.abs(other.getPosition().getY() - this.getPosition().getY()) <= 1 && other.getName() != this.getName()) {
                //there is an animal too close, it dies
                world.removeOrganism(other);
            }
        }
        return this.getPosition();
    }
}