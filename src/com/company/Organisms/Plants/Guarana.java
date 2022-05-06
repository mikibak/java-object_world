package com.company.Organisms.Plants;

import com.company.Organisms.Organism;
import com.company.Organisms.Plant;
import com.company.World.Point;
import com.company.World.World;

public class Guarana extends Plant {
    private int strengthAdded;
    public Guarana(Point position, World world) {
        super(position, "Guarana", 0, 0, 0, world, "src\\com\\company\\img\\guarana.png");
        this.strengthAdded = 3;
    }
    @Override
    public String collision(Organism guest) {
        guest.setPosition(this.getPosition());
        String strength = Integer.toString(strengthAdded);
        guest.setPower(guest.getPower() + strengthAdded);
        world.removeOrganism(this);
        return guest.getName() + " ate a " + this.getName() + " and gained " + strength + " additional strength on x=" + this.getPosition().getX() + ", y=" + this.getPosition().getY() + "; ";
    }
}
