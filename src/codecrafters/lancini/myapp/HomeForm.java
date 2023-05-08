/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.myapp;

import codecrafters.lancini.service.NewsArticle;
import codecrafters.lancini.service.NewsService;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import java.util.Observable;
import java.util.Observer;

public class HomeForm extends Form {

  public HomeForm() {
    setTitle("Home");
    setLayout(BoxLayout.y());

    add(new Label("Choose an option"));

    Button btnListProducts = new Button("List Products");
    Button btnAddProduct = new Button("Add Products");

    btnAddProduct.addActionListener(e -> new AjouterProduitForm(this).show());
    btnListProducts.addActionListener(e -> new AfficherProduitForm(this).show());

    add(btnAddProduct);
    add(btnListProducts);

    fetchNewsArticles(); // Fetch news articles
}

  public void displayNewsArticles(String title, String description) {
    // Create container for each news article
    Container newsContainer = new Container(BoxLayout.y());
    newsContainer.getAllStyles().setBorder(Border.createLineBorder(1, 0xCCCCCC)); // Add border
    newsContainer.getAllStyles().setMargin(10, 10, 0, 0); // Add margin

    // Create labels for title and description
    Label lblTitle = new Label(title);
    lblTitle.getAllStyles().setFgColor(0x333333); // Set title color
    lblTitle.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE)); // Set title font

    Label lblDescription = new Label(description);
    lblDescription.getAllStyles().setFgColor(0x666666); // Set description color
    lblDescription.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM)); // Set description font

    // Create button for more details
    Button btnDetails = new Button("More Details");
    btnDetails.addActionListener(e -> showNewsDetails(title, description));

    // Add labels and button to the container
    newsContainer.add(lblTitle);
    newsContainer.add(lblDescription);
    newsContainer.add(btnDetails);

    // Add the container to the form
    add(newsContainer);

    revalidate(); // Refresh the form to show the new components
}
public void showNewsDetails(String title, String description) {
    Form newsDetailsForm = new Form(title);
    newsDetailsForm.setLayout(BoxLayout.y());

    TextArea txtTitle = new TextArea(title);
    txtTitle.setEditable(false);
    txtTitle.setUIID("NewsTitle"); // Apply custom UIID for styling

    TextArea txtDescription = new TextArea(description);
    txtDescription.setEditable(false);
    txtDescription.setUIID("NewsDescription"); // Apply custom UIID for styling

    newsDetailsForm.add(txtTitle);
    newsDetailsForm.add(txtDescription);
 Button btnBack = new Button("Back");
    btnBack.addActionListener(e -> showBack());

    newsDetailsForm.add(btnBack);
    newsDetailsForm.show();
}


    private void fetchNewsArticles() {
    NewsService newsService = new NewsService();
    newsService.addObserver(new Observer() {
        @Override
        public void update(Observable o, Object arg) {
            if (arg instanceof NewsArticle) {
                NewsArticle newsArticle = (NewsArticle) arg;
                Display.getInstance().callSerially(() -> displayNewsArticles(newsArticle.getTitle(), newsArticle.getDescription()));
            } else {
                Dialog.show("Error", "Failed to retrieve news articles", "OK", null);
            }
        }
    });
    newsService.fetchNewsArticles();
}

}
