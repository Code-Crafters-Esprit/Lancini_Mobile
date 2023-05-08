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
public class Secteur {
    private int IdSecteur  ; 
    private String nom, description  ; 
    private String DateCreation , DateModification ; 

    public Secteur() {
    }

    public Secteur(int IdSecteur, String nom, String description, String DateCreation, String DateModification) {
        this.IdSecteur = IdSecteur;
        this.nom = nom;
        this.description = description;
        this.DateCreation = DateCreation;
        this.DateModification = DateModification;
    }

    public Secteur(String nom, String description, String DateCreation, String DateModification) {
        this.nom = nom;
        this.description = description;
        this.DateCreation = DateCreation;
        this.DateModification = DateModification;
    }

    public int getIdSecteur() {
        return IdSecteur;
    }

    public void setIdSecteur(int IdSecteur) {
        this.IdSecteur = IdSecteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(String DateCreation) {
        this.DateCreation = DateCreation;
    }

    public String getDateModification() {
        return DateModification;
    }

    public void setDateModification(String DateModification) {
        this.DateModification = DateModification;
    }

    

    @Override
    public String toString() {
        return "Secteur{" + "IdSecteur=" + IdSecteur + ", nom=" + nom + ", description=" + description + ", DateCreation=" + DateCreation + ", DateModification=" + DateModification + '}';
    }
    
    
}
