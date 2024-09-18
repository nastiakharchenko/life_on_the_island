package org.module_two.services;

import lombok.extern.log4j.Log4j;

import static org.module_two.constants.AnimalAndPlantsConstants.*;

@Log4j
public class ComplianceIndexAnimalService {
    public static int getIndexAnimalsAndPlant(String nameAnimal){
        return switch(nameAnimal){
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
                log.error("ComplianceIndexAnimal " + " unexpected value: " + nameAnimal);
                throw new IllegalStateException("Unexpected value: " + nameAnimal);
            }
        };
    }

    public static String getNameAnimalsAndPlant(int indexAnimal){
        return switch(indexAnimal){
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
                log.error("ComplianceIndexAnimal " + " unexpected value: " + indexAnimal);
                throw new IllegalStateException("Unexpected value: " + indexAnimal);
            }
        };
    }
}
