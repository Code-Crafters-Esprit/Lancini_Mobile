/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import codecrafters.lancini.service.UserService;
import codecrafters.lancini.gui.StartUpInterface;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.plaf.UIManager;

/**
 *
 * @author Youssef Ayed
 */
public class LoginInterface extends Form {

    public LoginInterface() {
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        Command returnCommand = new Command("") {
            public void actionPerformed(ActionEvent evt) {
                new StartUpInterface().show();
            }
        };
        returnCommand.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK_IOS, UIManager.getInstance().getComponentStyle("TitleCommand")));
        tb.addCommandToLeftBar(returnCommand);
        setTitle("Sign In");
        //getTitleArea().setUIID("Container");
//        getToolbar().setUIID("Container");
//        getToolbar().getTitleComponent().setUIID("SigninTitle");
        getContentPane().setUIID("SignInForm");
        TextField username = new TextField("", "Username", 20, TextField.ANY);
        TextField password = new TextField("", "Password", 20, TextField.PASSWORD);
        username.setSingleLineTextArea(false);
        password.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
        signUp.setUIID("navigationButton");
        Label signUpMessage = new Label("Don't have an account?");
        //show SignUp form
        //signUp.addActionListener(e -> new SignUpForm(res).show());
        //signUp.setUIID("Link");
        Container content = BoxLayout.encloseY(
                new FloatingHint(username),
                new FloatingHint(password),
                signIn,
                BoxLayout.encloseX(signUpMessage, signUp)
        );
        signUp.addActionListener(e -> {
            new SignupInterface().show();
        });
        signIn.addActionListener(e -> {
            if (UserService.getInstance().Login(username.getText(), password.getText())) {
                new ProfileInterface().show();
            } else {
                Dialog.show("Error", "Invalid username or password", "OK", null);
            }
        });
        content.setScrollableY(true);
        content.setUIID("Container");
        content.getAllStyles().setPaddingTop(50);
        add(content);
    }
}
