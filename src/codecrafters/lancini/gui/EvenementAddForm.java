/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;

import codecrafters.lancini.entities.Evenement;
import codecrafters.lancini.service.EvenementService;
import codecrafters.lancini.tools.CountryAPI;
import com.codename1.ui.ComboBox;
import com.codename1.ui.FontImage;
import java.util.ArrayList;

import java.util.Date;

/**
 *
 * @author samar
 */
public class EvenementAddForm extends Form {

    public EvenementAddForm(Form previous) {
        super("Add Evenement");

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                ev -> {
                    previous.showBack();
                });

        // Create text fields and a combo box for the evenement information
        TextField titreField = new TextField("", "titre");
        TextField sujetField = new TextField("", "sujet");
        //TextField lieuField = new TextField("", "lieu");
        Picker dateEventPicker = new Picker();
        TextField horaireField = new TextField("", "horaire");

        CountryAPI countryAPI = CountryAPI.getInstance();
        ArrayList<String> nomsPays = countryAPI.getAllPays();
        System.out.println(countryAPI.getAllPays());
        ComboBox<String> comboBoxPays = new ComboBox<>();
        for (String nomPays : nomsPays) {
                    comboBoxPays.addItem(nomPays);
                }

        // Create a button to add the evenement
        Button addBtn = new Button("Add");
        addBtn.addActionListener(e -> {
            // Validate the entered values
            try {
                String titre = titreField.getText().trim();
                String sujet = sujetField.getText();
                Date date = dateEventPicker.getDate();
//                String lieu = lieuField.getText();
                String horaire = horaireField.getText();
                String nomPays = comboBoxPays.getSelectedItem().toString();
                

                // Check that the entered values are valid
                if (titre.isEmpty() || sujet.isEmpty() || horaire.isEmpty()) {
                    Dialog.show("Error", "Please enter valid values for all fields", "OK", null);
                    return;
                }
 
                // Create a new demande with the entered information
                Evenement event = new Evenement();
                event.setTitre(titre);
                event.setSujet(sujet);
                //event.setLieu(lieu);
                event.setDateEvent(date.toString());
                event.setHoraire(horaire);
                event.setLieu(nomPays);

                EvenementService.getInstance().addEvenement(event);
                new EvenementListForm(this).show();

                // Close the form
                // this.close();
            } catch (NumberFormatException ex) {
                // Handle the case where the entered values are not valid numbers
                Dialog.show("Error", "Please enter valid inputs", "OK", null);
            }
        });

        // Add the text fields and combo box to the form
        add(titreField);
        add(sujetField);
        // add(lieuField);
        add(dateEventPicker);
        add(horaireField);
        add(comboBoxPays);

        add(addBtn);
    }

//  public void showAdd() {
//    EvenementAddForm form = new EvenementAddForm();
//    form.show();
//}    
}
