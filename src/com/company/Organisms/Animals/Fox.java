package com.company.Organisms.Animals;

import com.company.Organisms.Animal;
import com.company.Organisms.Organism;
import com.company.World.Point;
import com.company.World.World;

public class Fox extends Animal {
    public Fox(Point position, World world) {
        super(position, "Fox", 7, 3, 0, world, "src\\com\\company\\img\\fox.png");
    }

    @Override
    public Point action() {
        int n_of_organisms = world.getNumberOfOrganisms();
        for (int j = 0; j < 15; j++) {
            //try 15 times, if it fails there is probably nowhere to go, so the fox
            //has to attack a random stronger creature
            Point newPosition = world.getMap().findRandomPointNearby(this.getPosition());
            boolean occupied = false;
            for (Organism other : world.getOrganismArray()) {
                if (other.getPosition().equals(this.getPosition()) && (other != this)) {
                    if (other.getPower() <= this.getPower()) {
                        //the other organism is not stronger, attack him
                        return other.getPosition();
                    }
				    else {
                        //the other organism is stronger, try again
                        occupied = true;
                        break;
                    }
                }
            }
            if (occupied == false) {
                //iterated through all organisms, none occupies the spot so the fox moves there
                return newPosition;
            }
        }
        return world.getMap().findRandomPointNearby(this.getPosition());
    }
}
