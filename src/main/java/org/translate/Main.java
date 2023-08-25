package org.translate;

import org.translate.http.HttpClient;
import org.translate.mapper.FileMapper;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FileMapper fileMapper = new FileMapper();
        HttpClient httpClient = new HttpClient();
        HashMap<String,String> data = fileMapper.readFileToMap();
        HashMap<String,String> translatedData = new HashMap<>();
        int count = 0;
        for (Map.Entry<String, String> entry: data.entrySet()) {
            count++;
            System.out.print(count + "/" + data.size() +"\r");
            translatedData.put(entry.getKey(), httpClient.post(entry.getValue()));
        }
        fileMapper.writeFile(translatedData);
    }
}