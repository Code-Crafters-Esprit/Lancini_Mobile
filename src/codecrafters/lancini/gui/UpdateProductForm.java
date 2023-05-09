package codecrafters.lancini.gui;

import codecrafters.lancini.entities.Produit;
import codecrafters.lancini.service.ServiceProduit;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;

public class UpdateProductForm extends Form {
    private Produit product;
    private TextField categorieTF;
    private TextField nomTF;
    private TextField descriptionTF;
    private TextField prixTF;

    public UpdateProductForm(Form previous, Produit product) {
        this.product = product;

        setTitle("Update Product");
        setLayout(BoxLayout.y());

        categorieTF = new TextField(product.getCategorie(), "Category");
        nomTF = new TextField(product.getNom(), "Name");
        descriptionTF = new TextField(product.getDescription(), "Description");
        prixTF = new TextField(String.valueOf(product.getPrix()), "Price");

        Button updateBtn = new Button("Update");
        updateBtn.addActionListener(evt -> updateProduct());

        addAll(categorieTF, nomTF, descriptionTF, prixTF, updateBtn);

        // Create the "Back" command
        Command backCommand = new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                previous.showBack();
            }
        };
        getToolbar().addCommandToLeftBar(backCommand);

        // Add the form to the display stack
        addCommandListener(evt -> {
            if (evt.getCommand() == backCommand) {
                previous.showBack();
            }
        });
    }

    private void updateProduct() {
        if ((categorieTF.getText().length() == 0) || (nomTF.getText().length() == 0) ||
                (descriptionTF.getText().length() == 0) || (prixTF.getText().length() == 0)) {
            Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
        } else {
            try {
                product.setCategorie(categorieTF.getText());
                product.setNom(nomTF.getText());
                product.setDescription(descriptionTF.getText());
                product.setPrix(Float.parseFloat(prixTF.getText()));

                boolean updated = ServiceProduit.getInstance().updateProduit(product);
                if (updated) {
                    Dialog.show("Success", "Product updated", new Command("OK"));
                    showBack();
                } else {
                    Dialog.show("Error", "Failed to update product", new Command("OK"));
                }
            } catch (NumberFormatException e) {
                Dialog.show("Error", "Invalid price format", new Command("OK"));
            }
        }
    }
}