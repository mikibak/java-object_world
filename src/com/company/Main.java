package com.company;

import com.company.GUI.App;
import com.company.Organisms.Animals.Wolf;
import com.company.Organisms.Plants.Grass;
import com.company.World.World;
import com.company.World.Point;

public class Main {

    public static void main(String[] args) {
        World world = new World(20,20);
        App app = new App(world);
        Point p = new Point(5,5);
        Wolf wolf = new Wolf(p,world);
        Grass grass = new Grass(new Point(15,15),world);
        world.addOrganism(wolf);
        world.addOrganism(grass);
        app.createWindow();
    }
}
