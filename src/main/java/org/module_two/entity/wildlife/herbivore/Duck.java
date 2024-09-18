package org.module_two.entity.wildlife.herbivore;

import lombok.Getter;
import lombok.Setter;
import org.module_two.entity.Island;
import org.module_two.entity.IslandLocation;

import java.util.HashMap;

import static org.module_two.constants.AnimalAndPlantsConstants.*;
import static org.module_two.constants.SystemConstants.*;

@Getter
public class Duck extends Herbivore {
    private final double maxWeight;
    @Setter
    private int hp = 100;   //%
    private final int movementSpeed;
    private final double kgForSatiety;
    private HashMap<Integer, Double> probabilityEat;

    public Duck(double maxWeight, int movementSpeed, double kgForSatiety, int hp) {
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
        IslandLocation location = Island.getInstance().getLocation(indexWLocation, indexHLocation);
        if(location.getCountAnimalSpecies(DUCK) < (int) (Island.gameInputStartData.getCharacteristic(DUCK
                , MAX_COUNT_ANIMAL_OR_PLANT_INDEX_TABLE) * 0.9)){
            location.addAnimal(DUCK, new Duck(
                    Island.gameInputStartData.getCharacteristic(DUCK, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(DUCK, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(DUCK, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP));
        }
    }

    @Override
    public void movementByLocations(int indexWLocation, int indexHLocation) {
        super.movementByLocations(indexWLocation, indexHLocation);
    }

    @Override
    public void fillTableProbabilityEat(){
        probabilityEat = new HashMap<>();
        probabilityEat.put(CATERPILLAR, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(DUCK, CATERPILLAR) / 100.0);
        probabilityEat.put(PLANT, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(DUCK, PLANT) / 100.0);
    }
}
