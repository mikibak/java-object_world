package com.company.Organisms.Animals;

import com.company.Organisms.Animal;
import com.company.World.Point;
import com.company.World.World;

public class Sheep extends Animal {
    public Sheep(Point position, World world) {
        super(position, "Sheep", 4, 4, 0, world, "src\\com\\company\\img\\sheep.png");
    }
}
