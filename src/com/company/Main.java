package com.company;

import com.company.GUI.App;
import com.company.Organisms.Animals.Wolf;
import com.company.World.World;
import com.company.World.Point;

public class Main {

    public static void main(String[] args) {
        World world = new World(20,20);
        App app = new App(world);
        Point p = new Point(5,5);
        Wolf wolf = new Wolf(p,world);
        world.addOrganism(wolf);
        app.createWindow();
    }
}
