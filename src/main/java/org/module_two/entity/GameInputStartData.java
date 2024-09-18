package org.module_two.entity;

import lombok.extern.log4j.Log4j;
import org.module_two.services.FileService;
import java.util.List;

import static org.module_two.constants.ExeptionConstants.FILE_IS_INVALID;
import static org.module_two.constants.SystemConstants.DASH;
import static org.module_two.constants.SystemConstants.NO_DATA_IN_CELL;

@Log4j
public class GameInputStartData {
    private final List<String[]> characteristicsOfCreatures;
    private final List<String[]> probabilityAnimalEatFood;

    public GameInputStartData(String fileNameCharacteristics, String fileNameProbability) {
        characteristicsOfCreatures = FileService.importFromCsv(fileNameCharacteristics);
        probabilityAnimalEatFood = FileService.importFromCsv(fileNameProbability);
    }

    /*
    * Получение данных из ячейки таблицы с характеристиками животных и растений
    * */
    public double getCharacteristic(int indexAnimalOrPlant, int indexVar) {
        try{
            String value = characteristicsOfCreatures.get(indexAnimalOrPlant)[indexVar];
            if(!value.equals(NO_DATA_IN_CELL)){
                return Double.parseDouble(value);
            }
        }catch(NullPointerException | NumberFormatException e){
            log.error(e.getMessage() + FILE_IS_INVALID);
        }
        return 0;
    }

    /*
     * Получение данных из ячейки таблицы с вероятностью с которой животное съест еду
     * */
    public int getProbabilityCurrentAnimalEatFood(int indexAnimalOrPlant, int indexVar) {
        try{
            String value = probabilityAnimalEatFood.get(indexAnimalOrPlant)[indexVar];
            if(!value.equals(DASH)){
                return Integer.parseInt(value);
            }
        }catch(NullPointerException | NumberFormatException e){
            log.error(e.getMessage() + FILE_IS_INVALID);
        }
        return 0;
    }
}
