package com.company.Organisms.Plants;

import com.company.Organisms.Plant;
import com.company.World.Point;
import com.company.World.World;

public class Dandelion extends Plant {
    public Dandelion(Point position, World world) {
        super(position, "Dandelion", 0, 0, 0, world, "src\\com\\company\\img\\dandelion.png");
    }

    @Override
    public Point action() {
        if (this.getAge() >= this.getMaturityAge() && this.getAge() <= this.getOldAge()) {
            for (int i = 0; i < 3; i++) {
                Point childsPosition = world.getMap().findEmptyPointNearby(this.getPosition());
                if (!(childsPosition.equals(this.getPosition()))) {
                    createAnyOffspring(world, childsPosition,this.getName());
                }
            }
        }
        return this.getPosition();
    }
}