package com.example.covitrack.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import com.example.covitrack.model.GlobalData;
import com.example.covitrack.repo.ApiUrlEndpoints;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalDataService {

    @Autowired
    ApiUrlEndpoints urlEndpoints = new ApiUrlEndpoints();

    private GlobalData globalData;

    @PostConstruct
    public GlobalData fetchGlobalData() {
        
        String jsonResponse = "";
        
        try {
            URL url = new URL(urlEndpoints.getGLOBAL_DATA_URL());
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.connect();

            int responseCode = httpUrlConnection.getResponseCode();

            if(responseCode != 200) {
                throw new RuntimeException("(GlobalDataService) Response Code: " + responseCode);
            } else {
                Scanner apiResponseScanner = new Scanner(url.openStream());
                while(apiResponseScanner.hasNext()) {
                    jsonResponse += apiResponseScanner.nextLine();
                } apiResponseScanner.close();

                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONObject globalDataObject = jsonObject.getJSONObject("Global");

                int confirmed = (Integer) globalDataObject.get("TotalConfirmed");
                int recovered = (Integer) globalDataObject.get("TotalRecovered");
                int deaths = (Integer) globalDataObject.get("TotalDeaths");
                int active = confirmed - (recovered + deaths);

                globalData = new GlobalData(confirmed, recovered, deaths, active);
                
                httpUrlConnection.disconnect();

                return globalData;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return globalData;
    }

}