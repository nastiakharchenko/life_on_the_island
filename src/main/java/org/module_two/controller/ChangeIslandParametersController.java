package org.module_two.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.module_two.services.IslandSimulationService;
import org.module_two.view.DialogToUser;

import static org.module_two.constants.ExeptionConstants.*;
import static org.module_two.constants.SystemIslandConstants.*;

@Log4j
public class ChangeIslandParametersController {
    @FXML
    private TextField islandWidth;
    @FXML
    private TextField islandHeight;
    @FXML
    private TextField durationSimulation;
    @Setter
    private Stage stage;

    @FXML
    private void initialize() {
        islandWidth.setText(String.valueOf(WIDTH_ISLAND));
        islandHeight.setText(String.valueOf(LENGTH_ISLAND));
        durationSimulation.setText(String.valueOf(DURATION_SIMULATION_CYCLE));
    }

    @FXML
    private void handleButtonOK() {
        //Проверка введенных значений:
        boolean statusChangeW = false, statusChangeH = false, statusDuration = false;
        try{
            int width = Integer.parseInt(islandWidth.getText());
            if(width <= 0 || width > 100) {
                DialogToUser.exceptionShow(ATTENTION, INCORRECT_VALUE, "Значення кількості рядків не може перевищувати 100 та бути меншим за 0");
            } else{
                IslandSimulationService.getInstance().getWidthIsland().set(width);
                statusChangeW = true;
            }
            int height = Integer.parseInt(islandHeight.getText());
            if(height <= 0 || height > 100) {
                DialogToUser.exceptionShow(ATTENTION, INCORRECT_VALUE, "Значення кількості стовпців не може перевищувати 100 та бути меншим за 0");
            } else{
                IslandSimulationService.getInstance().getLengthIsland().set(height);
                statusChangeH = true;
            }
            int duration = Integer.parseInt(durationSimulation.getText());
            if(duration <= 0 || duration > 100) {
                DialogToUser.exceptionShow(ATTENTION, INCORRECT_VALUE, "Значення тривалості симуляції не може перевищувати 100 та бути меншим за 0");
            } else{
                IslandSimulationService.getInstance().getDurationSimulation().set(duration);
                statusDuration = true;
            }
        }catch (NumberFormatException e){
            log.error(this.getClass() + " " + e.getMessage());
            DialogToUser.exceptionShow(ATTENTION, INCORRECT_VALUE, "Значення кількості рядків та стовпців повинно бути числом");
        }

        //Закрываем окно:
        if(statusChangeW && statusChangeH && statusDuration) {
            stage.close();
        }
    }
}
