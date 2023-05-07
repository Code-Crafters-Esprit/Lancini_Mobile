/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import codecrafters.lancini.service.ServiceRec;
import codecrafters.lancini.entities.Reclamation;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;

import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.Label;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
/**
 *
 * @author ezzar
 */
import java.util.ArrayList;
public class AfficherRecForm extends Form {

    private final ArrayList<Reclamation> reclamations;

    public AfficherRecForm(Form previous) {
    super("Liste des réclamations");

    getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD,
            e -> {
                new AjoutRecForm(this).show();
            });

    // Récupérer toutes les réclamations du serveur
    reclamations = ServiceRec.getInstance().getAllReclamations();

    // Créer un conteneur pour stocker les réclamations
    Container reclamationContainer = new Container();
    // Ajouter chaque réclamation au conteneur avec des boutons pour la modifier et la supprimer
    for (Reclamation r : reclamations) {
        // Créer un conteneur pour stocker les informations et boutons de la réclamation
        Container reclamationRow = new Container(new BorderLayout());
        reclamationRow.setUIID("ReclamationBox");

        // Créer des labels pour afficher les informations de la réclamation
        Label idLabel = new Label("ID: " + r.getId());
        idLabel.setUIID("ReclamationLabel");

        Label nomLabel = new Label("Nom: " + r.getNom());
        nomLabel.setUIID("NomLabel");
        Label prenomLabel = new Label("prenom: " + r.getPrenom());
        prenomLabel.setUIID("PrenomLabel");

        Label descriptionLabel = new Label("Description: " + r.getDescription());
        descriptionLabel.setUIID("DescriptionLabel");

        Label SujetdereclamationsLabel = new Label("Sujetdereclamations: " + r.getSujetdereclamations());
        SujetdereclamationsLabel.setUIID("SujetdereclamationsLabel");
        Label emailLabel = new Label("Email: " + r.getEmail());
        emailLabel.setUIID("EmailLabel");
        Label telLabel = new Label("Tel: " + r.getTel());
        telLabel.setUIID("TelLabel");
        Label etatLabel = new Label("Etat: " + r.getEtat());
        etatLabel.setUIID("Etatabel");

        // Créer des boutons pour modifier et supprimer la réclamation
       // Button modifierBtn = new Button("Modifier");
        //modifierBtn.addActionListener(e -> {
          //  new ModifierRecForm(this, r).show();
        //});

        //Button supprimerBtn = new Button("Supprimer");
        //supprimerBtn.addActionListener(a -> {
          //  ServiceRec.getInstance().deleteReclamation(r.getId());

            //reclamationContainer.removeComponent(reclamationRow);
        //});

        // Ajouter les labels à la ligne de la réclamation
        Container labelsContainer = new Container(new GridLayout(4, 1));
        labelsContainer.add(idLabel);
        labelsContainer.add(nomLabel);
        labelsContainer.add(descriptionLabel);
        labelsContainer.add(SujetdereclamationsLabel);

        reclamationRow.add(BorderLayout.CENTER, labelsContainer);

        // Créer un conteneur pour stocker les boutons
        Container buttonsContainer = new Container(new GridLayout(2, 1));
        buttonsContainer.setUIID("ReclamationButtonBox");
       // buttonsContainer.add(modifierBtn);
        //buttonsContainer.add(supprimerBtn);

        reclamationRow.add(BorderLayout.EAST, buttonsContainer);

        // Ajouter la ligne de réclamation au conteneur
        reclamationContainer.add(reclamationRow);
    }

    // Ajouter le conteneur au formulaire
    add(reclamationContainer);
}

public void AfficherRecList() {
    AfficherRecForm form = new AfficherRecForm();
    form.show();
}


    
  
    
}
