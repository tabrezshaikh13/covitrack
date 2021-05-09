package com.example.covitrack.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.example.covitrack.model.CountryData;
import com.example.covitrack.model.GlobalData;
import com.example.covitrack.repo.ApiUrlEndpoints;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GlobalDataService {

    @Autowired
    ApiUrlEndpoints urlEndpoints = new ApiUrlEndpoints();

    private GlobalData globalData = new GlobalData();
    private List<CountryData> eachCountryData = new ArrayList<>();

    public GlobalData fetchGlobalData() {
        
        String globalJsonResponse = "";
        
        try {
            URL url = new URL(urlEndpoints.getGLOBAL_DATA_URL());
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.connect();

            int responseCode = httpUrlConnection.getResponseCode();

            if(responseCode != 200) {
                throw new RuntimeException("(GlobalData) Response Code: " + responseCode);
            } else {
                Scanner apiResponseScanner = new Scanner(url.openStream());
                while(apiResponseScanner.hasNextLine()) {
                    globalJsonResponse += apiResponseScanner.nextLine();
                } apiResponseScanner.close();

                JSONObject jsonObject = new JSONObject(globalJsonResponse);
                JSONObject globalDataObject = jsonObject.getJSONObject("Global");

                int confirmed = (Integer) globalDataObject.get("TotalConfirmed");
                int recovered = (Integer) globalDataObject.get("TotalRecovered");
                int deaths = (Integer) globalDataObject.get("TotalDeaths");
                int active = confirmed - (recovered + deaths);

                globalData.setConfirmed(confirmed);
                globalData.setRecovered(recovered);
                globalData.setDeaths(deaths);
                globalData.setActive(active);
                
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

    public List<CountryData> fetchCountryData() {

        String countryJsonResponse = "";

        try {
            URL url = new URL(urlEndpoints.getGLOBAL_DATA_URL());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            int responseCode = httpURLConnection.getResponseCode();

            if(responseCode != 200) {
                throw new RuntimeException("(CountryData) Response Code: " + responseCode);
            } else {
                Scanner apiResponseScanner = new Scanner(url.openStream());
                while(apiResponseScanner.hasNextLine()) {
                    countryJsonResponse += apiResponseScanner.nextLine();
                } apiResponseScanner.close();

                JSONObject jsonObject = new JSONObject(countryJsonResponse);
                JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("Countries").toString());

                int jsonArraylength = jsonArray.length();

                for(int i=0; i<jsonArraylength; i++) {
                    JSONObject arrayObject = (JSONObject)jsonArray.get(i);
                    CountryData countryData = new CountryData();

                    String country = (String)arrayObject.get("Country");
                    int cases = (Integer)arrayObject.get("TotalConfirmed");
                    int recovered = (Integer)arrayObject.get("TotalRecovered");
                    int deaths = (Integer)arrayObject.get("TotalDeaths");
                    int active = cases - (recovered + deaths);

                    countryData.setCountry(country);
                    countryData.setCases(cases);
                    countryData.setRecovered(recovered);
                    countryData.setDeaths(deaths);
                    countryData.setActive(active);

                    eachCountryData.add(countryData);
                }

                httpURLConnection.disconnect();
                
                return eachCountryData;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return eachCountryData;
    }

}