package codecrafters.lancini.myapp;

import codecrafters.lancini.entities.Produit;
import codecrafters.lancini.service.ServiceProduit;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import java.io.IOException;
import java.util.ArrayList;

public class AfficherProduitForm extends Form {

    public AfficherProduitForm(Form previous) {
        setTitle("List Products");
        setLayout(BoxLayout.y());
 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> {
            // Show the HomeForm
            HomeForm homeForm = new HomeForm();
            homeForm.show();
        });
        // Set the cover image
        try {
            EncodedImage placeholder = EncodedImage.create("/img.png");
            String flickrImageUrl = "https://live.staticflickr.com/65535/52811485745_dcdd7e8ce1.jpg";
            Image coverImage = URLImage.createToStorage(placeholder, "cover_image", flickrImageUrl);
            Label coverLabel = new Label(coverImage);
            coverLabel.setUIID("CoverImage");
            add(coverLabel);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        ArrayList<Produit> produits = ServiceProduit.getInstance().getAllProduits();
        for (Produit p : produits) {
            addElement(p);
        }

        Command backButton = new Command("Back") {
            @Override
            public void actionPerformed(ActionEvent evt) {
                previous.showBack();
            }
        };
        setBackCommand(backButton);
    }

    public void addElement(Produit produit) {
        Container container = new Container(new BorderLayout());

        // Create an ImageViewer to display the product image
        ImageViewer imageViewer = new ImageViewer();
        try {
            EncodedImage placeholder = EncodedImage.create("/placeholder_image.png");
            Image image = Image.createImage("/" + produit.getImage());
            imageViewer.setImage(image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        container.add(BorderLayout.WEST, imageViewer);

        // Create a Container for the product details
        Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        // Add the product details to the detailsContainer
        Label nameLabel = new Label(produit.getNom());
        Label descriptionLabel = new Label(produit.getDescription());
        Label priceLabel = new Label("Price: " + produit.getPrix());
        detailsContainer.add(nameLabel);
        detailsContainer.add(descriptionLabel);
        detailsContainer.add(priceLabel);

        container.add(BorderLayout.CENTER, detailsContainer);

        // Create a "Show Details" button
        Button showDetailsButton = new Button(FontImage.MATERIAL_INFO);
        showDetailsButton.setUIID("ShowDetailsButton");
        showDetailsButton.addActionListener(e -> showProductDetails(produit)); // Call the showProductDetails() method passing the product
        container.add(BorderLayout.EAST, showDetailsButton);

        add(container);
    }

public void showProductDetails(Produit produit) {
    // Create a new Form to display the product details
    Form productDetailsForm = new Form("Product Details");
    productDetailsForm.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

    // Create Labels to display the product details
    Label nameLabel = new Label("Name: " + produit.getNom());
    Label descriptionLabel = new Label("Description: " + produit.getDescription());
    Label priceLabel = new Label("Price: " + produit.getPrix());

    // Apply custom styling to the labels
    nameLabel.setUIID("ProductDetailsLabel");
    descriptionLabel.setUIID("ProductDetailsLabel");
    priceLabel.setUIID("ProductDetailsLabel");

    // Add the Labels to the productDetailsForm
    productDetailsForm.add(nameLabel);
    productDetailsForm.add(descriptionLabel);
    productDetailsForm.add(priceLabel);

    // Create buttons for delete and update actions
    Button deleteButton = new Button("Delete");
   deleteButton.addActionListener(e -> {
    // Prompt the user for confirmation
    boolean confirmed = Dialog.show("Confirm Deletion", "Are you sure you want to delete this product?", "Yes", "No");
    if (confirmed) {
        // Perform delete operation for the product
        boolean deleted = ServiceProduit.getInstance().deleteProduit(produit.getIdProduit());
        if (deleted) {
            // Show success message and navigate back to the previous form
            Dialog.show("Success", "Product deleted", "OK", null);
            showBack();
        } else {
            // Show error message if delete operation fails
            Dialog.show("Error", "Failed to delete product", "OK", null);
        }
    }
});

    Button updateButton = new Button("Update");
    updateButton.addActionListener(e -> {
        // Perform update operation for the product
        boolean updated =ServiceProduit.getInstance().updateProduit(produit);
        if (updated) {
            // Show success message and refresh the product details form
            Dialog.show("Success", "Product updated", "OK", null);
            refreshProductDetails(productDetailsForm, produit);
        } else {
            // Show error message if update operation fails
            Dialog.show("Error", "Failed to update product", "OK", null);
        }
    });

    // Add the buttons to the productDetailsForm
    productDetailsForm.add(deleteButton);
    productDetailsForm.add(updateButton);

    // Create a back button
    Button backButton = new Button("Back");
    backButton.addActionListener(e -> {
        // Show the previous form (AfficherProduitForm)
        showBack();
    });
    productDetailsForm.add(backButton);

    // Create a back command for the productDetailsForm
    Command backCommand = new Command("Back") {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Show the previous form (AfficherProduitForm)
            showBack();
        }
    };
    productDetailsForm.setBackCommand(backCommand);

    // Show the productDetailsForm
    productDetailsForm.show();
}





private void refreshProductDetails(Form productDetailsForm, Produit produit) {
    // Clear the form content
    productDetailsForm.removeAll();

    // Create Labels to display the updated product details
    Label nameLabel = new Label("Name: " + produit.getNom());
    Label descriptionLabel = new Label("Description: " + produit.getDescription());
    Label priceLabel = new Label("Price: " + produit.getPrix());

    // Apply custom styling to the labels
    nameLabel.setUIID("ProductDetailsLabel");
    descriptionLabel.setUIID("ProductDetailsLabel");
    priceLabel.setUIID("ProductDetailsLabel");

    // Add the Labels to the productDetailsForm
    productDetailsForm.add(nameLabel);
    productDetailsForm.add(descriptionLabel);
    productDetailsForm.add(priceLabel);

    // Create buttons for delete and update actions
    Button deleteButton = new Button("Delete");
   deleteButton.addActionListener(e -> {
    // Prompt the user for confirmation
    boolean confirmed = Dialog.show("Confirm Deletion", "Are you sure you want to delete this product?", "Yes", "No");
    if (confirmed) {
        // Perform delete operation for the product
        boolean deleted = ServiceProduit.getInstance().deleteProduit(produit.getIdProduit());
        if (deleted) {
            // Show success message and navigate back to the previous form
            Dialog.show("Success", "Product deleted", "OK", null);
            showBack();
        } else {
            // Show error message if delete operation fails
            Dialog.show("Error", "Failed to delete product", "OK", null);
        }
    }
});
    Button updateButton = new Button("Update");
    updateButton.addActionListener(e -> {
        // Perform update operation for the product
        boolean updated =ServiceProduit.getInstance().updateProduit(produit);
        if (updated) {
            // Show success message and refresh the product details form
            Dialog.show("Success", "Product updated", "OK", null);
            refreshProductDetails(productDetailsForm, produit);
        } else {
            // Show error message if update operation fails
            Dialog.show("Error", "Failed to update product", "OK", null);
        }
    });

    // Add the buttons to the productDetailsForm
    productDetailsForm.add(deleteButton);
    productDetailsForm.add(updateButton);

    // Create a back button
    Button backButton = new Button("Back");
    backButton.addActionListener(e -> {
        // Show the previous form (AfficherProduitForm)
        showBack();
    });
    productDetailsForm.add(backButton);

    // Create a back command for the productDetailsForm
}
}