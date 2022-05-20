package com.company;

import com.company.GUI.App;
import com.company.World.World;

public class Main {

    public static void main(String[] args) {
        World world = new World();
        Controller controller = new Controller(world);
        App app = new App(controller);
        controller.setApp(app);
        app.createWindow();
    }
}
