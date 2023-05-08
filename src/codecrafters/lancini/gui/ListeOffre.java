/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import codecrafters.lancini.entities.Offre;
import codecrafters.lancini.entities.Secteur;
import codecrafters.lancini.service.service_Offre;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class ListeOffre  extends Form{
                private Button btnDelete;

    public ListeOffre(Form previous) {
        setTitle("List Offers");
        setLayout(BoxLayout.y());

        
        ArrayList<Offre> offers = service_Offre.getInstance().findAll();
        for (Offre t : offers) {
            LabelNom(t);
            NomElement(t);
            LabelType(t);
            TypeElement(t);
            Labelcomp(t);
            CompElement(t);
            SyppElement(t);

        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
  public void LabelNom(Offre offre) {
        Label ln = new Label("Offre name : ");
        ln.setEnabled(false);
        add(ln );
    } 
  public void LabelType(Offre offre) {
        Label ln = new Label("Offre Type : ");
        ln.setEnabled(false);
        add(ln);
    } 
   public void Labelcomp (Offre offre) {
        Label lc = new Label("Skills : ");
        lc.setEnabled(false);
        add(lc);
    } 
    public void NomElement(Offre offer) {
        CheckBox cb = new CheckBox(offer.getNom());
        cb.setEnabled(false);
        cb.setSelected(true);
        add(cb);
    }
    public void CompElement(Offre offer) {
        CheckBox cmp = new CheckBox(offer.getCompetence());
        cmp.setEnabled(false);
        cmp.setSelected(true);
        add(cmp);
    }
     public void TypeElement(Offre offer) {

         CheckBox  type= new CheckBox(offer.getTypeOffre());
        type.setSelected(true);
        type.setEnabled(false);
        add(type);

    }
      public void SyppElement(Offre offre) {

      btnDelete = new Button("delete Offer");

        btnDelete.setEnabled(true);
        add(btnDelete );
          btnDelete.addActionListener(a -> {
                service_Offre.getInstance().deleteSecteur(offre.getIdOffre());
            });
      }
}
