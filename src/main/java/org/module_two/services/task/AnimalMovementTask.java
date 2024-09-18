package org.module_two.services.task;

import lombok.extern.log4j.Log4j;
import org.module_two.entity.Island;
import org.module_two.entity.IslandLocation;
import org.module_two.entity.wildlife.Animal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@Log4j
public class AnimalMovementTask implements Runnable{

    private final CountDownLatch latch;

    public AnimalMovementTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            for (List<IslandLocation> locationRow : Island.getInstance().getLocations()){     //строки матрицы-острова
                for (IslandLocation location : locationRow){                                  //столбцы
                    for(Map.Entry<Integer, List<Animal>> species : location.getAnimals().entrySet()){   //все животные локации
                        int countAnimalSpecies = species.getValue().size();
                        if(countAnimalSpecies > 0){
                            int percentReproduce = (int) (countAnimalSpecies * 0.25);    //разрешаем передвигаться только 25% животных каждого вида
                            List<Animal> animalsForReproduce = species.getValue();
                            for (int i = 0; i < percentReproduce; i++) {
                                animalsForReproduce.get(i).movementByLocations(location.getIndexWidth(), location.getIndexHeight());
                            }
                        }
                    }
                }
            }
            latch.countDown();
        }catch (Exception e){
            log.error(this.getClass() + " " + e.getMessage());
        }
    }
}
