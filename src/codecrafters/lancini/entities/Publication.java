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
public class Publication {
    private int idPub ;
    private User proprietaire ;
    private String libelle ;
    private String datePub; 
    private String description ;
    private Category cat;
    
    public enum Category {
    SIMPLE_BLOG, SUCCESS_STORY, CONSEIL;
    }

    public Publication() {
    }

    public Publication(int idPub, User proprietaire, String libelle, String datePub, String description, Category cat) {
        this.idPub = idPub;
        this.proprietaire = proprietaire;
        this.libelle = libelle;
        this.datePub = datePub;
        this.description = description;
        this.cat = cat;
    }

    public Publication(User proprietaire, String libelle, String datePub, String description, Category cat) {
        this.proprietaire = proprietaire;
        this.libelle = libelle;
        this.datePub = datePub;
        this.description = description;
        this.cat = cat;
    }

    
       
//    public Publication(int proprietaire, String libelle, String datePub, String description,Category cat) {
//        this.proprietaire = proprietaire;
//        this.libelle = libelle;
//        this.datePub = datePub;
//        this.description = description;
//        this.cat=cat;
//    }
 
    
//    public Publication(int idPub, int proprietaire, String libelle, String datePub, String description, String sujet,Category cat) {
//        this.idPub = idPub;
//        this.proprietaire = proprietaire;
//        this.libelle = libelle;
//        this.datePub = datePub;
//        this.description = description;
//        this.cat=cat;
//    }

   

    public Publication(int idPub, User proprietaire) {
        this.idPub = idPub;
        this.proprietaire = proprietaire;
    }

    public Publication(String libelle) {
        this.libelle = libelle;
    }

    public Category getCat() {
        return cat;
    }

    public void setCat(Category cat) {
        this.cat = cat;
    }

    public int getIdPub() {
        return idPub;
    }

    public void setIdPub(int idPub) {
        this.idPub = idPub;
    }

    public int getProprietaire() {
        return proprietaire.getIdUser();
    }
    
      public User getUser() {
        return proprietaire;
    }
    
     

    public void setProprietaire(User proprietaire) {
        this.proprietaire = proprietaire;
    }



    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDatePub() {
        return datePub;
    }

    public void setDatePub(String datePub) {
        this.datePub = datePub;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    @Override
    public String toString() {
        return "Publication{" + "idPub=" + idPub + ", proprietaire=" + proprietaire + ", libelle=" + libelle + ", datePub=" + datePub + ", description=" + description + ", cat=" + cat + '}';
    }





    
}
