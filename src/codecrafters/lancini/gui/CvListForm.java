/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package codecrafters.lancini.gui;

/**
 *
 * @author LENOVO
 */

import codecrafters.lancini.entities.Cv;
import codecrafters.lancini.service.CvService;

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
public class CvListForm extends Form{
    private ArrayList<Cv> cvs;

    public CvListForm() {
        super("cvs");

        getToolbar().addMaterialCommandToRightBar("New cv", FontImage.MATERIAL_ADD,
                e -> {
                    new CvAddForm().show();
                });
        

        cvs= CvService.getInstance().getAllcvs();
        System.out.println(cvs);
        Container cvContainer = new Container();

        for (Cv cv : cvs) {
            Container cvRow = new Container(new BorderLayout());
            cvRow.setUIID("cvBox");


            Label nomLabel = new Label("nom: " + cv.getNom());
            nomLabel.setUIID("nomLabel");

            Label prenomLabel = new Label("prenom: " + cv.getPrenom());
            prenomLabel.setUIID("prenomLabel");

            Label dateLabel = new Label("Date de naissance : " + cv.getDateNaissance());
            dateLabel.setUIID("dateLabel");

            Label sexeLabel = new Label("sexe: " + cv.getSexe());
            sexeLabel.setUIID("sexeLabel");
            
            
            Label cinLabel = new Label("cin: " + cv.getCin());
            cinLabel.setUIID("cinLabel");
            
            Label adresseLabel = new Label("adresse: " + cv.getAdresse());
            adresseLabel.setUIID("adresseLabel");
            
            Label emailLabel = new Label("email: " + cv.getEmail());
            emailLabel.setUIID("emailLabel");
            
            Label langueLabel = new Label("langue: " + cv.getLangue());
            langueLabel.setUIID("langueLabel");
            
            Label educationLabel = new Label("education: " + cv.getEducation());
            educationLabel.setUIID("educationLabel");

            // Create buttons to reserve, edit, and delete the cv
            Button reserveBtn = new Button("Add");
            reserveBtn.addActionListener(e -> {
            });
            
            Button editBtn = new Button("Edit");
            editBtn.addActionListener(e -> {
                new CveditForm(cv).show();
                cvContainer.removeComponent(cvRow);
           });
            
            Button deleteBtn = new Button("Delete");
            deleteBtn.addActionListener(a -> {
                CvService.getInstance().deleteCv(cv.getIdCv());

                cvContainer.removeComponent(cvRow);
            });

            // Add the labels to the cv row
            Container labelsContainer = new Container(new GridLayout(5, 1));
//            labelsContainer.add(idLabel);
            labelsContainer.add(nomLabel);
            labelsContainer.add(prenomLabel);
            labelsContainer.add(dateLabel);
            labelsContainer.add(sexeLabel);
            labelsContainer.add(cinLabel);
            labelsContainer.add(adresseLabel);
            labelsContainer.add(emailLabel);
            labelsContainer.add(langueLabel);
            labelsContainer.add(educationLabel);
            
            
            labelsContainer.add(deleteBtn);
            labelsContainer.add(editBtn);
            cvRow.add(BorderLayout.CENTER, labelsContainer);

            cvContainer.add(cvRow);
        }

        // Add the container to the form
        add(cvContainer);
        

    }

    public void showEvenementList() {
        CvListForm form = new CvListForm();
        form.show();
    }
}
    
