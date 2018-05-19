package com.example.jokes;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Data {

    private static final String DATA_PATH = "jokes/data/data.json";


    public static String getJokesJson() {

        BufferedReader bufferedReader = null;
        String json = null;

        try {

            bufferedReader = new BufferedReader(new FileReader(DATA_PATH));
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();

            while (line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }

            json = stringBuilder.toString();

        } catch (FileNotFoundException e) {
            System.out.println("File is not found. " + DATA_PATH);
        } catch (IOException e) {
            System.out.println("Can't read the line. " + e.getMessage());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    System.out.println("Can't close the BufferedReader. " + e.getMessage());
                }
            }
        }

        System.out.println(json);

        return json;

    }

}
