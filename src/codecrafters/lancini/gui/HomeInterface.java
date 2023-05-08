/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import codecrafters.lancini.gui.LoginInterface;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.Border;
import java.io.IOException;


/**
 *
 * @author Youssef-Ayed
 */
public class HomeInterface extends Form {

    public HomeInterface() {
        setTitle("Home");
        setLayout(BoxLayout.y());
        Label welcome = new Label("Welcome to Lancini Mobile!");
        welcome.setUIID("CenterLabel");
        getTitleArea().setUIID("Container");
        add(welcome);
        Button Login = new Button("Login");
        Button Signup = new Button("Signup");
        Login.addActionListener(e -> new LoginInterface().show());
        Signup.addActionListener(e -> {
            new SignupInterface().show();
        });
        addAll(Login, Signup);
    }

}
