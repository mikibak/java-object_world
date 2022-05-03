package com.company;

import com.company.GUI.App;
import com.company.Organisms.Animals.Human;
import com.company.Organisms.Animals.Wolf;
import com.company.Organisms.Plants.Grass;
import com.company.World.World;
import com.company.World.Point;

public class Main {

    public static void main(String[] args) {
        World world = new World(10,10);
        App app = new App(world);
        Wolf wolf = new Wolf(new Point(5,5),world);
        Grass grass = new Grass(new Point(15,15),world);
        Human human = new Human(new Point(7,7),world);
        world.addOrganism(wolf);
        world.addOrganism(grass);
        world.addOrganism(human);
        app.createWindow();
    }
}
