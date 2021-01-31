package com.example.covitrack.model;

public class CountryData {
    
    private String country;
    private int cases;
    private int recovered;
    private int deaths;
    private int active;

    public CountryData() {

    }

    public CountryData(String country, int cases, int recovered, int deaths, int active) {
        this.country = country;
        this.cases = cases;
        this.recovered = recovered;
        this.deaths = deaths;
        this.active = active;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCases() {
        return cases;
    }

    public void setCases(int cases) {
        this.cases = cases;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CountryData [active=" + active
        + ", cases=" + cases
        + ", country=" + country
        + ", deaths=" + deaths
        + ", recovered=" + recovered + "]";
    }

}
