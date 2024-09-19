package org.module_two.entity;

import lombok.Getter;
import lombok.Setter;
import org.module_two.services.IslandSimulationService;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.module_two.constants.SystemIslandConstants.*;
import static org.module_two.constants.FileNameConstants.FILE_CHARACTERISTICS_ANIMAL;
import static org.module_two.constants.FileNameConstants.FILE_PROBABILITY_EAT;

public class Island{
    public static GameInputStartData gameInputStartData;
    @Getter
    private int mapWidth = IslandSimulationService.getInstance().getWidthIsland().get();
    @Getter
    private int mapLength = IslandSimulationService.getInstance().getLengthIsland().get();
    @Getter
    @Setter
    private List<List<IslandLocation>> locations;
    private static volatile Island island;  //гарантия, что значение переменной будет сразу же записано
                                            // в основную память, а не в кэш процессора, и любые другие потоки
                                            // будут видеть это обновленное значение

    private Island() {
        gameInputStartData = new GameInputStartData(FILE_CHARACTERISTICS_ANIMAL, FILE_PROBABILITY_EAT);
        locations = new CopyOnWriteArrayList<>();
        for (int i = 0; i < mapWidth; i++) {
            List<IslandLocation> location = new CopyOnWriteArrayList<>();
            for (int j = 0; j < mapLength; j++) {
                location.add(new IslandLocation(i, j));
            }
            locations.add(location);
        }
    }

    public static Island getInstance(){
        if(island == null){
            synchronized (Island.class){
                if (island == null) {
                    island = new Island();
                }
            }
        }
        return island;
    }

    public IslandLocation getLocation(int indexWidth, int indexLength){
        return locations.get(indexWidth).get(indexLength);
    }

    /*
     * Рост растений на процентное соотношение PLANT_PERCENT_REPRODUCE
     * */
    public void plantGrowthInIsland(){
        for (List<IslandLocation> loc: locations){
            for (IslandLocation islandLocation : loc){
                islandLocation.addPlants(islandLocation.getPlants().size() * PLANT_PERCENT_REPRODUCE / 100);
            }
        }
    }

    /*
     * Получаем значение количества растений на острове
     * */
    public int countPlantsInIsland(){
        int count = 0;
        for (List<IslandLocation> islandLocations : locations){
            for (IslandLocation location : islandLocations){
                count += location.getPlants().size();
            }
        }
        return count;
    }

    /*
     * Получаем значение количества животных на острове
     * */
    public int countAnimalInIsland(){
        int count = 0;
        for (List<IslandLocation> islandLocations : locations){
            for (IslandLocation location : islandLocations){
                count += location.getCountAnimalInLocation();
            }
        }
        return count;
    }

    /*
    * Получаем Map количества особей видов животных на острове
    * */
    public Map<Integer, Integer> countSpeciesAnimalInIsland(){
        Map<Integer, Integer> countSpeciesAnimalInIsland = new ConcurrentHashMap<>();
        int[] countAnimal = new int[15];
        for (int i = 0; i < 15; i++) {
            countAnimal[i] = 0;
        }

        for (List<IslandLocation> islandLocations : locations){
            for (IslandLocation location : islandLocations){
                for (int i = 0; i < 15; i++) {
                    countAnimal[i] += location.getCountAnimals().get(i);
                }
            }
        }

        for (int i = 0; i < 15; i++) {
            countSpeciesAnimalInIsland.put(i, countAnimal[i]);
        }

        return countSpeciesAnimalInIsland;
    }
}
