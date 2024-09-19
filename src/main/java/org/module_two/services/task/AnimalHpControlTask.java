package org.module_two.services.task;

import lombok.extern.log4j.Log4j;
import org.module_two.entity.Island;
import org.module_two.entity.IslandLocation;
import org.module_two.entity.wildlife.Animal;
import java.util.List;
import java.util.Map;
import static org.module_two.constants.SystemIslandConstants.ANIMAL_HP_DECREASE_PERCENT;

@Log4j
public class AnimalHpControlTask implements Runnable{

    @Override
    public void run() {
        try{
            Island island = Island.getInstance();
            int indexW = 0, indexH = 0;
            while(indexW != island.getLocations().size() && indexH != island.getLocations().getFirst().size()){
                IslandLocation location = island.getLocation(indexW, indexH);
                if(!location.getAnimals().isEmpty()){   //если животные на локации есть
                    for(Map.Entry<Integer, List<Animal>> species : location.getAnimals().entrySet()){   //перебор всех видов животных
                        List<Animal> animalsSpecies = species.getValue();     //получение особей текущего вида
                        for (int i = 0; i < animalsSpecies.size(); i++) {
                            if(animalsSpecies.get(i).getHp() - ANIMAL_HP_DECREASE_PERCENT > 0){
                                animalsSpecies.get(i).setHp(animalsSpecies.get(i).getHp() - ANIMAL_HP_DECREASE_PERCENT);
                            } else{                                                 //если жизненный ресурс животного исчерпан,
                                location.deleteAnimal(species.getKey(), animalsSpecies.get(i));    //наступает смерть
                            }
                        }
                    }
                }
                indexH++;
                if(indexH == island.getLocations().getFirst().size() && indexW < island.getLocations().size() - 1){
                    indexH = 0;
                    indexW++;
                }
            }
        }catch (Exception e){
            log.error(this.getClass() + " " + e.getMessage());
        }
    }
}
