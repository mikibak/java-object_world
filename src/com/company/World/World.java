package com.company.World;

import com.company.Organisms.Organism;
import com.company.World.Maps.HexMap;
import com.company.World.Maps.Map;
import com.company.World.Maps.SquareMap;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;


public class World {

    private ArrayList<Organism> organismArray;
    private String logs;
    private int turn;
    private Map map;
    private List<Class<Organism>> organismTypes;

    public World(int x, int y) {
        this.organismArray = new ArrayList<Organism>();
        this.organismTypes = this.findAllClassesUsingReflectionsLibrary("com.company.Organisms");
        this.logs = "Game starts now! ";
    }

    //getters
    public int getNumberOfOrganisms() {
        return organismArray.size();
    }
    Organism getOneOrganism(int index) {
        return null;
    }
    public ArrayList<Organism> getOrganismArray() {
        return organismArray;
    }
    public Map getMap() {
        return map;
    }
    public String getLogs() {
        return logs;
    }
    public int getTurn() {
        return turn;
    }

    //map
    public void setMap(Map map) {
        this.map = map;
    }

    //operations on organisms
    public void addOrganism(Organism organism) {
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
        if (!added) {
            organismArray.add(organism);
        }
    }
    public void addMultiple(int number_of_each_species) {
        for(int i = 0; i < number_of_each_species; i++) {
            for(Class species : organismTypes) {
                try {
                    Class<?> clazz = Class.forName(species.getName());
                    Constructor<?> ctor = clazz.getConstructor(Point.class, World.class);
                    Organism o = (Organism) ctor.newInstance(new Object[] { this.getMap().findRandomEmptyPoint(), this});
                    this.addOrganism(o);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    //its fine really XD
                }
            }
        }
    }
    public void removeOrganism(Organism organism) {
        ListIterator<Organism> iterator = organismArray.listIterator(1);
        for (Organism o : organismArray) {
            if (o == organism) {
                iterator.remove();
                break;
            }
            iterator.next();
        }
    }
    private List<Class<Organism>> findAllClassesUsingReflectionsLibrary(String packageName) {
        List<Class<Organism>> organisms;
        try (ScanResult scanResult = new ClassGraph().whitelistPackages(packageName).enableClassInfo().scan()) {
            organisms = scanResult.getSubclasses(Organism.class.getName()).loadClasses(Organism.class);
        }
        return organisms;
    }

    //world functionality
    public void playTurn() {
        logs = "";
        int i = 0;
        while(i < organismArray.size()) {
            Organism current = organismArray.get(i);
            current.addAge();
            Point newPosition = current.action();
            boolean collided = false;
            for (Organism host : organismArray) {
                //check whether a collision occurred
                if (host.getPosition().equals(newPosition) && host != current) {
                    logs = logs + host.collision(current);
                    collided = true;
                    break;
                }
            }
            if (!collided) {
                current.setPosition(newPosition);
            }
            i++;
        }
        turn++;
    }
}
