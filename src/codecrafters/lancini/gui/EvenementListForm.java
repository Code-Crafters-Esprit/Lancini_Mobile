/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

/**
 *
 * @author Samar
 */
import codecrafters.lancini.entities.Evenement;
import codecrafters.lancini.service.EvenementService;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;

import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.Label;

import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;


import java.util.ArrayList;

public class EvenementListForm extends Form {

    private ArrayList<Evenement> Evenements;

    public EvenementListForm(Form previous) {
        super("Lancini Events");

        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD,
                e -> {
                    new EvenementAddForm(this).show();
                });
        
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                ev -> {previous.showBack();} );
        

        //getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD,
        //          e -> {
        //EvenementAddForm form = new EvenementAddForm();
        //form.showAdd();
        //});
        // Get all events from the server
        Evenements = EvenementService.getInstance().getAllEvenements();

        // Create a container to hold the events
        Container evenementContainer = new Container();
//        evenementContainer.getStyle().setPaddingTop(20);
//        evenementContainer.getStyle().setPaddingBottom(20);
//        evenementContainer.getStyle().setMarginLeft(10);
//        evenementContainer.getStyle().setMarginRight(10);
        // evenementContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        //evenementContainer.setScrollableY(true);        
        // Add each event to the container with buttons to reserve, edit, and delete it
        for (Evenement e : Evenements) {
            // Create a container to hold the event's information and buttons
            Container evenementRow = new Container(new BorderLayout());
            evenementRow.setUIID("EvenementBox");

            // Create labels to display the event's information
            //Label idLabel = new Label("ID: " + e.getIdEvent());
            //idLabel.setUIID("EventLabel");

            Label titreLabel = new Label( e.getTitre());
            titreLabel.setUIID("TitreLabel");
            Style titreLabelStyle = titreLabel.getUnselectedStyle();
            titreLabelStyle.setFgColor(0x5AB9EA); // Set foreground color to red
            titreLabel.setUnselectedStyle(titreLabelStyle);

            Label sujetLabel = new Label("Sujet: " + e.getSujet());
            sujetLabel.setUIID("SujetLabel");

            Label lieuLabel = new Label("Lieu: " + e.getLieu());
            lieuLabel.setUIID("LieuLabel");

            Label horaireLabel = new Label("Horaire: " + e.getHoraire());
            horaireLabel.setUIID("horaireLabel");

            Label dateLabel = new Label("Date: " + e.getDateEvent());
            dateLabel.setUIID("dateLabel");

//            Label proprietaireLabel = new Label("Proprietaire: " + e.getProprietaire());
//            proprietaireLabel.setUIID("proprietaireLabel");

            // Create buttons to reserve, edit, and delete the event
            //Button reserveBtn = new Button("Add");
            //  reserveBtn.addActionListener(e -> {
            // Reserve the event
            //});
            // Button editBtn = new Button("Edit");
            //editBtn.addActionListener(e -> {
            //  new EditVeloForm(user).show();
            //});
            Button deleteBtn = new Button("Delete");
            deleteBtn.addActionListener(a -> {
                EvenementService.getInstance().deleteEvenement(e.getIdEvent());

                evenementContainer.removeComponent(evenementRow);
            });

            // Add the labels to the event row
            Container labelsContainer = new Container(new GridLayout(5, 1));
//            labelsContainer.add(idLabel);
            labelsContainer.add(titreLabel);
            labelsContainer.add(sujetLabel);
            labelsContainer.add(lieuLabel);
            labelsContainer.add(horaireLabel);
            labelsContainer.add(dateLabel);
//            labelsContainer.add(proprietaireLabel);
            labelsContainer.add(deleteBtn);

            evenementRow.add(BorderLayout.CENTER, labelsContainer);

            // Create a container to hold the buttons
            // Container buttonsContainer = new Container(new GridLayout(3, 1));
            //buttonsContainer.setUIID("EvenementButtonBox");
            //buttonsContainer.add(reserveBtn);
            // buttonsContainer.add(editBtn);
            // buttonsContainer.add(deleteBtn);
            //evenementRow.add(BorderLayout.EAST, buttonsContainer);
            // Add the event row to the container
            evenementContainer.add(evenementRow);
        }

        // Add the container to the form
        add(evenementContainer);
        

        // Add a button to add a new event
        // Button addBtn = new Button("Add");
        Button avisBtn = new Button("Avis");
        // addBtn.addActionListener(e -> {
        //    new EvenementAddForm().showAdd();
        // });

//        avisBtn.addActionListener(e -> {
//            new AddAvisForm().show();
//        });
        // add(addBtn);
        // add(avisBtn);
    }

    public void showEvenementList() {
        EvenementListForm form = new EvenementListForm(this);
        form.show();
    }
}
