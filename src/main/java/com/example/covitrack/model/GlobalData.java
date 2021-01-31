package com.example.covitrack.model;

public class GlobalData {
    
    private int confirmed;
    private int recovered;
    private int deaths;
    private int active;

    public GlobalData() {

    }

    public GlobalData(int confirmed, int recovered, int deaths, int active) {
        this.confirmed = confirmed;
        this.recovered = recovered;
        this.deaths = deaths;
        this.active = active;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
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
        return "GlobalData [active=" + active
        + ", confirmed=" + confirmed
        + ", deaths=" + deaths
        + ", recovered=" + recovered + "]";
    }

}
