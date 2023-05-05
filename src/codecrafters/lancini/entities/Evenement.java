/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.entities;

/**
 *
 * @author Samar
 */
public class Evenement {
    private int idEvent;
    private int proprietaire;
    private String titre;
    private String sujet;
    private String lieu;
   // private double latitude;
    // private double longitude;
    private String horaire;
    private String dateEvent;

    public Evenement() {
    }

    public Evenement(int idEvent, int proprietaire, String titre, String sujet, String lieu, String horaire, String dateEvent) {
        this.idEvent = idEvent;
        this.proprietaire = proprietaire;
        this.titre = titre;
        this.sujet = sujet;
        this.lieu = lieu;
        this.horaire = horaire;
        this.dateEvent = dateEvent;
    }

    public Evenement(int proprietaire, String titre, String sujet, String lieu, String horaire, String dateEvent) {
        this.proprietaire = proprietaire;
        this.titre = titre;
        this.sujet = sujet;
        this.lieu = lieu;
        this.horaire = horaire;
        this.dateEvent = dateEvent;
    }



    public Evenement(String titre) {
        this.titre = titre;
    }

    public Evenement(int idEvent) {
        this.idEvent = idEvent;
    }

    public Evenement(int idEvent, int proprietaire) {
        this.idEvent = idEvent;
        this.proprietaire = proprietaire;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }


     public int getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(int proprietaire) {
        this.proprietaire = proprietaire;
    }
   

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getLieu() {
        return lieu;
    }

//     public void setLocation(double latitude, double longitude) {
//        this.latitude = latitude;
//        this.longitude = longitude;
//        this.lieu = String.format("(%f, %f)", latitude, longitude);
//    }
    
    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvent=" + idEvent + ", proprietaire=" + proprietaire + ", titre=" + titre + ", sujet=" + sujet + ", lieu=" + lieu + ", horaire=" + horaire + ", dateEvent=" + dateEvent + '}';
    }


    
    
    
    
    
    
    
    
}
