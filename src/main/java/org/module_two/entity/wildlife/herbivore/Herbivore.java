package org.module_two.entity.wildlife.herbivore;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.module_two.entity.Island;
import org.module_two.entity.IslandLocation;
import org.module_two.entity.wildlife.Animal;
import org.module_two.entity.wildlife.Plant;
import org.module_two.services.ComplianceIndexAnimalService;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

import static org.module_two.constants.SystemConstants.*;

@Log4j
@Getter
public class Herbivore implements Animal, Cloneable {
    private final double maxWeight;
    @Setter
    private int hp;   //%
    private final int movementSpeed;
    private final double kgForSatiety;
    @Setter
    private HashMap<Integer, Double> probabilityEat;

    public Herbivore(double maxWeight, int movementSpeed, double kgForSatiety, int hp) {
        this.maxWeight = maxWeight;
        this.movementSpeed = movementSpeed;
        this.kgForSatiety = kgForSatiety;
        this.hp = hp;
    }

    @Override
    public boolean eat(Object o, int indexWLocation, int indexHLocation) {
        if(this.getHp() > 0 && this.getHp() < ANIMAL_HUNGRY_PERCENT){
            Island island = Island.getInstance();
            IslandLocation location = island.getLocation(indexWLocation, indexHLocation);
            if(o instanceof Plant){
                int index = ComplianceIndexAnimalService.getIndexAnimalsAndPlant(o.getClass().getSimpleName());
                double weightSacrifice = Island.gameInputStartData.getCharacteristic(index, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE);

                location.deletePlant((Plant) o);

                this.setHp(weightSacrifice >= kgForSatiety ? ANIMAL_MAX_HP : (int) (this.getHp() + ((weightSacrifice < 1.0) ? 1 : weightSacrifice)));
                //hp = weightSacrifice >= kgForSatiety ? ANIMAL_MAX_HP : (int) (hp + ((weightSacrifice < 1.0) ? 1 : weightSacrifice));
                return true;
            } else if(o instanceof Animal){
                if(((Animal) o).getHp() > 0){
                    int index = ComplianceIndexAnimalService.getIndexAnimalsAndPlant(o.getClass().getSimpleName());
                    Double percent = probabilityEat.get(index);
                    if(percent != null){

                        if(ThreadLocalRandom.current().nextDouble() >= percent){
                            double weightSacrifice = Island.gameInputStartData.getCharacteristic(index
                                    , MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE);

                            ((Animal) o).setHp(-1);

                            this.setHp(weightSacrifice >= kgForSatiety ? ANIMAL_MAX_HP : (int) (this.getHp() + ((weightSacrifice < 1.0) ? 1 : weightSacrifice)));
                            //hp = weightSacrifice >= kgForSatiety ? ANIMAL_MAX_HP : (int) (hp + ((weightSacrifice < 1.0) ? 1 : weightSacrifice));
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void reproduce(int indexWLocation, int indexHLocation) {
    }

    @Override
    public void movementByLocations(int indexWLocation, int indexHLocation) {
        int countStep = 0;
        while(true){
            if(countStep > 2){
                break;
            }

            int direction = ThreadLocalRandom.current().nextInt( 0, DIRECTION_COUNT);
            int countStepDirection = ThreadLocalRandom.current().nextInt( 0, movementSpeed + 1);

            int stepHor, stepVert;

            switch(direction){
                case 0 ->  {
                    stepHor = indexWLocation - countStepDirection;
                    stepVert = indexHLocation;
                }
                case 1 -> {
                    stepHor = indexWLocation;
                    stepVert = indexHLocation + countStepDirection;
                }
                case 2 -> {
                    stepHor = indexWLocation + countStepDirection;
                    stepVert = indexHLocation;
                }
                case 3 -> {
                    stepHor = indexWLocation;
                    stepVert = indexHLocation - countStepDirection;
                }
                default -> {
                    continue;
                }
            }

            //если новые индексы не выходят за границы допустимых
            // ИЛИ не выполнено количество допустимых попыток сменить локацию:
            if(stepHor >= 0 && stepVert >= 0 && stepHor < Island.getInstance().getMapWidth() && stepVert < Island.getInstance().getMapLength()){
                int indexAnimal = ComplianceIndexAnimalService.getIndexAnimalsAndPlant(this.getClass().getSimpleName());

                try{
                    //проверяем доступна ли нужная локация:
                    if (Island.getInstance().getLocation(indexWLocation, indexHLocation).getCountAnimalSpecies(indexAnimal)
                            < (int) (Island.gameInputStartData.getCharacteristic(indexAnimal, MAX_COUNT_ANIMAL_OR_PLANT_INDEX_TABLE) * 0.9)){

                        Island.getInstance().getLocation(stepHor, stepVert).addAnimal(indexAnimal, (Animal) this.clone());
                        this.setHp(-1);

                        break;
                    }
                }catch(NullPointerException | CloneNotSupportedException e){
                    log.error(this.getClass() + " " + e.getMessage());
                }
            }
            countStep++;
        }
    }

    @Override
    public void fillTableProbabilityEat() {

    }
}
