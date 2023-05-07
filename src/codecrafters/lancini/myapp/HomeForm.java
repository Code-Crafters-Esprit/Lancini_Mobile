/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Daly
 */

    public class HomeForm extends Form{

    public HomeForm() {
        
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
       
        Button btnListProducts = new Button("List Products");
        Button btnAddProduct = new Button("Add Producta");
        
        btnAddProduct.addActionListener(e-> new AjouterProduitForm(this).show());
        
        btnListProducts.addActionListener(e-> new AfficherProduitForm(this).show());
        add(btnAddProduct);
         add(btnListProducts);
        
        
    }
    
    
}


