package org.module_two.entity.wildlife.predator;

import lombok.Getter;
import lombok.Setter;
import org.module_two.entity.Island;

import java.util.HashMap;

import static org.module_two.constants.AnimalAndPlantsConstants.*;

@Getter
public class BoaConstrictor extends Predator {    //Удав
    private final double maxWeight;
    @Setter
    private int hp = 100;   //%
    private final int movementSpeed;
    private final double kgForSatiety;
    @Setter
    private HashMap<Integer, Double> probabilityEat;

    public BoaConstrictor(double maxWeight, int movementSpeed, double kgForSatiety, int hp) {
        super(maxWeight, movementSpeed, kgForSatiety, hp);
        this.maxWeight = maxWeight;
        this.movementSpeed = movementSpeed;
        this.kgForSatiety = kgForSatiety;
        fillTableProbabilityEat();
        super.setProbabilityEat(probabilityEat);
    }

    @Override
    public boolean eat(Object o, int indexWLocation, int indexHLocation) {
        return super.eat(o, indexWLocation, indexHLocation);
    }

    @Override
    public void reproduce(int indexWLocation, int indexHLocation) {
        super.reproduce(indexWLocation, indexHLocation);
    }

    @Override
    public void movementByLocations(int indexWLocation, int indexHLocation) {
        super.movementByLocations(indexWLocation, indexHLocation);
    }

    @Override
    public void fillTableProbabilityEat(){
        probabilityEat = new HashMap<>();
        probabilityEat.put(FOX, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BOA_CONSTRICTOR, FOX) / 100.0);
        probabilityEat.put(RABBIT, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BOA_CONSTRICTOR, RABBIT) / 100.0);
        probabilityEat.put(MOUSE, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BOA_CONSTRICTOR, MOUSE) / 100.0);
        probabilityEat.put(DUCK, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BOA_CONSTRICTOR, DUCK) / 100.0);
    }
}
