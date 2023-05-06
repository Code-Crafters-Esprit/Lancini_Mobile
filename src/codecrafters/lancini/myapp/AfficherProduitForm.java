package codecrafters.lancini.myapp;

import codecrafters.lancini.entities.Produit;
import codecrafters.lancini.service.ServiceProduit;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 * Form to display a list of products.
 */
public class AfficherProduitForm extends Form {

    public AfficherProduitForm(Form previous) {
        setTitle("List Products");
        setLayout(BoxLayout.y());

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
        CheckBox cb = new CheckBox(produit.getNom());
        add(cb);
    }
}
