package com.company.Organisms.Animals;

import com.company.Organisms.Animal;
import com.company.World.Point;
import com.company.World.World;

import javax.swing.*;

public class Wolf extends Animal {
    public Wolf(Point position, World world) {
        super(position, "Wolf", 5, 9, 0, world, "src\\com\\company\\img\\wolf.bmp");
    }
}
