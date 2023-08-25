package org.translate.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class HttpClient {
 java.net.http.HttpClient client = java.net.http.HttpClient.newHttpClient();
 public String post(String string) {
  try {
   String requestBody = prepareRequest(string);
   HttpRequest request = HttpRequest
     .newBuilder()
     .uri(URI.create("http://localhost:5000/translate"))
     .POST(HttpRequest.BodyPublishers.ofString(requestBody))
     .header("Content-Type", "application/json")
     .build();

   HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
   JsonNode json= new ObjectMapper().readTree(response.body());
      return json.path("translatedText").asText();
  } catch (IOException | InterruptedException e) {
   System.out.println("Couldn't resolve translate request");
   throw new RuntimeException();
  }
 }

 private String prepareRequest(String string) throws JsonProcessingException {
  HashMap<String, String> values = new HashMap<>() {
   {
    put("q", string);
    put("source", "ja");
    put("target", "en");
    put("format","text");
    put("api_key","");
   }
  };
  ObjectMapper objectMapper = new ObjectMapper();
     return objectMapper.writeValueAsString(values);
 }
}

