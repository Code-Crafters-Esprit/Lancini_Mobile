/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;


import codecrafters.lancini.entities.Publication;
import codecrafters.lancini.service.PublicationService;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;
import com.codename1.ui.Form;


/**
 *
 * @author Samar
 */
public class PublicationListForm extends  Form{
    
    
     public PublicationListForm(Form previous) {
       super("Lancini Content");
       
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                ev -> {previous.showBack();} );

//        getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_ADD,
//                e -> {
//                    new EvenementAddForm(this).show();
//                });
         ArrayList<Publication> publications = PublicationService.getInstance().getAllPublications();

        // Create a container to hold the events
        Container publicationContainer = new Container();

        for (Publication e : publications) {
            // Create a container to hold the event's information and buttons
            Container publicationRow = new Container(new BorderLayout());
            publicationRow.setUIID("PublicationBox");

 

            Label libelleLabel = new Label( e.getLibelle());
            libelleLabel.setUIID("LibelleLabel");
            
            Style libelleLabelStyle = libelleLabel.getUnselectedStyle();
            libelleLabelStyle.setFgColor(0x5AB9EA); // Set foreground color to red
            libelleLabel.setUnselectedStyle(libelleLabelStyle);
           

            Label descriptionLabel = new Label("Description: " + e.getDescription());
            descriptionLabel.setUIID("descriptionLabel");


            Label dateLabel = new Label("Date: " + e.getDatePub());
            dateLabel.setUIID("dateLabel");

       
            
//            DELETE PUB
//            Button deleteBtn = new Button("Delete");
//            deleteBtn.addActionListener(a -> {
//                EvenementService.getInstance().deleteEvenement(e.getIdEvent());
//
//                evenementContainer.removeComponent(evenementRow);
//            });

            // Add the labels to the event row
            Container labelsContainer = new Container(new GridLayout(5, 1));
//            labelsContainer.add(idLabel);
            labelsContainer.add(libelleLabel);
            labelsContainer.add(descriptionLabel);
            labelsContainer.add(dateLabel);

          //  labelsContainer.add(deleteBtn);

            publicationRow.add(BorderLayout.CENTER, labelsContainer);

            publicationContainer.add(publicationRow);
        }

        // Add the container to the form
        add(publicationContainer);
        

  
    }

    public void showPublicationList() {
        PublicationListForm form = new PublicationListForm(this);
        form.show();
    }



 
    

  


}

    

