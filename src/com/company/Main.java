package com.company;

import com.company.GUI.App;
import com.company.Organisms.Animals.Human;
import com.company.Organisms.Animals.Wolf;
import com.company.Organisms.Plants.Grass;
import com.company.World.World;
import com.company.World.Point;

public class Main {

    public static void main(String[] args) {
        World world = new World();
        App app = new App(world);
        app.createWindow();
    }
}
