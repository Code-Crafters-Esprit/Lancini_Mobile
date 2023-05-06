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

/**
 *
 * @author ezzar
 */
public class AjoutRecForm extends Form {

    private TextField nomField, prenomField, emailField, descriptionField, telField;
    private ComboBox<String> sujetComboBox, etatComboBox;

    public AjoutRecForm() {
        super("Poser une réclamation", BoxLayout.y());

        // Nom
        Label nomLabel = new Label("Nom :");
        nomField = new TextField();
        addField(nomLabel, nomField);

        // Prénom
        Label prenomLabel = new Label("Prénom :");
        prenomField = new TextField();
        addField(prenomLabel, prenomField);

        // Email
        Label emailLabel = new Label("Email :");
        emailField = new TextField();
        emailField.setConstraint(TextField.EMAILADDR);
        addField(emailLabel, emailField);

        // Sujet de la réclamation
        Label sujetLabel = new Label("Sujet de la réclamation :");
        sujetComboBox = new ComboBox<>(new String[]{"Choisissez un sujet...", "Sujet 1", "Sujet 2", "Sujet 3"});
        addField(sujetLabel, sujetComboBox);

        // Description
        Label descriptionLabel = new Label("Description :");
        descriptionField = new TextField();
        addField(descriptionLabel, descriptionField);

        // Téléphone
        Label telLabel = new Label("Téléphone :");
        telField = new TextField();
        telField.setConstraint(TextField.PHONENUMBER);
        addField(telLabel, telField);

        // État de la réclamation
        Label etatLabel = new Label("État de la réclamation :");
        etatComboBox = new ComboBox<>(new String[]{"En attente", "En cours de traitement", "Terminé"});
        addField(etatLabel, etatComboBox);

        // Bouton pour ajouter la réclamation
        Button ajouterButton = new Button("Ajouter la réclamation");
        ajouterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Ajouter la réclamation ici
            }
        });
        addComponent(ajouterButton);

        // Bouton pour retourner à la page d'accueil
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                HomePageForm homePageForm = new HomePageForm();
                homePageForm.showBack();
            }
        });
        addComponent(retourButton);

        // Bouton pour afficher les détails de la réclamation
        Button detailsButton = new Button("Afficher les détails");
        detailsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                AfficherRecForm afficherRecForm = new AfficherRecForm();
                afficherRecForm.show();
            }
        });
        addComponent(detailsButton);
    }

    private void addField(Label label, TextField textField) {
        Container container = new Container(new BorderLayout());
        container.add(BorderLayout.WEST, label);
        container.add(BorderLayout.CENTER, textField);
        addComponent(container);
    }

    private void addField(Label sujetLabel, ComboBox<String> sujetComboBox) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
