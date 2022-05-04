package com.company.Organisms.Animals;

import com.company.Organisms.Animal;
import com.company.Organisms.Organism;
import com.company.World.Point;
import com.company.World.World;

import javax.swing.*;

public class Human extends Animal {
    public Human(Point position, World world) {
        super(position, "Human", 4, 5, 0, world, "src\\com\\company\\img\\human.png");
    }
    @Override
    public Point action() {
        char c = world.getKeyInput();
        System.out.println(c);
        return world.getMap().pickPointNearby(this.getPosition());
    }
    //TODO Alzur's shield
    //@Override
    //public String collision(Organism guest) {
    //}
}
