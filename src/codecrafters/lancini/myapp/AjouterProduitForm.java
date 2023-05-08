package codecrafters.lancini.myapp;

import codecrafters.lancini.entities.Produit;
import codecrafters.lancini.service.ServiceProduit;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;

import java.io.IOException;

public class AjouterProduitForm extends Form {
    private Label thumbnailLabel;

    public AjouterProduitForm(Form previous) {
        setTitle("Add a new Product");
        setLayout(BoxLayout.y());
        Label categorieLabel =new Label("Choose categorie");
 ComboBox<String> categorieComboBox = new ComboBox<>();
        categorieComboBox.addItem("Avatar");
        categorieComboBox.addItem("Logo");
        categorieComboBox.addItem("Graphic Design");
        categorieComboBox.addItem("Mascott");
        categorieComboBox.addItem("Wallpaper");
        categorieComboBox.addItem("Background");

        TextField nomTF = new TextField("", "Nom");
        TextField descriptionTF = new TextField("", "Description");
        TextField prixTF = new TextField("", "Prix");
        Picker date = new Picker();
        ComboBox<String> vendeurComboBox = new ComboBox<>();

        Button uploadBtn = new Button("Upload Image");
        uploadBtn.addActionListener(e -> {
            Display.getInstance().openGallery((ActionListener) (ActionEvent ev) -> {
                if (ev != null && ev.getSource() != null) {
                    String filePath = (String) ev.getSource();
                    // Handle the selected file path
                    // Update the thumbnail label with the selected image
                    updateThumbnail(filePath);
                }

            }, Display.GALLERY_IMAGE);
        });

        Button btnValider = new Button("Add Product");

        btnValider.addActionListener(evt -> {
            if ((categorieComboBox.getSelectedIndex() == -1)|| (nomTF.getText().length() == 0) ||
                    (descriptionTF.getText().length() == 0) || (prixTF.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            } else {
                try {
                    // Get the image file path from the thumbnail label

                    Produit p = new Produit(categorieComboBox.getSelectedItem(), nomTF.getText(), descriptionTF.getText(), thumbnailLabel.getText(), Float.parseFloat(prixTF.getText()), date.getDate());

                    p.setCategorie(categorieComboBox.getSelectedItem());
                    if (ServiceProduit.getInstance().addProduit(p)) {
                        Dialog.show("Success", "Product "+thumbnailLabel.getText(), new Command("OK"));
                         vendeurComboBox.setSelectedIndex(-1);
                        nomTF.clear();
                        descriptionTF.clear();
                        prixTF.clear();
                       
                    } else {
                        Dialog.show("ERROR", "Server error", new Command("OK"));
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "There is a problem in adding the product", new Command("OK"));
                }
            }
        });

        addAll(categorieLabel,categorieComboBox, nomTF, descriptionTF, prixTF, date, uploadBtn, btnValider);


        // Create the thumbnail label
        thumbnailLabel = new Label();
        thumbnailLabel.setPreferredW(100);
        thumbnailLabel.setPreferredH(100);
        thumbnailLabel.getStyle().setBgColor(0xeeeeee);
        thumbnailLabel.getStyle().setBgTransparency(255);
        thumbnailLabel.getStyle().setMarginTop(10);
        thumbnailLabel.getStyle().setMarginBottom(10);

        add(thumbnailLabel);

           // Create the "Back" command
        Command backCommand = new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                previous.showBack();
            }
        };
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, backCommand);

        // Add the form to the display stack
        addCommandListener(evt -> {
            if (evt.getCommand() == backCommand) {
                previous.showBack();
            }
        });
    }

    private void updateThumbnail(String imagePath) {
        try {
            // Create a thumbnail from the selected image and set it to the thumbnail label
            Image originalImage = Image.createImage(imagePath);
            Image thumbnailImage = originalImage.scaledSmallerRatio(100, 100);
            thumbnailLabel.setIcon(thumbnailImage);

            // Store the image file name in the UIID property of the thumbnail label
            String fileName = getFileNameFromPath(imagePath);
            thumbnailLabel.setUIID(fileName);

            // Refresh the UI to reflect the updated thumbnail
            revalidate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileNameFromPath(String path) {
        int lastIndex = path.lastIndexOf('/');
        if (lastIndex != -1 && lastIndex < path.length() - 1) {
            return path.substring(lastIndex + 1);
        }
        return "";
    }
}