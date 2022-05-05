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
        world.setSpecialEffectCooldown(world.getSpecialEffectCooldown()-1);

        char c = world.getKeyInput();
        System.out.println(c);
        if(c == 'k') {
            if(world.getSpecialEffectCooldown()==0 || world.getSpecialEffectCooldown()>=5) {
                world.setSpecialEffectCooldown(10);
            } else {
                System.out.println("Wait for the cooldown!");
            }
            return this.getPosition();
        } else {
            return world.getMap().pickPointNearby(this.getPosition(),c);
        }
    }
    @Override
    public String collision(Organism guest) {
    //deflects an attack of any animal if Alzur's shield abillity is active
        if (world.getSpecialEffectCooldown() >= 5) {
            return this.getName() + " deflected a " + guest.getName() + " using Alzur's shield on x=" + this.getPosition().getX() + ", y=" + this.getPosition().getY() + "; ";
        }
        else {
            return super.collision(guest);
        }
    }
}
