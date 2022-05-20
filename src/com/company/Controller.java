package com.company;

import com.company.GUI.App;
import com.company.World.Maps.HexMap;
import com.company.World.Maps.Map;
import com.company.World.Maps.SquareMap;
import com.company.World.World;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;


public class Controller {
    private final World world;
    private App app;

    public Controller(World world) {
        this.world = world;
        this.app = null;
    }
    //getters and setters
    public void setKeyInput(char keyChar) {
        world.setKeyInput(keyChar);
    }
    public void setApp(App app) {
        this.app = app;
    }
    public Map getMap() {
        return world.getMap();
    }
    public String getLogs() {
        return world.getLogs();
    }

    public void setupNewGame(int sizeX, int sizeY, int setsOfOrganisms, String description) {
        if(Objects.equals(description, "square map")) {
            world.setMap(new SquareMap(sizeX, sizeY, world));
        } else world.setMap(new HexMap(sizeX, sizeY, world));
        world.getOrganismArray().clear();
        world.addMultiple(setsOfOrganisms);
    }

    //listeners
    public ActionListener nextTurn() {
        return e -> {
            world.playTurn();
            app.drawMap();
        };
    }
    public ActionListener save() {
        return e -> {
            try {
                world.save();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
    }
    public ActionListener load() {
        return e -> world.load();
    }

}
