package com.company.Organisms;

import com.company.World.Point;
import com.company.World.World;
import com.company.Organisms.Plants.Grass;

import javax.swing.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class Organism {
    private Point position;
    private final String name;
    private final int initiative;
    private int power;
    private int age = 0;
    protected World world;
    private final int maturityAge = 5;
    private final int oldAge = 15;
    String imagePath;

    public Organism(Point position, String name, int initiative, int power, int age, World world, String imagePath) {
        this.position = position;
        this.name = name;
        this.initiative = initiative;
        this.power = power;
        this.age = age;
        this.world = world;
        this.imagePath = imagePath;
    }
    //getters and setters
    public int getPower() {
        return power;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public int getAge() {
        return age;
    }
    public void addAge() {
        this.age++;
    }
    public int getInitiative() {
        return initiative;
    }
    public Point getPosition() {
        return position;
    }
    public void setPosition(Point position) {
        this.position = position;
    }
    public String getName() {
        return name;
    }
    public World getWorld() {
        return world;
    }
    public int getMaturityAge() {
        return maturityAge;
    }
    public int getOldAge() {
        return oldAge;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setWorld(World world) {
        this.world = world;
    }

    public void createAnyOffspring(World world, Point childsPosition, String name) {
        try
        {
            Constructor<? extends Organism> constructor = getClass().getDeclaredConstructor(Point.class, World.class);
            Organism organism = constructor.newInstance(childsPosition, world );
            world.addOrganism(organism);
        }
        catch( Exception e )
        {
            System.out.println( e ) ;
        }
    }
    public abstract Point action();
    public abstract String collision(Organism guest);
}
