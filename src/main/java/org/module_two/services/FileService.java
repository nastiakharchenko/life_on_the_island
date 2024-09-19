package org.module_two.services;

import lombok.extern.log4j.Log4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.module_two.constants.ExeptionConstants.*;
import static org.module_two.constants.SystemIslandConstants.SEMICOLON;

@Log4j
public class FileService {

    /*
     * Получение данных из *.csv
     * */
    public static List<String[]> importFromCsv(String filename) {
        List<String[]> dataFromFile = null;
        try (BufferedReader buffRead = Files.newBufferedReader(Path.of(filename))) {
            dataFromFile = new ArrayList<>();
            String line = buffRead.readLine();
            if(line == null){
                log.error(FILE_IS_EMPTY);
            } else{
                while((line = buffRead.readLine()) != null){
                    String[] elements = line.split(SEMICOLON);
                    dataFromFile.add(Arrays.copyOfRange(elements, 1, elements.length));
                }
            }
        } catch (IOException | NullPointerException e) {
            log.error(e.getMessage() + FILE_IS_INVALID);
        }
        return dataFromFile;
    }
}
