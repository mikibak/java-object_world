package com.company;

import com.company.GUI.App;
import com.company.World.World;

public class Main {

    public static void main(String[] args) {
        World world = new World(20,20);
        App app = new App(world);
        app.createWindow();
    }
}
