/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.myapp;

import codecrafters.lancini.entities.Produit;
import codecrafters.lancini.service.ServiceProduit;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;


/**
 *
 * @author houss
 */
public class AjouterProduitForm extends Form {

    public AjouterProduitForm(Form previous) {
        setTitle("Add a new Product");
        setLayout(BoxLayout.y());

        TextField categorieTF = new TextField("", "Categorie");
        TextField nomTF = new TextField("", "Nom");
        TextField descriptionTF = new TextField("", "Description");
        TextField imageTF = new TextField("", "image");
        
                TextField prixTF = new TextField("", "prix");

        Picker date = new Picker();

        Button btnValider = new Button("Add Product");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((categorieTF.getText().length() == 0) || (nomTF.getText().length() == 0) || (descriptionTF.getText().length() == 0) || (imageTF.getText().length() == 0)|| (prixTF.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                       
                        Produit p = new Produit(categorieTF.getText(), nomTF.getText(),descriptionTF.getText(),imageTF.getText(),Float.parseFloat(prixTF.getText()), date.getDate());
                        p.setCategorie(categorieTF.getText());
                        if (ServiceProduit.getInstance().addProduit(p)) {
                            Dialog.show("Success", "produit ajoutée ", new Command("OK"));
                            categorieTF.clear();
                          nomTF.clear();
                          descriptionTF.clear();
                          imageTF.clear();
                          prixTF.clear();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "there is problem in adding ", new Command("OK"));
                    }

                }

            }
        });

        addAll(categorieTF, nomTF,descriptionTF,prixTF,imageTF,date, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}