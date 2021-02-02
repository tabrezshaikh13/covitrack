package com.example.covitrack.controller;

import java.util.List;
import com.example.covitrack.model.CountryData;
import com.example.covitrack.model.GlobalData;
import com.example.covitrack.service.GlobalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class HomePage {
    
    @Autowired
    GlobalDataService globalDataService = new GlobalDataService();

    @GetMapping
    public String homeData(Model model) {

        GlobalData globalData = globalDataService.fetchGlobalData();
        List<CountryData> eachCountryData = globalDataService.fetchCountryData();

        model.addAttribute("globalData", globalData);
        model.addAttribute("eachCountryData", eachCountryData);

        return "home";
    }

}
