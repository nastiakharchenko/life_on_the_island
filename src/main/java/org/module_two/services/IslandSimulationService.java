package org.module_two.services;

import lombok.Getter;
import org.module_two.entity.Island;
import org.module_two.services.task.AnimalHpControlTask;
import org.module_two.services.task.AnimalLifecycleTask;
import org.module_two.services.task.IslandStatisticsTask;
import org.module_two.services.task.PlantGrowthTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.module_two.constants.SystemIslandConstants.*;

public class IslandSimulationService {
    private static volatile IslandSimulationService islandSimulation;
    @Getter
    private volatile ScheduledExecutorService executorService;
    @Getter
    private AtomicInteger countDay = new AtomicInteger(0);
    @Getter
    private AtomicInteger widthIsland = new AtomicInteger(WIDTH_ISLAND);
    @Getter
    private AtomicInteger lengthIsland = new AtomicInteger(LENGTH_ISLAND);
    @Getter
    private AtomicLong durationSimulation = new AtomicLong(DURATION_SIMULATION_CYCLE);

    public static IslandSimulationService getInstance() {
        synchronized (Island.class){
            if (islandSimulation == null) {
                islandSimulation = new IslandSimulationService();
            }
        }
        return islandSimulation;
    }

    public void run() {
        executorService = Executors.newScheduledThreadPool(4);

        AnimalLifecycleTask animalLifecycleTask = new AnimalLifecycleTask();
        PlantGrowthTask plantGrowthTask = new PlantGrowthTask();
        IslandStatisticsTask islandStatisticsTask = new IslandStatisticsTask();
        AnimalHpControlTask animalHpControlTask = new AnimalHpControlTask();

        executorService.scheduleAtFixedRate(animalLifecycleTask, 0
                , durationSimulation.get() / 3 == 0 ? 1 : durationSimulation.get() / 3
                , TimeUnit.SECONDS);   //3 раза в сутки
        executorService.scheduleAtFixedRate(plantGrowthTask, 0, durationSimulation.get(), TimeUnit.SECONDS);      //раз в сутки
        executorService.scheduleAtFixedRate(islandStatisticsTask, 0, durationSimulation.get(), TimeUnit.SECONDS); //раз в сутки
        executorService.scheduleAtFixedRate(animalHpControlTask, 0, durationSimulation.get(), TimeUnit.SECONDS);  //раз в сутки
    }
}
