/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import codecrafters.lancini.entities.Reclamation;
import codecrafters.lancini.service.ServiceRec;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

/**
 *
 * @author ezzar
 */
public class ModifierRecForm extends Form {

    private Resources res;
    private ServiceRec serviceRec = ServiceRec.getInstance();
    private ArrayList<Reclamation> reclamations = serviceRec.getAllReclamations();

    public ModifierRecForm(AfficherRecForm aThis, Reclamation r) {
    super("Modifier ou supprimer votre réclamation");
    
    // Ajouter les champs de modification
    TextField nomField = new TextField(r.getNom());
    TextField prenomField = new TextField(r.getPrenom());
    TextField descriptionField = new TextField(r.getDescription());
    TextField sujetdereclamationsField = new TextField(r.getSujetdereclamations());
    TextField emailField = new TextField(r.getEmail());
    TextField telField = new TextField(r.getTel());
    CheckBox etatField = new CheckBox("Traitée");
    etatField.setSelected(r.getEtat().equals("Traitée"));
    
    // Ajouter les boutons de modification et suppression
    Button modifierButton = new Button("Modifier");
    Button supprimerButton = new Button("Supprimer");
    
    // Ajouter les actions de modification et suppression
    modifierButton.addActionListener((evt) -> {
        // Récupérer les nouvelles valeurs
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String description = descriptionField.getText();
        String sujetdereclamations = sujetdereclamationsField.getText();
        String email = emailField.getText();
        String tel = telField.getText();
        String etat = etatField.isSelected() ? "Traitée" : "Non traitée";
        
        // Modifier la réclamation dans la base de données
        boolean success = ServiceRec.getInstance().modifierReclamation(r.getId(), nom, prenom, description, sujetdereclamations, email, tel, etat);
        
        if (success) {
            // Afficher un message de confirmation
            Dialog.show("Succès", "La réclamation a été modifiée avec succès.", "OK", null);
            
            // Actualiser la liste des réclamations
            aThis.refresh();
            
            // Fermer la fenêtre
            this.dispose();
        } else {
            // Afficher un message d'erreur
            Dialog.show("Erreur", "Une erreur est survenue lors de la modification de la réclamation.", "OK", null);
        }
    });
    
    supprimerButton.addActionListener((evt) -> {
        // Supprimer la réclamation de la base de données
        boolean success = ServiceRec.getInstance().deleteReclamation(r.getId());
        
        if (success) {
            // Afficher un message de confirmation
            Dialog.show("Succès", "La réclamation a été supprimée avec succès.", "OK", null);
            
            // Actualiser la liste des réclamations
            aThis.refresh();
            
            // Fermer la fenêtre
            this.dispose();
        } else {
            // Afficher un message d'erreur
            Dialog.show("Erreur", "Une erreur est survenue lors de la suppression de la réclamation.", "OK", null);
        }
    });
    
    // Ajouter les champs et les boutons à la forme
    this.add("Nom", nomField);
    this.add("Prénom", prenomField);
    this.add("Description", descriptionField);
    this.add("Sujet de réclamation", sujetdereclamationsField);
    this.add("E-mail", emailField);
    this.add("Téléphone", telField);
    this.add("État", etatField);
    this.add(modifierButton);
    this.add(supprimerButton);
}

    private void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }}
