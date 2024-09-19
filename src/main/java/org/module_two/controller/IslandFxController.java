package org.module_two.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.module_two.AppIsland;
import org.module_two.entity.Island;
import org.module_two.entity.IslandLocation;
import org.module_two.services.IslandSimulationService;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import static org.module_two.constants.AnimalAndPlantsConstants.PLANT;
import static org.module_two.constants.AnimalAndPlantsConstants.UNICODE_ANIMALS_AND_PLANT;
import static org.module_two.constants.JavaFXConstants.*;

@Log4j
public class IslandFxController {
    @FXML
    private ScrollPane paneForGrid;
    @FXML
    private TextArea textAreaForStatistic;
    @FXML
    private Button btnStart;
    @FXML
    private Button btnChangeParametr;

    private GridPane gridIsland;
    private Boolean statusProgram = false;
    @Getter
    private Timer time;

    @FXML
    private void initialize(){}

    @FXML
    private void start(){
        if(!statusProgram){
            IslandSimulationService.getInstance().run();
            statusProgram = true;
            btnStart.setText("Вийти");
            btnChangeParametr.setDisable(true);
            drawGridPaneThread();
        } else{
            if(!IslandSimulationService.getInstance().getExecutorService().isShutdown()){
                IslandSimulationService.getInstance().getExecutorService().shutdown();
            }
            time.cancel();
            log.info(this.getClass() + " The game is over.");
            System.exit(0);     //завершение без ошибок
        }
    }

    @FXML
    private void changeParameters() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(AppIsland.class.getResource("/fxml/change_island_parameters.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        Stage changeParamStage = new Stage();
        changeParamStage.setTitle("Edit parameters island");
        changeParamStage.initModality(Modality.WINDOW_MODAL);
        changeParamStage.initOwner(AppIsland.getPrimaryStage());
        Scene scene = new Scene(page);
        changeParamStage.setScene(scene);

        ChangeIslandParametersController controller = loader.getController();
        controller.setStage(changeParamStage);

        changeParamStage.showAndWait();
    }

    public void printStatistics(String statistics){
        textAreaForStatistic.appendText(statistics);
    }

    public void drawGridPaneThread(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> fillDataByGrid());
            }
        };
        time = new Timer();
        time.scheduleAtFixedRate(task,0, IslandSimulationService.getInstance().getDurationSimulation().get() * 1000); // * 1000 - чтобы перевести миллисекунды в секунды
    }

    private void drawIsland(){
        if(gridIsland != null){
            gridIsland.getChildren().clear();
        }
        gridIsland = new GridPane();

        for (int i = 0; i < Island.getInstance().getMapLength(); i++) {
            ColumnConstraints column = new ColumnConstraints(WIDTH_COLUMN);
            column.setHgrow(Priority.ALWAYS);
            gridIsland.getColumnConstraints().add(column);
        }

        for (int i = 0; i < Island.getInstance().getMapWidth(); i++) {
            RowConstraints row = new RowConstraints(HEIGHT_COLUMN);
            row.setVgrow(Priority.ALWAYS);
            gridIsland.getRowConstraints().add(row);
        }
    }

    public void fillDataByGrid(){
        drawIsland();
        for (int i = 0; i < Island.getInstance().getMapWidth(); i++) {
            for (int j = 0; j < Island.getInstance().getMapLength(); j++) {
                Label label = new Label(fillSymbolsByLocationGrid(Island.getInstance().getLocation(i, j)));

                if(!Island.getInstance().getLocation(i, j).getPlants().isEmpty()){
                    label.setStyle(
                            "-fx-wrap-text: true; " +
                                    "-fx-text-alignment: justify; " +
                                    "-fx-font-size: 16px; " +
                                    "-fx-background-color: #85ff9b; "
                    );
                } else{
                    label.setStyle(
                            "-fx-wrap-text: true; " +
                                    "-fx-text-alignment: justify; " +
                                    "-fx-font-size: 16px; " +
                                    "-fx-background-color: #fbfc9f; "
                    );
                }

                GridPane.setHgrow(label, Priority.ALWAYS);  // Растягиваем по горизонтали
                GridPane.setVgrow(label, Priority.ALWAYS);  // Растягиваем по вертикали
                label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); // Заполняем ячейку полностью

                gridIsland.add(label, j, i);
            }
        }
        gridIsland.setGridLinesVisible(true);
        gridIsland.setStyle("-fx-border-color: #01360b; ");
        paneForGrid.setContent(gridIsland);
    }

    private String fillSymbolsByLocationGrid(IslandLocation location){
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, Integer> species : location.getCountAnimals().entrySet()){
            if(species.getValue() <= 0){
                continue;
            }
            if(species.getValue() <= 10){
                sb.append(UNICODE_ANIMALS_AND_PLANT[species.getKey()]);
            } else if(species.getValue() <= 50){
                sb.append(UNICODE_ANIMALS_AND_PLANT[species.getKey()]);
                sb.append(UNICODE_ANIMALS_AND_PLANT[species.getKey()]);
            } else if(species.getValue() <= 200){
                sb.append(UNICODE_ANIMALS_AND_PLANT[species.getKey()]);
                sb.append(UNICODE_ANIMALS_AND_PLANT[species.getKey()]);
                sb.append(UNICODE_ANIMALS_AND_PLANT[species.getKey()]);
            } else{
                sb.append(UNICODE_ANIMALS_AND_PLANT[species.getKey()]);
                sb.append(UNICODE_ANIMALS_AND_PLANT[species.getKey()]);
                sb.append(UNICODE_ANIMALS_AND_PLANT[species.getKey()]);
                sb.append(UNICODE_ANIMALS_AND_PLANT[species.getKey()]);
            }
        }
        if (!location.getPlants().isEmpty()){
            sb.append(UNICODE_ANIMALS_AND_PLANT[PLANT]);
        }

        return sb.toString();
    }
}
