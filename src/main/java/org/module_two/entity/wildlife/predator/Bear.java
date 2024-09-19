package org.module_two.entity.wildlife.predator;

import lombok.Getter;
import lombok.Setter;
import org.module_two.entity.Island;

import java.util.HashMap;

import static org.module_two.constants.AnimalAndPlantsConstants.*;

@Getter
public class Bear extends Predator {
    private final double maxWeight;
    @Setter
    private int hp = 100;   //%
    private final int movementSpeed;
    private final double kgForSatiety;
    @Setter
    private HashMap<Integer, Double> probabilityEat;

    public Bear(double maxWeight, int movementSpeed, double kgForSatiety, int hp) {
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
        probabilityEat.put(HORSE, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BEAR, HORSE) / 100.0);
        probabilityEat.put(DEER, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BEAR, DEER) / 100.0);
        probabilityEat.put(RABBIT, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BEAR, RABBIT) / 100.0);
        probabilityEat.put(MOUSE, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BEAR, MOUSE) / 100.0);
        probabilityEat.put(GOAT, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BEAR, GOAT) / 100.0);
        probabilityEat.put(SHEEP, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BEAR, SHEEP) / 100.0);
        probabilityEat.put(BOAR, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BEAR, BOAR) / 100.0);
        probabilityEat.put(BUFFALO, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BEAR, BUFFALO) / 100.0);
        probabilityEat.put(DUCK, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(BEAR, DUCK) / 100.0);
    }
}
