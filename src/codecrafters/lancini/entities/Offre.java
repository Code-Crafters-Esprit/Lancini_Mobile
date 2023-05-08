/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.entities;


/**
 *
 * @author user
 */
public class Offre {
     private int idOffre, idSecteur;
    private User proprietaire ;
    private String nom  , secteur , description , competence, nomProprietaire;
    private String   dateDebut , dateFin;      
    private String typeOffre ;
  
     private enum TypeOffre {  
              Temps_plein , Temps_partiel ,Freelance,Stage, Alternance ,CDD ;
    }

    public Offre() {
    }

    public Offre(int idOffre, User proprietaire, String nom, int idSecteur, String description, String competence, String dateDebut, String dateFin, String typeOffre) {
        this.idOffre = idOffre;
        this.proprietaire = proprietaire;
        this.nom = nom;
        this.idSecteur = idSecteur;
        this.description = description;
        this.competence = competence;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeOffre = typeOffre;
    }

    public Offre(User proprietaire, String nom, int idSecteur, String description, String competence, String dateDebut, String dateFin, String typeOffre) {
        this.proprietaire = proprietaire;
        this.nom = nom;
        this.idSecteur= idSecteur;
        this.description = description;
        this.competence = competence;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.typeOffre = typeOffre;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdSecteur() {
        return idSecteur;
    }

    public void setIdSecteur(int idSecteur) {
        this.idSecteur = idSecteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

   

    public String getTypeOffre() {
        return typeOffre;
    }

    public void setTypeOffre(String typeOffre) {
        this.typeOffre = typeOffre;
    }

    public String getNomProprietaire() {
        return nomProprietaire;
    }

    public void setNomProprietaire(String nomProprietaire) {
        this.nomProprietaire = nomProprietaire;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    @Override
    public String toString() {
        return "Offre{" + "idOffre=" + idOffre + ", idSecteur=" + idSecteur + ", proprietaire=" + proprietaire + ", nom=" + nom + ", description=" + description + ", competence=" + competence + ", nomProprietaire=" + nomProprietaire + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", typeOffre=" + typeOffre + '}';
    }
    
}
