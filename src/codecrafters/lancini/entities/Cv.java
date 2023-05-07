/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.entities;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Flija Yosra
 */
public class Cv {
    private int idCv , userid_id  ;
    private String nom , prenom ,sexe ,adresse,email ;
    private String dateNaissance ;
    private String langue,cin ; 
    private String education; 

 
      public Cv(){
    }

    public Cv(int idcv, String cin, String nom, String prenom, String sexe,String dateNaissance ,String adresse, String email, String langue, String education) {
        this.idCv = idcv;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.langue = langue;
        this.education = education;
    }

    
    public Cv(int idcv, int userid_id, String cin, String nom, String prenom, String sexe,String dateNaissance, String adresse, String email, String langue, String education) {
        this.userid_id = userid_id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.adresse = adresse;
        this.email = email;
        this.langue = langue;
        this.education = education;
        this.idCv = idcv;
    }
    

    public Cv(int idcv, String cin, String nom, String prenom, String sexe, String dateNaissance , String adresse, String email, String langue, String education,int userid_id ) {
        this.idCv = idcv;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.langue = langue;
        this.education = education;
        this.userid_id  = userid_id ;

    }

    public Cv(String cin, String nom, String prenom, String sexe, String adresse, String email, String dateNaissance, String langue, String education) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.langue = langue;
        this.education = education;
    }
    

    public Cv(String cin, String nom, String prenom, String sexe, String adresse, String email, String dateNaissance, String langue, String education,int userid_id ) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.adresse = adresse;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.langue = langue;
        this.education = education;
        this.userid_id  = userid_id ;

    }



    public String getCin() {
        return cin;
    }

    
    public String getNom() {
        return nom;
    }  
    public String getPrenom() {
        return prenom;
    }  
     public String getSexe() {
        return sexe;
    }  
      public String getDateNaissance() {
        return dateNaissance;
    }
     
        public String getAdresse() {
        return adresse;
    } 
         public String getEmail() {
        return email;
    } 

    public int getUserid_id() {
        return userid_id;
    }
        

    public String getLangue() {
        return langue;
    }

    public String getEducation() {
        return education;
    }

    public void setIdCv(int idcv) {
        this.idCv = idcv;
    }
            

    public void setCin(String cin) {
        this.cin = cin;
    }
    
    public void setnom(String nom) {
        this.nom=nom ;
    }  
    public void setprenom(String prenom ) {
        this.prenom= prenom;
    }  
     public void setsexe(String sexe ) {
        this.sexe=sexe ;
    }  
      public void setdateNaissance(String dateNaissance ) {
        this.dateNaissance=dateNaissance;
    }
      
        public void setadresse(String adresse) {
        this.adresse=adresse;
    } 
         public void setemail(String email) {
        this.email = email;
    } 

    public void setUserid_id(int userid_id) {
        this.userid_id = userid_id;
    }

    public int getIdCv() {
        return idCv;
    }
      

    public void setLangue(String langue) {
        this.langue = langue;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String toString() {
        return "Cv{" + "idcv=" + idCv + ", cin=" + cin + ", userid_id =" + userid_id  + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", adresse=" + adresse + ", email=" + email + ", dateNaissance=" + dateNaissance + ", langue=" + langue + ", education=" + education + '}';
    }
    
    
            
    

     
        
        
        
        
        
        
        
        
    
    
            
}
