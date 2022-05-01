package com.company.Organisms.Plants;

import com.company.Organisms.Animal;
import com.company.Organisms.Plant;
import com.company.World.Point;
import com.company.World.World;

import javax.swing.*;

public class Grass extends Plant {
    public Grass(Point position, World world) {
        super(position, "Grass", 0, 0, 0, world, new ImageIcon("src\\com\\company\\img\\grass.png"));
    }
}