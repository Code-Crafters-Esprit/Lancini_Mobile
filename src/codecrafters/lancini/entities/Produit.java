package codecrafters.lancini.entities;

import codecrafters.lancini.entities.User;
import java.util.Date;

public class Produit {
    private int idProduit;
    private String nom, categorie, description, image;
    private float prix;
    private Date date;
     private User vendeur;
     private int idVendeur;

    public Produit() {
    }

    public Produit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getIdVendeur() {
        return this.idVendeur;
    }
       public User getVendeur() {
        return vendeur;
    }

    public void setVendeur(User vendeur) {
        this.vendeur = vendeur;
    }
    public void setIdVendeur(int vendeur) {
        this.idVendeur = vendeur;
    }

    public Produit(String nom, String categorie, String description, String image, float prix, Date date, User vendeur) {
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.date = date;
        this.vendeur = vendeur;
    }

    public Produit(int idProduit, String nom, String categorie, String description, String image, float prix, Date date, User vendeur) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.date = date;
        this.vendeur = vendeur;
    }

    public Produit(int idProduit, String nom, String categorie, String description, String image, float prix, Date date) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.date = date;
    }

    public Produit(String nom, String categorie, String description, String image, float prix, Date date) {
        this.nom = nom;
        this.categorie = categorie;
        this.description = description;
        this.image = image;
        this.prix = prix;
        this.date = date;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "idProduit=" + idProduit +               
                ", categorie='" + categorie + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", prix=" + prix +
                ", date=" + date +
                '}';
    }
}