package com.example.collecteur_app.models;

public class Agent {
    int agId;
    String agLogin;
    String agNom;
    String agPrenoms;
    String tErreur;
    String agPass;
    String tAgentCollecteur;
    String tIncident;
    String tMail;
    String tMailAgent;
    String trAgentModuleDroit;
    String tResCollecte;
    String tResMetafacture;
    String tTraces;

    public Agent(String agLogins, String agPasse) {
        agLogin = agLogins;
        agPass = agPasse;
    }

    public Agent(int agId, String agLogin, String agNom, String agPrenoms, String tErreur, String agPass, String tAgentCollecteur, String tIncident, String tMail, String tMailAgent, String trAgentModuleDroit, String tResCollecte, String tResMetafacture, String tTraces) {
        this.agId = agId;
        this.agLogin = agLogin;
        this.agNom = agNom;
        this.agPrenoms = agPrenoms;
        this.tErreur = tErreur;
        this.agPass = agPass;
        this.tAgentCollecteur = tAgentCollecteur;
        this.tIncident = tIncident;
        this.tMail = tMail;
        this.tMailAgent = tMailAgent;
        this.trAgentModuleDroit = trAgentModuleDroit;
        this.tResCollecte = tResCollecte;
        this.tResMetafacture = tResMetafacture;
        this.tTraces = tTraces;
    }

    public int getAgId() {
        return agId;
    }

    public void setAgId(int agId) {
        this.agId = agId;
    }

    public String getAgLogin() {
        return agLogin;
    }

    public void setAgLogin(String agLogin) {
        this.agLogin = agLogin;
    }

    public String getAgNom() {
        return agNom;
    }

    public void setAgNom(String agNom) {
        this.agNom = agNom;
    }

    public String getAgPrenoms() {
        return agPrenoms;
    }

    public void setAgPrenoms(String agPrenoms) {
        this.agPrenoms = agPrenoms;
    }

    public String gettErreur() {
        return tErreur;
    }

    public void settErreur(String tErreur) {
        this.tErreur = tErreur;
    }

    public String getAgPass() {
        return agPass;
    }

    public void setAgPass(String agPass) {
        this.agPass = agPass;
    }

    public String gettAgentCollecteur() {
        return tAgentCollecteur;
    }

    public void settAgentCollecteur(String tAgentCollecteur) {
        this.tAgentCollecteur = tAgentCollecteur;
    }

    public String gettIncident() {
        return tIncident;
    }

    public void settIncident(String tIncident) {
        this.tIncident = tIncident;
    }

    public String gettMail() {
        return tMail;
    }

    public void settMail(String tMail) {
        this.tMail = tMail;
    }

    public String gettMailAgent() {
        return tMailAgent;
    }

    public void settMailAgent(String tMailAgent) {
        this.tMailAgent = tMailAgent;
    }

    public String getTrAgentModuleDroit() {
        return trAgentModuleDroit;
    }

    public void setTrAgentModuleDroit(String trAgentModuleDroit) {
        this.trAgentModuleDroit = trAgentModuleDroit;
    }

    public String gettResCollecte() {
        return tResCollecte;
    }

    public void settResCollecte(String tResCollecte) {
        this.tResCollecte = tResCollecte;
    }

    public String gettResMetafacture() {
        return tResMetafacture;
    }

    public void settResMetafacture(String tResMetafacture) {
        this.tResMetafacture = tResMetafacture;
    }

    public String gettTraces() {
        return tTraces;
    }

    public void settTraces(String tTraces) {
        this.tTraces = tTraces;
    }
}
