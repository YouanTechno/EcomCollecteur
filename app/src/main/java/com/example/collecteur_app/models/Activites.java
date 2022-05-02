package com.example.collecteur_app.models;

public class Activites {

    private int activid;
    private String activLib;

    public int getActivid() {
        return activid;
    }

    public void setActivid(int activid) {
        this.activid = activid;
    }

    public String getActivLib() {
        return activLib;
    }

    public void setActivLib(String activLib) {
        this.activLib = activLib;
    }

    public Activites(int activid, String activLib) {
        this.activid = activid;
        this.activLib = activLib;
    }
    public Activites(){

    }
}
