/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import codecrafters.lancini.entities.User;
import codecrafters.lancini.service.UserService;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.plaf.Style;
/**
 *
 * @author Youssef Ayed
 */
public class SignupInterface extends Form {

    public SignupInterface() {

        //Header
        getContentPane().setUIID("SignInForm");
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        Command returnCommand = new Command("") {
            public void actionPerformed(ActionEvent evt) {
                new HomeInterface().show();
            }
        };
        returnCommand.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK_IOS, UIManager.getInstance().getComponentStyle("TitleCommand")));
        tb.addCommandToLeftBar(returnCommand);
        setTitle("Sign Up");

        //Form fields
        TextField email = new TextField("", "Email", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        TextField prenom = new TextField("", "First Name", 20, TextField.ANY);
        TextField nom = new TextField("", "Last Name", 20, TextField.ANY);
        String[] choices = {"Simple User", "Employeur", "Candidat"};
        ComboBox<String> role = new ComboBox<>(choices);
        role.getAllStyles().setMarginTop(90);
        role.getAllStyles().setPaddingTop(15);
        role.getAllStyles().setPaddingBottom(15);
        Button signUp = new Button("Sign Up");
        Button signIn = new Button("Sign In");
        signIn.setUIID("navigationButton");
        Label signInMessage = new Label("Already have an account?");
        signUp.addActionListener(e -> {
            User user = new User();
            user.setEmail(email.getText());
            user.setMotDePasse(password.getText());
            user.setRole(role.getSelectedItem());
            user.setNom(nom.getText());
            user.setPrenom(prenom.getText());
            String response = UserService.getInstance().Register(user);
            if (response == "Account created successfully") {
                Dialog.show("Confirm", response, "OK", null);
                new ProfileInterface().show();
            } else {
                Dialog.show("Error", response, "OK", null);
            }
        });

        signIn.addActionListener(e -> {
            new LoginInterface().show();
        });
        Container content = BoxLayout.encloseY(
                new FloatingHint(email),
                new FloatingHint(prenom),
                new FloatingHint(nom),
                role,
                new FloatingHint(password),
                signUp,
                BoxLayout.encloseX(signInMessage, signIn)
        );
        add(content);
    }
}
