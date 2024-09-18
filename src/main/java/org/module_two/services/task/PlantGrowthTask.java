package org.module_two.services.task;

import org.module_two.entity.Island;

public class PlantGrowthTask implements Runnable{
    @Override
    public void run() {
        Island.getInstance().plantGrowthInIsland();
    }
}
