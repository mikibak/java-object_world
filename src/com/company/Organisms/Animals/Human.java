package com.company.Organisms.Animals;

import com.company.Organisms.Animal;
import com.company.World.Point;
import com.company.World.World;

import javax.swing.*;

public class Human extends Animal {
    public Human(Point position, World world) {
        super(position, "Human", 4, 5, 0, world, "src\\com\\company\\img\\human.bmp");
    }
    @Override
    public Point action() {
        return world.getMap().pickPointNearby(this.getPosition());
    }
}
