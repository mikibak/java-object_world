package com.company.Organisms.Animals;

import com.company.Organisms.Animal;
import com.company.Organisms.Organism;
import com.company.World.Point;
import com.company.World.World;

import java.util.Random;

public class Turtle extends Animal {
    public Turtle(Point position, World world) {
        super(position, "Turtle", 1, 2, 0, world, "src\\com\\company\\img\\turtle.png");
    }

    @Override
    public Point action() {
        Random rand = new Random();
        if (rand.nextInt() % 4 == 0) {
            return world.getMap().findRandomPointNearby(this.getPosition());
        }
        else {
            return this.getPosition();
        }
    }

    public String collision(Organism guest) {
        if (guest.getPower() < 5) {
            return this.getName() + " deflected a " + guest.getName() + " on x=" + this.getPosition().getX() + ", y=" + this.getPosition().getY() + "; ";
        }
        else return super.collision(guest);
    }
}

