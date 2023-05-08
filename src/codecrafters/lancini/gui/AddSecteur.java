/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import codecrafters.lancini.service.Service_Secteur;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author user
 */
public class AddSecteur  extends Form{
        private Label lNom , Ldesc , lDate;
        private Button btn ;
        private Picker date ;
     public AddSecteur(Form previous) {
        setTitle("Add a new task");


        setLayout(BoxLayout.y());
        Label labNom=new Label("Sector name");
        TextField nom = new TextField("","name");
        Label labDesc=new Label("Description");
        TextField description = new TextField("","Description");
        Label labDate=new Label("Creation date");
        date = new Picker();
        btn = new Button("Add Sector");
       Form hi1 = new Form();

      addAll(labNom,nom,labDesc,description,labDate , date,btn );

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
            }
        });
        btn.addActionListener(e->{
           Service_Secteur.getInstance().AddSecteur(nom.getText(), description.getText(), date.getText());
           Dialog.show("Sector added successfully ", nom.getText(),"ok",null);
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Form hi2 = new Form("Details", BoxLayout.y());
                Label lNom = new Label("Name : "+nom.getText());
                Label Ldesc = new Label("Description : "+description.getText());
                Label lDate = new Label("Date de creation : "+date.getDate());
                hi2.addAll(lNom,Ldesc,lDate);
                hi2.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                hi2.show();

            }
            
        });
    }

    
}
