/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author LENOVO
 */
public class HomeInterface  extends Form{
    public HomeInterface() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddSecteur = new Button("Add Sector");
        Button btnListSecteur = new Button("List Sector");
        Button btnListOffre = new Button("List Offers");
        Button MapOffre = new Button("Map");
        Button btnCapture = new Button("Capture");

        btnAddSecteur.addActionListener(e-> new AddSecteur(this).show());
        btnListSecteur.addActionListener(e-> new ListeSecteur(this).show());
        btnListOffre.addActionListener(e-> new ListeOffre(this).show());
        MapOffre.addActionListener(e-> new MapForm(this));


        btnCapture.addActionListener(e-> new CaptureSecteur(this));

       
        addAll(btnAddSecteur,btnListSecteur , btnListOffre, btnCapture , MapOffre );
       
    }
}
