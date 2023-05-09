package codecrafters.lancini.entities;

public class Reclamation {
    private int id;
     private String nom;
      private String prenom;
       private String description;
       private String sujetdereclamations;
        private String email;
         private String tel;
          private String etat;

    public Reclamation(int id, String nom, String prenom, String description, String sujetdereclamations, String email, String tel, String etat) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.description = description;
        this.sujetdereclamations = sujetdereclamations;
        this.email = email;
        this.tel = tel;
              this.etat = etat;
    }

    public Reclamation(String nom, String prenom, String description, String sujetdereclamations, String email, String tel, String etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.description = description;
        this.sujetdereclamations = sujetdereclamations;
        this.email = email;
        this.tel = tel;
              this.etat = etat;
    }

    public Reclamation() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSujetdereclamations() {
        return sujetdereclamations;
    }

    public void setSujetdereclamations(String sujetdereclamations) {
        this.sujetdereclamations = sujetdereclamations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    
    
    

   
@Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ",description=" + description + ",sujetdereclamations=" + sujetdereclamations + ",email=" + email + ",tel=" + tel + ",etat=" + etat + '}';
    }

  
   

   

    

   

    

  

   
   

    
}
