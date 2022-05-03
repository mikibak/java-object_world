package com.company.Organisms;
import com.company.World.Point;
import com.company.World.World;
import com.company.Organisms.Organism;

import javax.swing.*;

public abstract class Animal extends Organism {
    public Animal(Point position, String name, int initiative, int power, int age, World world, String imagePath) {
        super(position, name, initiative, power, age, world, imagePath);
    }

    public Point action() {
        return world.getMap().findRandomPointNearby(this.getPosition());
    }
    public String collision(Organism guest) {
        if (guest.getName() == this.getName()) {
            if (guest.getAge() > this.getMaturityAge() && this.getAge() > this.getMaturityAge() && guest.getAge() < guest.getOldAge() && this.getAge() < this.getOldAge()) {
                //breeding occurs only when both parents are of correct age
                return breeding(guest);
            }
        }
        else {
            //stronger one eats the weaker one
            if (guest.getPower() >= this.getPower()) {
                //guest eats the host
                guest.setPosition(this.getPosition());
                world.removeOrganism(this);
                return this.getName() + " got eaten by a hungry " + guest.getName() + " on x=" + this.getPosition().getX() + ", y=" + this.getPosition().getY() + "; ";
            }
            else {
                //host eats the guest
                world.removeOrganism(guest);
                return guest.getName() + " walked into a " + this.getName() + " and got eaten on x=" + this.getPosition().getX() + ", y=" + this.getPosition().getY() + "; ";
            }
        }
        return "";
    }
    public String breeding(Organism guest) {
        Point childsPosition = world.getMap().findEmptyPointNearby(getPosition());
        if (childsPosition.equals(this.getPosition()) || childsPosition.equals(guest.getPosition())) {
            //there was no space, the child dies
            //return "A newborn " + name + " died;";
            return "";//avoiding too much text messages
        }
        createAnyOffspring(world, childsPosition, guest.getName());
        return "A new " + getName() + " was born on x = " + childsPosition.getX() + ", y = " + childsPosition.getY() + "; ";
    }
}
