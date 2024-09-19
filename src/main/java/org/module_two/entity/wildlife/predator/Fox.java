package org.module_two.entity.wildlife.predator;

import lombok.Getter;
import lombok.Setter;
import org.module_two.entity.Island;

import java.util.HashMap;

import static org.module_two.constants.AnimalAndPlantsConstants.*;

@Getter
public class Fox extends Predator {
    private final double maxWeight;
    @Setter
    private int hp = 100;   //%
    private final int movementSpeed;
    private final double kgForSatiety;
    @Setter
    private HashMap<Integer, Double> probabilityEat;

    public Fox(double maxWeight, int movementSpeed, double kgForSatiety, int hp) {
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
        probabilityEat.put(RABBIT, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(FOX, RABBIT) / 100.0);
        probabilityEat.put(MOUSE, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(FOX, MOUSE) / 100.0);
        probabilityEat.put(DUCK, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(FOX, DUCK) / 100.0);
        probabilityEat.put(CATERPILLAR, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(FOX, CATERPILLAR) / 100.0);
    }
}
