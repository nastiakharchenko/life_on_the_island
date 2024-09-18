package org.module_two.entity.wildlife.predator;

import lombok.Getter;
import lombok.Setter;
import org.module_two.entity.Island;
import org.module_two.entity.IslandLocation;

import java.util.HashMap;

import static org.module_two.constants.AnimalAndPlantsConstants.*;
import static org.module_two.constants.SystemConstants.*;

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
        IslandLocation location = Island.getInstance().getLocation(indexWLocation, indexHLocation);
        if(location.getCountAnimalSpecies(BEAR) < (int) (Island.gameInputStartData.getCharacteristic(BEAR
                , MAX_COUNT_ANIMAL_OR_PLANT_INDEX_TABLE) * 0.9)){
            location.addAnimal(BEAR, new Bear(
                    Island.gameInputStartData.getCharacteristic(BEAR, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(BEAR, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(BEAR, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
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
