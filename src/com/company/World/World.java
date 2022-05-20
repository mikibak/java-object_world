package com.company.World;

import com.company.GUI.MovementListener;
import com.company.Organisms.Animal;
import com.company.Organisms.Animals.Human;
import com.company.Organisms.Organism;
import com.company.World.Maps.HexMap;
import com.company.World.Maps.Map;
import com.company.World.Maps.SquareMap;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class World {

    private ArrayList<Organism> organismArray;
    private String logs;
    private int turn;
    private Map map;
    private List<Class<Organism>> organismTypes;

    private char keyInput;
    private int specialEffectCooldown;

    public World() {
        this.organismArray = new ArrayList<Organism>();
        this.organismTypes = this.findAllClassesUsingReflectionsLibrary("com.company.Organisms");
        this.logs = "Game starts now! ";
    }

    //getters
    public int getNumberOfOrganisms() {
        return organismArray.size();
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
    public char getKeyInput() {
        return keyInput;
    }
    public void setKeyInput(char keyInput) {
        this.keyInput = keyInput;
    }
    public int getSpecialEffectCooldown() {
        return specialEffectCooldown;
    }
    public void setSpecialEffectCooldown(int specialEffectCooldown) {
        this.specialEffectCooldown = specialEffectCooldown;
        if(this.specialEffectCooldown<0) {
            this.specialEffectCooldown = 0;
        }
    }
    protected List<Class<Organism>> getOrganismTypes() {
        return organismTypes;
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
                    if(o.getName() != "Human") //there can only be one human
                    this.addOrganism(o);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    //its fine really
                }
            }
        }
        this.addOrganism(new Human(new Point(getMap().getMapSizeX()/2,getMap().getMapSizeY()/2),this));
    }
    public Organism addByName(String name) {
        for(Class species : organismTypes) {
            if(species.getName().equals("com.company.Organisms.Animals." + name) || species.getName().equals("com.company.Organisms.Plants." + name)|| species.getName().equals(name)) {
                try {
                    Class<?> clazz = Class.forName(species.getName());
                    Constructor<?> ctor = clazz.getConstructor(Point.class, World.class);
                    Organism o = (Organism) ctor.newInstance(new Object[] { this.getMap().findRandomEmptyPoint(), this});
                    return o;
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    //its fine really XD
                }
            }
        }
        return null;
    }
    public void removeOrganism(Organism organism) {
        ListIterator<Organism> iterator = organismArray.listIterator(0);
        for (int i = 0; i < organismArray.size(); i ++) {
            Organism o = organismArray.get(i);
            if (o == organism) {
                organismArray.remove(i);
                break;
            }
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

    //file operations
    public void save() throws IOException {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("save.txt"), "UTF-8");
            writer.write(Integer.toString(this.organismArray.size())+'\n');
            if(this.getMap().getClass() == SquareMap.class) {
                writer.write("SquareMap"+'\n');
            } else writer.write("HexMap"+'\n');
            writer.write(Integer.toString(this.getMap().getMapSizeX())+'\n');
            writer.write(Integer.toString(this.getMap().getMapSizeY())+'\n');
            writer.write(Integer.toString(this.getTurn())+'\n');
            writer.write(Integer.toString(this.getSpecialEffectCooldown())+'\n');
            for(Organism o : organismArray) {
                writer.write(Integer.toString(o.getPosition().getX())+'\n');
                writer.write(Integer.toString(o.getPosition().getY())+'\n');
                writer.write(o.getName()+'\n');
                writer.write(Integer.toString(o.getPower())+'\n');
                writer.write(Integer.toString(o.getAge())+'\n');
            }
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void load() {
        try {
            File myObj = new File("save.txt");
            Scanner myReader = new Scanner(myObj);
            String line;
            organismArray.clear();

            line = myReader.nextLine();
            int n_of_organisms = Integer.parseInt(line);

            String mapType = myReader.nextLine();

            int sizeX, sizeY;
            sizeX = Integer.parseInt(myReader.nextLine());
            sizeY = Integer.parseInt(myReader.nextLine());
            if(mapType == "SquareMap") {
                this.setMap(new SquareMap(sizeX, sizeY,this));
            } else this.setMap(new HexMap(sizeX, sizeY,this));

            line = myReader.nextLine();
            turn = Integer.parseInt(line);

            line = myReader.nextLine();
            specialEffectCooldown = Integer.parseInt(line);

            for (int i = 0; i < n_of_organisms; i++) {
                Point p = new Point();
                line = myReader.nextLine();
                p.setX(Integer.parseInt(line));

                line = myReader.nextLine();
                p.setY(Integer.parseInt(line));

                line = myReader.nextLine();
                Organism organism = addByName(line);

                line = myReader.nextLine();
                organism.setPower(Integer.parseInt(line));

                line = myReader.nextLine();
                for (int j = 0; j < Integer.parseInt(line); j++) {
                    organism.addAge();
                }
                organism.setPosition(p);
                organism.setWorld(this);
                addOrganism(organism);
            }
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
