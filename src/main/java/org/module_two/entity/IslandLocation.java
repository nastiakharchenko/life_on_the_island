package org.module_two.entity;

import lombok.Getter;
import org.module_two.entity.wildlife.*;
import org.module_two.services.ComplianceIndexAnimalService;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

import static org.module_two.constants.AnimalAndPlantsConstants.*;
import static org.module_two.constants.SystemIslandConstants.*;

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
        plants = new CopyOnWriteArrayList<>();
        fillInitialLocationData();
    }

    /*
     * Инициализация острова рандомными значениями количества животных
     * и максимальным значением растений
     * */
    public void fillInitialLocationData() {
        fillInitialAnimal(WOLF, ComplianceIndexAnimalService.getObjectAnimalBySpecies(WOLF));
        fillInitialAnimal(BOA_CONSTRICTOR, ComplianceIndexAnimalService.getObjectAnimalBySpecies(BOA_CONSTRICTOR));
        fillInitialAnimal(FOX, ComplianceIndexAnimalService.getObjectAnimalBySpecies(FOX));
        fillInitialAnimal(BEAR, ComplianceIndexAnimalService.getObjectAnimalBySpecies(BEAR));
        fillInitialAnimal(EAGLE, ComplianceIndexAnimalService.getObjectAnimalBySpecies(EAGLE));
        fillInitialAnimal(HORSE, ComplianceIndexAnimalService.getObjectAnimalBySpecies(HORSE));
        fillInitialAnimal(DEER, ComplianceIndexAnimalService.getObjectAnimalBySpecies(DEER));
        fillInitialAnimal(RABBIT, ComplianceIndexAnimalService.getObjectAnimalBySpecies(RABBIT));
        fillInitialAnimal(MOUSE, ComplianceIndexAnimalService.getObjectAnimalBySpecies(MOUSE));
        fillInitialAnimal(GOAT, ComplianceIndexAnimalService.getObjectAnimalBySpecies(GOAT));
        fillInitialAnimal(SHEEP, ComplianceIndexAnimalService.getObjectAnimalBySpecies(SHEEP));
        fillInitialAnimal(BOAR, ComplianceIndexAnimalService.getObjectAnimalBySpecies(BOAR));
        fillInitialAnimal(BUFFALO, ComplianceIndexAnimalService.getObjectAnimalBySpecies(BUFFALO));
        fillInitialAnimal(DUCK, ComplianceIndexAnimalService.getObjectAnimalBySpecies(DUCK));
        fillInitialAnimal(CATERPILLAR, ComplianceIndexAnimalService.getObjectAnimalBySpecies(CATERPILLAR));

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
        int countAnimal = ThreadLocalRandom.current().nextInt(1, (int) Island.gameInputStartData
                .getCharacteristic(keyAnimal, MAX_COUNT_ANIMAL_OR_PLANT_INDEX_TABLE) + 1);
        List<Animal> animalList = new CopyOnWriteArrayList<>();
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

    /*
    * Возвращает количество животных на локации
    * */
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
