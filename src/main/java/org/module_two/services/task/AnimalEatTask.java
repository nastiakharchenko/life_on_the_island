package org.module_two.services.task;

import lombok.extern.log4j.Log4j;
import org.module_two.entity.Island;
import org.module_two.entity.IslandLocation;
import org.module_two.entity.wildlife.Animal;
import org.module_two.entity.wildlife.Plant;
import org.module_two.entity.wildlife.herbivore.Herbivore;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

@Log4j
public class AnimalEatTask implements Runnable{
    private final CountDownLatch latch;

    public AnimalEatTask(CountDownLatch latch) {
        this.latch = latch; //счетчик для синхронизации потоков
    }

    @Override
    public void run() {
        try{
            int indexW = 0, indexH = 0;

            while(indexW != Island.getInstance().getLocations().size() && indexH != Island.getInstance().getLocations().getFirst().size()){
                IslandLocation location = Island.getInstance().getLocation(indexW, indexH);

                if(!location.getAnimals().isEmpty()){
                    for(Map.Entry<Integer, List<Animal>> species : location.getAnimals().entrySet()){   //перебор всех видов животных
                        List<Animal> animalsSpecies = species.getValue();     //получение особей текущего вида

                        for (int i = 0; i < animalsSpecies.size(); i++) {
                            Map<Integer, List<Animal>> otherSpeciesAnimal = new ConcurrentHashMap<>(location.getAnimals());
                            otherSpeciesAnimal.remove(species.getKey());       //нельзя съесть себе подобного

                            for (Map.Entry<Integer, List<Animal>> otherSpecies : otherSpeciesAnimal.entrySet()) {

                                List<Animal> animal = location.getAnimals().get(otherSpecies.getKey());

                                boolean eatStatus = false;
                                while(true){        //питается животными
                                    if(!animal.isEmpty()){
                                        int index = ThreadLocalRandom.current().nextInt(animal.size());
                                        if(index < animal.size()){
                                            eatStatus = animalsSpecies.get(i).eat(animal.get(index), indexW, indexH);
                                            break;
                                        }
                                    } else{
                                        break;
                                    }
                                }
                                if(animalsSpecies.get(i) instanceof Herbivore){     //питается растением
                                    List<Plant> plantList = location.getPlants();
                                    if(!plantList.isEmpty()){
                                        for (int j = 0; j < plantList.size(); j++) {
                                            eatStatus = animalsSpecies.get(i).eat(plantList.get(j), indexW, indexH);
                                            if(!eatStatus){
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (eatStatus){     //может скушать только одно животное, но много растений
                                    break;
                                }
                            }
                        }
                    }
                }

                indexH++;
                if(indexH == Island.getInstance().getLocations().getFirst().size() && indexW < Island.getInstance().getLocations().size() - 1){
                    indexH = 0;
                    indexW++;
                }
            }

            //гарантия, что зависимый поток, вызывающий await(), будет блокироваться до тех пор,
            //пока рабочие потоки не будут завершены
            latch.countDown();
        }catch (Exception e) {
            log.error(this.getClass() + " " + e.getMessage());
        }
    }
}
