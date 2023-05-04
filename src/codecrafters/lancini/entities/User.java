/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.entities;

/**
 *
 * @author LENOVO
 */

public class User {
    private int idUser; 
    private String Nom; 
    private String Prenom; 
    private String email;
    private String motDePasse; 
    private String role; 
    private String bio; 
    private String photoPath;
    
    public User() {
    }
    
    public User(int idUser, String Nom, String Prenom, String email, String motDePasse, String role, String bio, String photoPath) {
        this.idUser = idUser;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
        this.bio = bio;
        this.photoPath = photoPath;
    }

    User(Integer idUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", Nom=" + Nom + ", Prenom=" + Prenom + ", email=" + email + ", motDePasse=" + motDePasse + ", role=" + role + ", bio=" + bio + ", photoPath=" + photoPath + '}';
    }

}
