package org.module_two.entity.wildlife.predator;

import lombok.Getter;
import lombok.Setter;
import org.module_two.entity.Island;

import java.util.HashMap;

import static org.module_two.constants.AnimalAndPlantsConstants.*;

@Getter
public class Wolf extends Predator {
    private final double maxWeight;
    @Setter
    private int hp = 100;   //%
    private final int movementSpeed;
    private final double kgForSatiety;
    private HashMap<Integer, Double> probabilityEat;

    public Wolf(double maxWeight, int movementSpeed, double kgForSatiety, int hp) {
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
        probabilityEat.put(HORSE, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(WOLF, HORSE) / 100.0);
        probabilityEat.put(DEER, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(WOLF, DEER) / 100.0);
        probabilityEat.put(RABBIT, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(WOLF, RABBIT) / 100.0);
        probabilityEat.put(MOUSE, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(WOLF, MOUSE) / 100.0);
        probabilityEat.put(GOAT, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(WOLF, GOAT) / 100.0);
        probabilityEat.put(SHEEP, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(WOLF, SHEEP) / 100.0);
        probabilityEat.put(BOAR, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(WOLF, BOAR) / 100.0);
        probabilityEat.put(BUFFALO, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(WOLF, BUFFALO) / 100.0);
        probabilityEat.put(DUCK, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(WOLF, DUCK) / 100.0);
    }
}
