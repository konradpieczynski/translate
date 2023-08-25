package org.translate.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FileMapper {
    public HashMap<String,String> readFileToMap(){
        HashMap<String, String> readFile;
        try {
            readFile = new ObjectMapper().readValue(new FileReader("ManualTransFile.json"), HashMap.class);
        } catch (IOException e) {
            System.out.println("Cannot open file");
            throw new RuntimeException(e);
        }
        System.out.println("Successfully read file");
        return readFile;
    }
    public void writeFile(HashMap<String,String> mapTowWrite){
        try {
            FileWriter fileWriter =  new FileWriter("ManualTransFileTranslated.json");
            String json = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(mapTowWrite);
            fileWriter.write(json);
            fileWriter.close();
            System.out.println("Write to file successful");
        } catch (IOException e) {
            System.out.println("Couldn't write to file.");
            throw new RuntimeException(e);
        }
    }
}
