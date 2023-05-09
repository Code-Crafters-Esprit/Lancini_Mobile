/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author ezzar
 */
public class AjoutAviForm extends Form {
    
     public AjoutAviForm(Form previous) {
    super("Ajouter vos avis", BoxLayout.y());
    
              getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                ev -> {previous.showBack();} );
         
         
       
    } 
    
    
}
