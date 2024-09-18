package org.module_two.services.task;

import org.module_two.AppIsland;
import org.module_two.entity.Island;
import org.module_two.services.IslandSimulationService;
import org.module_two.services.StatisticsLocationService;

public class IslandStatisticsTask implements Runnable{
    @Override
    public void run() {
        IslandSimulationService.getInstance().getCountDay().getAndIncrement();
        String statistic = StatisticsLocationService.printStatisticByIsland(IslandSimulationService.getInstance().getCountDay(), Island.getInstance().countSpeciesAnimalInIsland(), Island.getInstance().countPlantsInIsland());
        AppIsland.getController().printStatistics(statistic);
    }
}
