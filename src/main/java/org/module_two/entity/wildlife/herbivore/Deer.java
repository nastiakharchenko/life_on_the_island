package org.module_two.entity.wildlife.herbivore;

import lombok.Getter;
import lombok.Setter;
import org.module_two.entity.Island;
import org.module_two.entity.IslandLocation;

import java.util.HashMap;

import static org.module_two.constants.AnimalAndPlantsConstants.*;
import static org.module_two.constants.SystemConstants.*;

@Getter
public class Deer extends Herbivore {
    private final double maxWeight;
    @Setter
    private int hp = 100;   //%
    private final int movementSpeed;
    private final double kgForSatiety;
    private HashMap<Integer, Double> probabilityEat;

    public Deer(double maxWeight, int movementSpeed, double kgForSatiety, int hp) {
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
        if(location.getCountAnimalSpecies(DEER) < (int) (Island.gameInputStartData.getCharacteristic(DEER
                , MAX_COUNT_ANIMAL_OR_PLANT_INDEX_TABLE) * 0.9)){
            location.addAnimal(DEER, new Deer(
                    Island.gameInputStartData.getCharacteristic(DEER, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(DEER, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(DEER, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
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
        probabilityEat.put(PLANT, Island.gameInputStartData.getProbabilityCurrentAnimalEatFood(DEER, PLANT) / 100.0);
    }
}
