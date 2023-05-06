/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

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
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author ezzar
 */
public class AjoutRecForm extends Form {

    public AjoutRecForm() {
        super("Poser une réclamation", BoxLayout.y());

        // Ajouter un bouton "Retour"
        Button btnRetour = new Button("Retour");
        btnRetour.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Retourner à la page précédente
                showBack();
            }
        });
        addComponent(btnRetour);

        // Ajouter les champs pour la réclamation
        TextField tfNom = new TextField("", "Nom");
        addComponent(tfNom);

        TextField tfPrenom = new TextField("", "Prénom");
        addComponent(tfPrenom);

        TextField tfDescription = new TextField("", "Description");
        addComponent(tfDescription);

        TextField tfSujet = new TextField("", "Sujet");
        addComponent(tfSujet);

        TextField tfEmail = new TextField("", "E-mail");
        addComponent(tfEmail);

        TextField tfTel = new TextField("", "Téléphone");
        addComponent(tfTel);

        TextField tfEtat = new TextField("", "État");
        addComponent(tfEtat);

   // Ajouter un bouton pour soumettre la réclamation
Button btnSubmit = new Button("Soumettre");
btnSubmit.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evt) {
        // Récupérer les valeurs saisies
        String nom = tfNom.getText();
        String prenom = tfPrenom.getText();
        String description = tfDescription.getText();
        String sujet = tfSujet.getText();
        String email = tfEmail.getText();
        String tel = tfTel.getText();
        String etat = tfEtat.getText();

        // Effectuer les contrôles de saisie
        if (nom.length() == 0 || prenom.length() == 0 || description.length() == 0 || sujet.length() == 0 || email.length() == 0 || tel.length() == 0 || etat.length() == 0) {
            Dialog.show("Erreur", "Veuillez remplir tous les champs.", "OK", null);
        } else if (!isValidEmail(email)) {
            Dialog.show("Erreur", "Veuillez saisir une adresse email valide.", "OK", null);
        } else if (!isValidTel(tel)) {
            Dialog.show("Erreur", "Veuillez saisir un numéro de téléphone valide.", "OK", null);
        } else {
            // Envoyer la réclamation ici ...

            // Afficher un message de confirmation
            Dialog.show("Confirmation", "Bonjour, votre réclamation a été déposée sur LANCINI.", "OK", null);

            // Retourner à la page d'accueil
            showBack();
        }
    }

            private boolean isValidEmail(String email) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            private boolean isValidTel(String tel) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
addComponent(btnSubmit);


    }
}