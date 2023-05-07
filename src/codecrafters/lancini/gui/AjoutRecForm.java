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
            previous.showBack();
        });
       
        // Create text fields for the réclamation information
        TextField nomField = new TextField("", "Nom");
        TextField prenomField = new TextField("", "Prénom");
        TextField descriptionField = new TextField("", "Description");
        TextField sujetField = new TextField("", "Sujet");
        TextField emailField = new TextField("", "Email");
        TextField telField = new TextField("", "Téléphone");
        TextField etatField = new TextField("", "Etat");

        // Create a button to submit the réclamation
        Button submitBtn = new Button("Soumettre");
        submitBtn.addActionListener(e -> {
            // Validate the entered values
            try {
                String nom = nomField.getText().trim();
                String prenom = prenomField.getText().trim();
                String description = descriptionField.getText().trim();
                String sujet = sujetField.getText().trim();
                String email = emailField.getText().trim();
                String tel = telField.getText().trim();
                String etat = etatField.getText().trim();

                // Check that the entered values are valid
                if (nom.isEmpty() || prenom.isEmpty() || description.isEmpty() || sujet.isEmpty() || email.isEmpty() || tel.isEmpty() || etat.isEmpty()) {
                    Dialog.show("Erreur", "Veuillez saisir des valeurs valides pour tous les champs", "OK", null);
                    return;
                }

                // Create a new réclamation with the entered information
                Reclamation rec = new Reclamation();
                rec.setNom(nom);
                rec.setPrenom(prenom);
                rec.setDescription(description);
                rec.setSujet(sujet);
                rec.setEmail(email);
                rec.setTel(tel);
                rec.setEtat(etat);
               
               ServiceRec.getInstance().addReclamation(rec);

                // Display a confirmation message
                Dialog.show("Confirmation", "Bonjour, votre réclamation a été déposée sur LANCINI.", "OK", null);

                // Return to the home page
                previous.showBack();
            } catch (NumberFormatException ex) {
                // Handle the case where the entered values are not valid numbers
                Dialog.show("Erreur", "Veuillez saisir des entrées valides", "OK", null);
            }
        });
       
        // Add the text fields to the form
        add(nomField);
        add(prenomField);
        add(descriptionField);
        add(sujetField);
        add(emailField);
        add(telField);
        add(etatField);

        add(submitBtn);
    }
}
