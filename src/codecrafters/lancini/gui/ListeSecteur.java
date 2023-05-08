/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import codecrafters.lancini.entities.Secteur;
import codecrafters.lancini.service.Service_Secteur;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ListeSecteur extends Form{
           private Button btnDelete , btnupdate;
//
  public ListeSecteur(Form previous) {
      setTitle("List sectors");
       setLayout(BoxLayout.y());
        Label titre=new Label("AVAILABLE SECTOR :");
              
      add(titre );
        ArrayList<Secteur> secteurs = Service_Secteur.getInstance().findAll();
        for (Secteur t : secteurs) {
            LabelNom(t);
            NomElement(t);
            LabelDate(t);
            DateElement(t);
            SuppElement(t);
            

        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
     
    
    public void NomElement(Secteur secteur) {
        CheckBox cb = new CheckBox(secteur.getNom());

        cb.setEnabled(false);
        add(cb );             
    } 
     public void LabelNom(Secteur secteur) {
        Label ln = new Label("Sector name : ");
        ln.setEnabled(false);
        add(ln );
    } 
      public void LabelDate(Secteur secteur) {
        Label ln = new Label("Creation date : ");
//     Style titreLabelStyle = ln.getUnselectedStyle();
//        titreLabelStyle.setFgColor(0x5AB9EA); 
        ln.setEnabled(false);
        add(ln );
    } 
     public void DateElement(Secteur secteur) {
        TextField bb = new TextField(secteur.getDateCreation());
        bb.setEnabled(false);
        add(bb );
        
        
    }   
     public void SuppElement(Secteur secteur) {

      btnDelete = new Button("delete Sector");


        btnDelete.setEnabled(true);
        add(btnDelete );
         btnDelete.addActionListener(a -> {
                Service_Secteur.getInstance().deleteSecteur(secteur.getIdSecteur());
            });
     }
    
}

    
    
    

