package org.module_two.entity.wildlife.herbivore;

import lombok.Getter;
import lombok.Setter;
import org.module_two.entity.Island;
import org.module_two.entity.IslandLocation;

import java.util.HashMap;

import static org.module_two.constants.AnimalAndPlantsConstants.*;
import static org.module_two.constants.SystemConstants.*;

@Getter
public class Caterpillar extends Herbivore {
    private final double maxWeight;
    @Setter
    private int hp = 100;   //%
    private final int movementSpeed;
    private final double kgForSatiety;
    private HashMap<Integer, Double> probabilityEat;

    public Caterpillar(double maxWeight, int movementSpeed, double kgForSatiety, int hp) {
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
        if(location.getCountAnimalSpecies(CATERPILLAR) < (int) (Island.gameInputStartData.getCharacteristic(CATERPILLAR
                , MAX_COUNT_ANIMAL_OR_PLANT_INDEX_TABLE) * 0.9)){
            location.addAnimal(CATERPILLAR, new Caterpillar(
                    Island.gameInputStartData.getCharacteristic(CATERPILLAR, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(CATERPILLAR, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(CATERPILLAR, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
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
        probabilityEat.put(PLANT, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(CATERPILLAR, PLANT) / 100.0);
    }
}
