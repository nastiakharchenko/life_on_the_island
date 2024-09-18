package org.module_two.services.task;

import lombok.extern.log4j.Log4j;
import org.module_two.entity.Island;
import org.module_two.entity.IslandLocation;
import org.module_two.entity.wildlife.Animal;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import static org.module_two.constants.SystemConstants.ANIMAL_HUNGRY_PERCENT;

@Log4j
public class AnimalReproduceTask implements Runnable{
    private final CountDownLatch latch;

    public AnimalReproduceTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            for (List<IslandLocation> locationRow : Island.getInstance().getLocations()){     //строки матрицы-острова
                for (IslandLocation location : locationRow){                    //столбцы
                    for(Map.Entry<Integer, List<Animal>> species : location.getAnimals().entrySet()){   //все животные локации
                        int countAnimalSpecies = species.getValue().size();
                        if(countAnimalSpecies > 1){
                            double percentReproduce = countAnimalSpecies * 0.25;    //разрешаем размножение только 25% животных каждого вида
                            int countAnimalReproduce = percentReproduce < 1.0 ? 1 : (int)percentReproduce; //или 1 паре
                            List<Animal> animalsForReproduce = species.getValue();
                            for (int i = 0; i < countAnimalReproduce; i++) {
                                Animal current = animalsForReproduce.getFirst();
                                if(current.getHp() > ANIMAL_HUNGRY_PERCENT){
                                    current.reproduce(location.getIndexWidth(), location.getIndexHeight());
                                }
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
