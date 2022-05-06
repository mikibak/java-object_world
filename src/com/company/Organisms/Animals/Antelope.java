package com.company.Organisms.Animals;

import com.company.Organisms.Animal;
import com.company.Organisms.Organism;
import com.company.World.Point;
import com.company.World.World;

import java.util.Random;

public class Antelope extends Animal {
    public Antelope(Point position, World world) {
        super(position, "Antelope", 4, 4, 0, world, "src\\com\\company\\img\\antelope.png");
    }

    @Override
    public Point action() {
        Point firstPosition = world.getMap().findRandomPointNearby(this.getPosition());
        return world.getMap().findRandomPointNearby(firstPosition);
    }

    @Override
    public String collision(Organism guest) {
        Random rand = new Random();
        if (rand.nextInt() % 2 == 0 && guest.getName() != this.getName()) {
            Point temp = world.getMap().findEmptyPointNearby(this.getPosition());
            if (!temp.equals(this.getPosition())) {
                guest.setPosition(this.getPosition());
                this.setPosition(temp);
                return this.getName() + " managed to escape from a " + guest.getName() + " on x=" + this.getPosition().getX() + ", y=" + this.getPosition().getY() + "; ";
            }
            else {
                //nowhere to run
                return super.collision(guest);
            }
        }
	    else {
            return super.collision(guest);
        }
    }
}
