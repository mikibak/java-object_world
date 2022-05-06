package com.company.Organisms.Plants;

import com.company.Organisms.Plant;
import com.company.World.Point;
import com.company.World.World;

public class Grass extends Plant {
    public Grass(Point position, World world) {
        super(position, "Grass", 0, 0, 0, world, "src\\com\\company\\img\\grass.png");
    }
}