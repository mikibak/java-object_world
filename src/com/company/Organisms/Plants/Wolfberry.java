package com.company.Organisms.Plants;

import com.company.Organisms.Plant;
import com.company.World.Point;
import com.company.World.World;

public class Wolfberry extends Plant {
    public Wolfberry(Point position, World world) {
        super(position, "Wolfberry", 0, 99, 0, world, "src\\com\\company\\img\\wolfberry.png");
    }
}