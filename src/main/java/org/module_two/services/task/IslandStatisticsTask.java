package org.module_two.services.task;

import lombok.extern.log4j.Log4j;
import org.module_two.AppIsland;
import org.module_two.entity.Island;
import org.module_two.services.IslandSimulationService;
import org.module_two.services.StatisticsLocationService;

import static org.module_two.constants.JavaFXConstants.GAME_OVER_ANIMAL;
import static org.module_two.constants.JavaFXConstants.GAME_OVER_PLANT;

@Log4j
public class IslandStatisticsTask implements Runnable{
    @Override
    public void run() {
        IslandSimulationService.getInstance().getCountDay().getAndIncrement();
        String statistic = StatisticsLocationService.printStatisticByIsland(IslandSimulationService.getInstance().getCountDay(), Island.getInstance().countSpeciesAnimalInIsland(), Island.getInstance().countPlantsInIsland());
        AppIsland.getController().printStatistics(statistic);

        if(Island.getInstance().countPlantsInIsland() == 0){    //если все локации пусты на растения
            log.info(this.getClass() + " There are no plants left on the island.");
            AppIsland.getController().printStatistics(GAME_OVER_PLANT);
            IslandSimulationService.getInstance().getExecutorService().shutdown();
        } else if(Island.getInstance().countAnimalInIsland() == 0){ //или животных
            log.info(this.getClass() + " There are no animals left on the island.");
            AppIsland.getController().printStatistics(GAME_OVER_ANIMAL);
            IslandSimulationService.getInstance().getExecutorService().shutdown();
        }
    }
}
