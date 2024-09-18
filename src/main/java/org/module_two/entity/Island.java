package org.module_two.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.module_two.constants.SystemConstants.*;
import static org.module_two.constants.FileNameConstants.FILE_CHARACTERISTICS_ANIMAL;
import static org.module_two.constants.FileNameConstants.FILE_PROBABILITY_EAT;

public class Island{
    public static GameInputStartData gameInputStartData;
    @Getter
    private int mapWidth = WIDTH_ISLAND;
    @Getter
    private int mapLength = LENGTH_ISLAND;
    @Getter
    @Setter
    private List<List<IslandLocation>> locations;
    private static volatile Island island;

    private Island() {
        gameInputStartData = new GameInputStartData(FILE_CHARACTERISTICS_ANIMAL, FILE_PROBABILITY_EAT);
        locations = new ArrayList<>();
        for (int i = 0; i < mapWidth; i++) {
            List<IslandLocation> location = new ArrayList<>();
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

    public void plantGrowthInIsland(){
        for (List<IslandLocation> loc: locations){
            for (IslandLocation islandLocation : loc){
                islandLocation.addPlants(islandLocation.getPlants().size() * PLANT_PERCENT_REPRODUCE / 100);
            }
        }
    }

    public int countPlantsInIsland(){
        int count = 0;
        for (List<IslandLocation> islandLocations : locations){
            for (IslandLocation location : islandLocations){
                count += location.getPlants().size();
            }
        }
        return count;
    }

    public int countAnimalInIsland(){
        int count = 0;
        for (List<IslandLocation> islandLocations : locations){
            for (IslandLocation location : islandLocations){
                count += location.getCountAnimalInLocation();
            }
        }
        return count;
    }

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
