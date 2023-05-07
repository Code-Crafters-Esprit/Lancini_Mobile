/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import codecrafters.lancini.entities.Cv;
import codecrafters.lancini.service.CvService;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author LENOVO
 */
public class CveditForm extends Form {
   
    public CveditForm(Cv cv1) {
        super("edit Cv");
        
        // Create text fields and a combo box for the cv information
        TextField nomField = new TextField(cv1.getNom(), "nom");
        TextField prenomField = new TextField(cv1.getPrenom(), "prenom");
        Picker dateNaissancePicker = new Picker();
        TextField sexeField = new TextField(cv1.getSexe(), "sexe");
        TextField cinField = new TextField(cv1.getCin(), "cin");
        TextField adresseField = new TextField(cv1.getAdresse(), "adresse");
        TextField emailField = new TextField(cv1.getEmail(), "email");
        TextField langueField = new TextField(cv1.getLangue(), "langue");
         TextField educationField = new TextField(cv1.getEducation(), "education");
        
        // Create a button to edit the cv
        Button addBtn = new Button("edit");
        addBtn.addActionListener(e -> {
            // Validate the entered values
            try {
                String nom = nomField.getText();
                String prenom = prenomField.getText();
                String date = dateNaissancePicker.getDate().toString();
                String sexe = sexeField.getText();
                String cin = cinField.getText();
                String adresse = adresseField.getText();
                String email = emailField.getText();
                String langue = langueField.getText();
                String education = educationField.getText();
                
                

                // Check that the entered values are valid
                if (nom.isEmpty() || prenom.isEmpty() || sexe.isEmpty() || cin.isEmpty() || adresse.isEmpty()|| email.isEmpty() || langue.isEmpty()||education.isEmpty()) {
                    Dialog.show( "Please complete the following","okey", null);
                    return;
                }

                // Create a new demande with the entered information
                Cv cv = new Cv();
                cv.setIdCv(cv1.getIdCv());
                cv.setnom(nom);
                cv.setprenom(prenom);
                cv.setdateNaissance(date);
                cv.setsexe(sexe);
                cv.setCin(cin);
                cv.setadresse(adresse);
                cv.setemail(email);
                cv.setLangue(langue);
                cv.setEducation(education);
            
               
                CvService.getInstance().editCv(cv);
                new CvListForm().show();


                // Close the form
               // this.close();
            } catch (NumberFormatException ex) {
                // Handle the case where the entered values are not valid numbers
                Dialog.show("Error", "Please enter valid inputs", "OK", null);
            }
        });
       
        // Add the text fields and combo box to the form
        add(nomField);
        add(prenomField);
        add(dateNaissancePicker);
        add(sexeField);
        add(cinField);
        add(adresseField);
        add(emailField);
        add(langueField);
        add(educationField);
        add(addBtn);
    }
   
 public void showAdd() {
 CvAddForm form = new CvAddForm();
  form.show();
}    
    
}
