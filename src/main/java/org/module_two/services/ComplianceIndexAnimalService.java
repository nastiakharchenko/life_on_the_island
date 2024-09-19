package org.module_two.services;

import lombok.extern.log4j.Log4j;
import org.module_two.entity.Island;
import org.module_two.entity.wildlife.Animal;
import org.module_two.entity.wildlife.herbivore.*;
import org.module_two.entity.wildlife.predator.*;

import static org.module_two.constants.AnimalAndPlantsConstants.*;
import static org.module_two.constants.SystemIslandConstants.*;
import static org.module_two.constants.SystemIslandConstants.ANIMAL_MAX_HP;

@Log4j
public class ComplianceIndexAnimalService {
    public static int getIndexAnimalsAndPlant(String nameAnimalOrPlant){
        return switch(nameAnimalOrPlant){
            case "Wolf" -> WOLF;
            case "BoaConstrictor" -> BOA_CONSTRICTOR;
            case "Fox" -> FOX;
            case "Bear" -> BEAR;
            case "Eagle" -> EAGLE;
            case "Horse" -> HORSE;
            case "Deer" -> DEER;
            case "Rabbit" -> RABBIT;
            case "Mouse" -> MOUSE;
            case "Goat" -> GOAT;
            case "Sheep" -> SHEEP;
            case "Boar" -> BOAR;
            case "Buffalo" -> BUFFALO;
            case "Duck" -> DUCK;
            case "Caterpillar" -> CATERPILLAR;
            case "Plant" -> PLANT;
            default -> {
                log.error("ComplianceIndexAnimal " + " unexpected value: " + nameAnimalOrPlant);
                throw new IllegalStateException("Unexpected value: " + nameAnimalOrPlant);
            }
        };
    }

    public static String getNameAnimalsAndPlant(int indexAnimalOrPlant){
        return switch(indexAnimalOrPlant){
            case WOLF -> "Wolf";
            case BOA_CONSTRICTOR -> "BoaConstrictor";
            case FOX -> "Fox";
            case BEAR -> "Bear";
            case EAGLE -> "Eagle";
            case HORSE -> "Horse";
            case DEER -> "Deer";
            case RABBIT -> "Rabbit";
            case MOUSE -> "Mouse";
            case GOAT -> "Goat";
            case SHEEP -> "Sheep";
            case BOAR -> "Boar";
            case BUFFALO -> "Buffalo";
            case DUCK -> "Duck";
            case CATERPILLAR -> "Caterpillar";
            case PLANT -> "Plant";
            default -> {
                log.error("ComplianceIndexAnimal " + " unexpected value: " + indexAnimalOrPlant);
                throw new IllegalStateException("Unexpected value: " + indexAnimalOrPlant);
            }
        };
    }

    public static Animal getObjectAnimalBySpecies(int indexAnimal){
        return switch(indexAnimal){
            case WOLF -> new Wolf(
                    Island.gameInputStartData.getCharacteristic(WOLF, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(WOLF, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(WOLF, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case BOA_CONSTRICTOR -> new BoaConstrictor(
                    Island.gameInputStartData.getCharacteristic(BOA_CONSTRICTOR, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(BOA_CONSTRICTOR, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(BOA_CONSTRICTOR, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case FOX -> new Fox(
                    Island.gameInputStartData.getCharacteristic(FOX, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(FOX, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(FOX, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case BEAR -> new Bear(
                    Island.gameInputStartData.getCharacteristic(BEAR, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(BEAR, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(BEAR, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case EAGLE -> new Eagle(
                    Island.gameInputStartData.getCharacteristic(EAGLE, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(EAGLE, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(EAGLE, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case HORSE -> new Horse(
                    Island.gameInputStartData.getCharacteristic(HORSE, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(HORSE, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(HORSE, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case DEER -> new Deer(
                    Island.gameInputStartData.getCharacteristic(DEER, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(DEER, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(DEER, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case RABBIT -> new Rabbit(
                    Island.gameInputStartData.getCharacteristic(RABBIT, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(RABBIT, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(RABBIT, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case MOUSE -> new Mouse(
                    Island.gameInputStartData.getCharacteristic(MOUSE, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(MOUSE, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(MOUSE, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case GOAT -> new Goat(
                    Island.gameInputStartData.getCharacteristic(GOAT, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(GOAT, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(GOAT, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case SHEEP -> new Sheep(
                    Island.gameInputStartData.getCharacteristic(SHEEP, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(SHEEP, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(SHEEP, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case BOAR -> new Boar(
                    Island.gameInputStartData.getCharacteristic(BOAR, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(BOAR, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(BOAR, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case BUFFALO -> new Buffalo(
                    Island.gameInputStartData.getCharacteristic(BUFFALO, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(BUFFALO, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(BUFFALO, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case DUCK -> new Duck(
                    Island.gameInputStartData.getCharacteristic(DUCK, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(DUCK, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(DUCK, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            case CATERPILLAR -> new Caterpillar(
                    Island.gameInputStartData.getCharacteristic(CATERPILLAR, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                    , (int) Island.gameInputStartData.getCharacteristic(CATERPILLAR, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                    , Island.gameInputStartData.getCharacteristic(CATERPILLAR, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                    , ANIMAL_MAX_HP);
            default -> {
                log.error("ComplianceIndexAnimal " + " unexpected value: " + indexAnimal);
                throw new IllegalStateException("Unexpected value: " + indexAnimal);
            }
        };
    }
}
