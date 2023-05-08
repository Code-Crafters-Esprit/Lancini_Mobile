/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.io.IOException;


/**
 *
 * @author user
 */
public class CaptureSecteur  {

    public CaptureSecteur(Form previous) {
         Form hi = new Form("Image", BoxLayout.y());
        Button   btnCapture = new Button("Capture");
        Label imageLabel = new Label();
        hi.add(btnCapture  );
        hi.add(imageLabel);
        hi.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

           btnCapture.addActionListener((e)->{
             
           String path= Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
           if (path != null){
               try {
                   Image img =  Image.createImage(path);
                   imageLabel.setIcon(img);
                   hi.revalidate();
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
           
         });

        hi.show();

        
    }
    
    
}
