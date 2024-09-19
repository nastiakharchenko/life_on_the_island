package org.module_two.services;

import org.module_two.entity.wildlife.Plant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import static org.module_two.constants.AnimalAndPlantsConstants.*;

public class StatisticsLocationService {
    public static String printStatisticByLocation(Map<Integer, Integer> countAnimals, List<Plant> plants, int indexWidth, int indexHeight){
        StringBuilder str = new StringBuilder();
        str.append("------------------------------------------\n");
        str.append("Statictic location (").append(indexWidth).append(", ").append(indexHeight).append("):\n");
        for (Map.Entry<Integer, Integer> entry : countAnimals.entrySet()){
            str.append(ComplianceIndexAnimalService.getNameAnimalsAndPlant(entry.getKey())).append(": ").append(entry.getValue()).append("\t");
        }
        str.append("Plant: ").append(plants.size());
        return str.toString();
    }

    public static String printStatisticByIsland(AtomicInteger day, Map<Integer, Integer> countAnimals, int countPlants){
        StringBuilder str = new StringBuilder();
        str.append("\n===============День ").append(day).append("===============\n");
        for (Map.Entry<Integer, Integer> entry : countAnimals.entrySet()){
            str.append(UNICODE_ANIMALS_AND_PLANT[entry.getKey()]).append(" - ").append(UKRAINE_NAME_ANIMALS_AND_PLANT[entry.getKey()])
                    .append(" - ").append(entry.getValue()).append("\n");
        }
        str.append(UNICODE_ANIMALS_AND_PLANT[PLANT]).append(" - ").append(UKRAINE_NAME_ANIMALS_AND_PLANT[PLANT])
                .append(" - ").append(countPlants).append("\n");
        return str.toString();
    }
}
