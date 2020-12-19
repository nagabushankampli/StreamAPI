package com.stream.json.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class GsonExample {

    public static  void main(String[] args){

        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        String jsonFilePath = "/Users/nagabushankampli/git/json/sample.json";
        GsonExample gsonExample = new GsonExample();
        gsonExample.parse(jsonFilePath);


    }


    public void parse(String jsonFilePath){
        //create JsonReader object and pass it the json file,json source or json text.
        try(JsonReader jsonReader = new JsonReader(
                new InputStreamReader(
                        new FileInputStream(jsonFilePath), StandardCharsets.UTF_8))) {
            Gson gson = new GsonBuilder().create();
            jsonReader.beginArray(); //start of json array
            int numberOfRecords = 0;
            while (jsonReader.hasNext()){ //next json array element
                System.out.println(jsonReader);
                //Type listType = new TypeToken<List<String>>() {}.getType();
                DocumentDetails document = gson.fromJson(jsonReader, DocumentDetails.class);
                //List<String> yourList = new Gson().fromJson(jsonReader, listType);
                //System.out.println(yourList);
                //do something real
//                System.out.println(document);
                numberOfRecords++;
            }
            jsonReader.endArray();
            System.out.println("Total Records Found : "+numberOfRecords);
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
