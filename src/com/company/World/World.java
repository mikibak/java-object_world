package com.company.World;

import com.company.Organisms.Organism;
import java.util.ArrayList;
import java.util.ListIterator;

public class World {

    private ArrayList<Organism> organismArray;
    private int mapSizeX;
    private int mapSizeY;
    private String logs;
    private int turn;
    private Map map;

    public World(int x, int y) {
        this.mapSizeX = x;
        this.mapSizeY = y;
    }

    public int getMapSizeX() {
        return mapSizeX;
    }
    public int getMapSizeY() {
        return mapSizeY;
    }
    public int getNumberOfOrganisms() {
        return organismArray.size();
    }
    Organism getOneOrganism(int index) {
        return null;
    }
    void add_organism(Organism organism) {
        ListIterator<Organism> iterator = organismArray.listIterator();
        boolean added = false;
        for (Organism o : organismArray) {
            if (organism.getInitiative() > o.getInitiative()) {
                iterator.add(organism);
                added = true;
                break;
            }
            iterator.next();
        }
        if (added == false) {
            organismArray.add(organism);
        }
    }
    void add_multiple(int number_of_each_species) {

    }
    void remove_organism(Organism organism) {
        ListIterator<Organism> iterator = organismArray.listIterator();
        for (Organism o : organismArray) {
            if (o == organism) {
                iterator.remove();
                break;
            }
            iterator.next();
        }
    }
    public ArrayList<Organism> getOrganismArray() {
        return organismArray;
    }

}
