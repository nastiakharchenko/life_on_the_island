package org.module_two.entity.wildlife;

import lombok.Getter;
import org.module_two.entity.Island;

import static org.module_two.constants.AnimalAndPlantsConstants.*;
import static org.module_two.constants.SystemConstants.MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE;

@Getter
public class Plant {
    private final double maxWeight;

    public Plant() {
        this.maxWeight = Island.gameInputStartData.getCharacteristic(PLANT, MAX_WEIGHT_ANIMAL_OR_PLANT_INDEX_TABLE);
    }
}
