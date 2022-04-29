package com.company.World;

import com.company.Organisms.Organism;

import java.util.ArrayList;

public class World {
    private ArrayList<Organism> organismArray;
    private int mapSizeX;
    private int mapSizeY;
    private String logs;
    private int turn;

    public World(int x, int y) {
        this.mapSizeX = x;
        this.mapSizeY = y;
    }
}
