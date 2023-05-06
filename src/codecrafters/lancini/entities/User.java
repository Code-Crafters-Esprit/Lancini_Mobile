package codecrafters.lancini.entities;

/**
 *
 * @author Youssef-Ayed
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
    private String numTel;
    
    public User() {
    }

    public User(String Nom, String Prenom, String email, String motDePasse, String role, String bio, String photoPath) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
        this.bio = bio;
        this.photoPath = photoPath;
    }

    public User(int idUser, String Nom, String Prenom, String email, String motDePasse, String role, String bio, String photoPath, String numTel) {
        this.idUser = idUser;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
        this.bio = bio;
        this.photoPath = photoPath;
        this.numTel = numTel;
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

//    public User(String string, String string0, String string1, String string2, String string3, String string4, String string5) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    public User(int idUser) {
        this.idUser = idUser;
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

    public User(int idUser, String Nom) {
        this.idUser = idUser;
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

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }
    
    

    @Override
    public String toString() {
        return "User{" + "idUser=" + idUser + ", Nom=" + Nom + ", Prenom=" + Prenom + ", email=" + email + ", motDePasse=" + motDePasse + ", role=" + role + ", bio=" + bio + ", photoPath=" + photoPath + '}';
    }

}