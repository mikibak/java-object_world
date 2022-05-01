package com.company.Organisms.Animals;

import com.company.Organisms.Animal;
import com.company.World.Point;
import com.company.World.World;

import javax.swing.*;
import java.io.File;

public class Wolf extends Animal {
    public Wolf(Point position, World world) {
        super(position, "Wolf", 5, 9, 0, world, new ImageIcon("C:\\Users\\mikolaj\\IdeaProjects\\Object_world\\src\\com\\company\\img\\wolf.bmp"));
        File imageCheck = new File("C:\\Users\\mikolaj\\IdeaProjects\\Object_world\\src\\com\\company\\img\\wolf.bmp");

        if(imageCheck.exists())
            System.out.println("Image file found!");
        else
            System.out.println("Image file not found!");
    }
}
