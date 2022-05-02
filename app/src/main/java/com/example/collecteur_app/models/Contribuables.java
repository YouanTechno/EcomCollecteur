package com.example.collecteur_app.models;

import java.util.ArrayList;
import java.util.List;

public class Contribuables {
   public int CONT_ID;
   public String CONT_NOM;
   public String CONT_PRENOM;
   public String CONT_NCC;
   public String CONT_PIECE_CODE;
   public String CONT_NUM_PIECE;
   public String CONT_MATRICULE;
   public String CONT_TELEPHONE;
   public String CONT_NAT_ID;
   public int[] ACTIVITE ;
   public String CONT_LOGIN;
   public String CONT_PASSWORD;
   public float COORDONNE_X;
   public float COORDONNE_Y;


   public int getCONT_ID() {
      return CONT_ID;
   }

   public void setCONT_ID(int CONT_ID) {
      this.CONT_ID = CONT_ID;
   }

   public String getCONT_NOM() {
      return CONT_NOM;
   }

   public void setCONT_NOM(String CONT_NOM) {
      this.CONT_NOM = CONT_NOM;
   }

   public String getCONT_PRENOM() {
      return CONT_PRENOM;
   }

   public void setCONT_PRENOM(String CONT_PRENOM) {
      this.CONT_PRENOM = CONT_PRENOM;
   }

   public String getCONT_NCC() {
      return CONT_NCC;
   }

   public void setCONT_NCC(String CONT_NCC) {
      this.CONT_NCC = CONT_NCC;
   }

   public String getCONT_PIECE_CODE() {
      return CONT_PIECE_CODE;
   }

   public void setCONT_PIECE_CODE(String CONT_PIECE_CODE) {
      this.CONT_PIECE_CODE = CONT_PIECE_CODE;
   }

   public String getCONT_NUM_PIECE() {
      return CONT_NUM_PIECE;
   }

   public void setCONT_NUM_PIECE(String CONT_NUM_PIECE) {
      this.CONT_NUM_PIECE = CONT_NUM_PIECE;
   }

   public String getCONT_MATRICULE() {
      return CONT_MATRICULE;
   }

   public void setCONT_MATRICULE(String CONT_MATRICULE) {
      this.CONT_MATRICULE = CONT_MATRICULE;
   }

   public String getCONT_TELEPHONE() {
      return CONT_TELEPHONE;
   }

   public void setCONT_TELEPHONE(String CONT_TELEPHONE) {
      this.CONT_TELEPHONE = CONT_TELEPHONE;
   }

   public String getCONT_NAT_ID() {
      return CONT_NAT_ID;
   }

   public void setCONT_NAT_ID(String CONT_NAT_ID) {
      this.CONT_NAT_ID = CONT_NAT_ID;
   }


   public String getCONT_LOGIN() {
      return CONT_LOGIN;
   }

   public void setCONT_LOGIN(String CONT_LOGIN) {
      this.CONT_LOGIN = CONT_LOGIN;
   }

   public String getCONT_PASSWORD() {
      return CONT_PASSWORD;
   }

   public void setCONT_PASSWORD(String CONT_PASSWORD) {
      this.CONT_PASSWORD = CONT_PASSWORD;
   }

   public int[] getACTIVITE() {
      return ACTIVITE;
   }

   public void setACTIVITE(int[] ACTIVITE) {
      this.ACTIVITE = ACTIVITE;
   }

   public float getCOORDONNE_X() {
      return COORDONNE_X;
   }

   public void setCOORDONNE_X(float COORDONNE_X) {
      this.COORDONNE_X = COORDONNE_X;
   }

   public float getCOORDONNE_Y() {
      return COORDONNE_Y;
   }

   public void setCOORDONNE_Y(float COORDONNE_Y) {
      this.COORDONNE_Y = COORDONNE_Y;
   }

   public Contribuables(String CONT_NOM, String CONT_PRENOM, String CONT_NUM_PIECE, String CONT_TELEPHONE, String CONT_NAT_ID, int[] ACTIVITE, String CONT_LOGIN, String CONT_PASSWORD, float COORDONNE_X, float COORDONNE_Y) {
      this.CONT_NOM = CONT_NOM;
      this.CONT_PRENOM = CONT_PRENOM;
      this.CONT_NUM_PIECE = CONT_NUM_PIECE;
      this.CONT_TELEPHONE = CONT_TELEPHONE;
      this.CONT_NAT_ID = CONT_NAT_ID;
      this.ACTIVITE = ACTIVITE;
      this.CONT_LOGIN = CONT_LOGIN;
      this.CONT_PASSWORD = CONT_PASSWORD;
      this.COORDONNE_X = COORDONNE_X;
      this.COORDONNE_Y = COORDONNE_Y;
   }
}
