/*
* File: Restapi.java
* Author: Zentai Pál
* Copyright: 2023, Zentai Pál
* Group: Szoft-II-N
* Date: 2023-01-30
* Github: https://github.com/Pali002/
* Licenc: GNU GPL
*/

package models;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Restapi {
    String host;
    public Restapi() {
        
        this.host = "http://[::1]:3000/";
    }

    public String getVehiclesAsString() {
        String response = null;
        try {
            response = tryGetVehiclesAsString();
        } catch (MalformedURLException e) {
            System.err.println("Hiba! Érvénytelen URL");
        } catch(IOException e) {
            System.err.println("Hiba! A respapi elérése sikertelen!");
        }
        return response;
    }

    public String tryGetVehiclesAsString() throws MalformedURLException, IOException{
        
        String endpoint = "vehicles";
        String urlStr = this.host + endpoint;
        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();
        int responseCode = http.getResponseCode();
        // System.out.println(responseCode);
        String text = null;
        if (responseCode == 200) {
            InputStream inputStream = http.getInputStream();
            text = this.converInputToString(inputStream);
        }else {
            text = "hiba";
        }
        // System.out.println(text);
        return text;
    }

    private String  converInputToString(InputStream inputStream) {
        Reader reader = new InputStreamReader(inputStream);
        Scanner scanner = new Scanner(reader);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine());
        }
        scanner.close();
        return stringBuilder.toString();
    }

    public ArrayList<Vehicles> getVehicles() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String text = getVehiclesAsString();
        Vehicles[] vehiclesArray = gson.fromJson(text, Vehicles[].class); 
        ArrayList<Vehicles> vehiclesList = new ArrayList<>(Arrays.asList(vehiclesArray));
        return vehiclesList;
    }
    
}
