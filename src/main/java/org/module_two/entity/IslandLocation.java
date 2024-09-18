package org.module_two.entity;

import lombok.Getter;
import org.module_two.entity.wildlife.*;
import org.module_two.entity.wildlife.herbivore.*;
import org.module_two.entity.wildlife.predator.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import static org.module_two.constants.AnimalAndPlantsConstants.*;
import static org.module_two.constants.SystemConstants.*;

@Getter
public class IslandLocation {
    private final Map<Integer, List<Animal>> animals;
    private final Map<Integer, Integer> countAnimals;
    private final List<Plant> plants;
    private final int indexWidth;
    private final int indexHeight;

    public IslandLocation(int indexWidth, int indexHeight) {
        this.indexWidth = indexWidth;
        this.indexHeight = indexHeight;
        animals = new ConcurrentHashMap<>();
        countAnimals = new ConcurrentHashMap<>();
        plants = new ArrayList<>();
        fillInitialLocationData();
    }

    /*
     * Инициализация острова рандомными значениями количества животных
     * и максимальным значением растений
     * */
    public void fillInitialLocationData() {
        fillInitialAnimal(WOLF, new Wolf(
                Island.gameInputStartData.getCharacteristic(WOLF, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(WOLF, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(WOLF, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(BOA_CONSTRICTOR, new BoaConstrictor(
                Island.gameInputStartData.getCharacteristic(BOA_CONSTRICTOR, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(BOA_CONSTRICTOR, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(BOA_CONSTRICTOR, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(FOX, new Fox(
                Island.gameInputStartData.getCharacteristic(FOX, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(FOX, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(FOX, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(BEAR, new Bear(
                Island.gameInputStartData.getCharacteristic(BEAR, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(BEAR, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(BEAR, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(EAGLE, new Eagle(
                Island.gameInputStartData.getCharacteristic(EAGLE, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(EAGLE, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(EAGLE, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(HORSE, new Horse(
                Island.gameInputStartData.getCharacteristic(HORSE, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(HORSE, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(HORSE, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(DEER, new Deer(
                Island.gameInputStartData.getCharacteristic(DEER, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(DEER, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(DEER, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(RABBIT, new Rabbit(
                Island.gameInputStartData.getCharacteristic(RABBIT, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(RABBIT, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(RABBIT, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(MOUSE, new Mouse(
                Island.gameInputStartData.getCharacteristic(MOUSE, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(MOUSE, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(MOUSE, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(GOAT, new Goat(
                Island.gameInputStartData.getCharacteristic(GOAT, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(GOAT, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(GOAT, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(SHEEP, new Sheep(
                Island.gameInputStartData.getCharacteristic(SHEEP, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(SHEEP, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(SHEEP, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(BOAR, new Boar(
                Island.gameInputStartData.getCharacteristic(BOAR, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(BOAR, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(BOAR, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(BUFFALO, new Buffalo(
                Island.gameInputStartData.getCharacteristic(BUFFALO, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(BUFFALO, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(BUFFALO, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(DUCK, new Duck(
                Island.gameInputStartData.getCharacteristic(DUCK, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(DUCK, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(DUCK, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));
        fillInitialAnimal(CATERPILLAR, new Caterpillar(
                Island.gameInputStartData.getCharacteristic(CATERPILLAR, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE)
                , (int) Island.gameInputStartData.getCharacteristic(CATERPILLAR, MOVEMENT_SPEED_PER_TURN_INDEX_TABLE)
                , Island.gameInputStartData.getCharacteristic(CATERPILLAR, KILOGRAMS_FOOD_COMPLETE_SATIETY_INDEX_TABLE)
                , ANIMAL_MAX_HP));

        for (int i = 0; i < Island.gameInputStartData
                .getCharacteristic(PLANT, MAX_COUNT_ANIMAL_OR_PLANT_INDEX_TABLE); i++) {
            plants.add(new Plant());
        }
    }

    /*
    * Заполнение локации рандомным количеством животного
    * в пределах от 0 до максимально возможного
    * Фиксация количества животных
    * */
    public void fillInitialAnimal(int keyAnimal, Animal animal){
        int countAnimal = ThreadLocalRandom.current().nextInt( 1, (int) Island.gameInputStartData
                .getCharacteristic(keyAnimal, MAX_COUNT_ANIMAL_OR_PLANT_INDEX_TABLE) + 1);
        List<Animal> animalList = new ArrayList<>();
        for (int i = 0; i < countAnimal; i++) {
            animalList.add(animal);
        }
        animals.put(keyAnimal, animalList);
        countAnimals.put(keyAnimal, countAnimal);
    }

    public synchronized void addAnimal(int indexAnimal, Animal animal){
        animals.get(indexAnimal).add(animal);
        int count = countAnimals.get(indexAnimal);
        if (count > 0) {
            countAnimals.remove(indexAnimal);
            countAnimals.put(indexAnimal, count + 1);
        }
    }

    public synchronized void addPlants(int countPlants){
        int maxCountPlants = (int) Island.gameInputStartData.getCharacteristic(PLANT, MAX_COUNT_ANIMAL_OR_PLANT_INDEX_TABLE);
        int count = (countPlants + plants.size()) < maxCountPlants ? countPlants : 0;
        for (int i = 0; i < count; i++) {
            plants.add(new Plant());
        }
    }

    public synchronized void deleteAnimal(int indexAnimal, Animal animal){
        animals.get(indexAnimal).remove(animal);
        int count = countAnimals.get(indexAnimal);
        if (count > 0) {
            countAnimals.remove(indexAnimal);
            countAnimals.put(indexAnimal, count - 1);
        }
    }

    public synchronized void deletePlant(Plant plant){
        plants.remove(plant);
    }

    public int getCountAnimalInLocation(){
        int count = 0;
        for (Map.Entry<Integer, Integer> animals : countAnimals.entrySet()){
            count += animals.getValue();
        }
        return count;
    }

    public synchronized Map<Integer, Integer> getCountAnimals(){
        return countAnimals;
    }

    public synchronized int getCountAnimalSpecies(int index){
        return countAnimals.get(index);
    }
}
