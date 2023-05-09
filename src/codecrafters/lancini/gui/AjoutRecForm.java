/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import codecrafters.lancini.entities.Reclamation;
import codecrafters.lancini.myapp.MyApplication;
import codecrafters.lancini.service.ServiceRec;
import com.codename1.ui.Form;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author ezzar
 */
public class AjoutRecForm extends Form {
   
    public AjoutRecForm(Form previous) {
        super("Ajouter une réclamation");
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, ev -> {
            previous.showBack();});
       
        
         TextField nomField = new TextField("", "Nom");
        TextField prenomField = new TextField("", "Prénom");
        TextField descriptionField = new TextField("", "Description");
        TextField sujetdereclamationsField = new TextField("", "Sujet de la réclamation");
        TextField emailField = new TextField("", "E-mail");
        TextField telField = new TextField("", "Téléphone");
        TextField etatField = new TextField("", "Etat");

        
        Button addBtn = new Button("Soumettre");
        addBtn.addActionListener(e -> {
            
            try {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String description = descriptionField.getText();
                String sujet = sujetdereclamationsField.getText();
                String email = emailField.getText();
                String tel = telField.getText();
                String etat = etatField.getText();

                // Check that the entered values are valid
                if (nom.isEmpty() || prenom.isEmpty() || description.isEmpty() || sujet.isEmpty() || email.isEmpty() || tel.isEmpty() || etat.isEmpty()) {
                    Dialog.show("Erreur", "Veuillez remplir tous les champs", "OK", null);
                    return;
                }

                // Create a new réclamation with the entered information
                Reclamation reclamation = new Reclamation();
                reclamation.setNom(nom);
                reclamation.setPrenom(prenom);
                reclamation.setDescription(description);
                reclamation.setSujetdereclamations(sujet);
                reclamation.setEmail(email);
                reclamation.setTel(tel);
                reclamation.setEtat(etat);

                ServiceRec.getInstance().addReclamation(reclamation);
 new AfficherRecForm(this).show();
                // Show a confirmation message and return to the home page
                Dialog.show("Confirmation", "Bonjour, votre réclamation a été déposée sur LANCINI.", "OK", null);
               
            } catch (NumberFormatException ex) {
                // Handle the case where the entered values are not valid numbers
                Dialog.show("Erreur", "Veuillez saisir des valeurs valides", "OK", null);
            }
        });

        // Add the text fields and combo box to the form
        add(nomField);
        add(prenomField);
        add(descriptionField);
        add(sujetdereclamationsField);
        add(emailField);
        add(telField);
        add(etatField);
        add(addBtn);
    }


   //public void showAdd() {
   //AjoutRecForm form = new AjoutRecForm();
    //form.show();
 //}
}
