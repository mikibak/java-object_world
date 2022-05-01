package com.company.Organisms;
import com.company.World.Point;
import com.company.World.World;
import com.company.Organisms.Organism;

import javax.swing.*;

public abstract class Plant extends Organism {
    private final int maturityAge = 5;
    private final int oldAge = 6;

    public Plant(Point position, String name, int initiative, int power, int age, World world, ImageIcon image) {
        super(position, name, initiative, power, age, world, image);
    }

    public Point action() {
        if (this.getAge() > this.getMaturityAge() && this.getAge() < this.getOldAge()) {
            Point childsPosition = world.getMap().findEmptyPointNearby(this.getPosition());
            if (!(childsPosition.equals(this.getPosition()))) {
                createAnyOffspring(world, childsPosition,this.getName());
            }
        }
        return this.getPosition();
    }
    public String collision(Organism guest) {
        guest.setPosition(this.getPosition());
        String position_string = "x=" + this.getPosition().getX() + ",y= " + this.getPosition().getY();
        String name = getName();
        String guests_name = guest.getName();
        if (this.getPower() <= guest.getPower()) {
            world.removeOrganism(this);
            return name + " got eaten by a " + guests_name + " on " + position_string + "; ";
        }
	else {
            world.removeOrganism(this);
            world.removeOrganism(guest);
            return guests_name + " ate a poisonous " + name + " and died on " + position_string +  "; ";
        }
    }
}
