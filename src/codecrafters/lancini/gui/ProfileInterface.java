/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import codecrafters.lancini.entities.User;
import codecrafters.lancini.service.SMSService;
import codecrafters.lancini.tools.Session;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Form;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import javafx.scene.control.Separator;

/**
 *
 * @author Youssef Ayed
 */
public class ProfileInterface extends Form {

    private User currentUser = Session.getCurrentUser();

    public ProfileInterface() {
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        Command returnCommand = new Command("") {
            public void actionPerformed(ActionEvent evt) {
                new HomeInterface().showHomeInterface();
            }
        };
        returnCommand.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK_IOS, UIManager.getInstance().getComponentStyle("TitleCommand")));
        tb.addCommandToLeftBar(returnCommand);
        setTitle("Profile");
        Label fullName = new Label(currentUser.getNom() + " " + currentUser.getPrenom());
        Label emailLabel = new Label("Email :");
        Label email = new Label(currentUser.getEmail());
        Label role = new Label(currentUser.getRole());
        Label bioLabel = new Label("Bio :");
        String bioString = currentUser.getBio() == null || currentUser.getBio().isEmpty() ? "No bio added" : currentUser.getBio();
        Label bio = new Label(bioString);
        Label phoneLabel = new Label("Phone :");
        String phoneString = currentUser.getNumTel() == null || currentUser.getNumTel().isEmpty() ? "No phone number added" : currentUser.getNumTel();        
        Label phone = new Label(phoneString);
        Button editProfile = new Button("Edit profile");
        String editPhoneString = currentUser.getNumTel() == null || currentUser.getNumTel().isEmpty() ? "Add phone number" : "Edit phone number";
        Button editPhoneNumber = new Button(editPhoneString);
        Button logoutButton = new Button("Log out");
        int screenWidth = Display.getInstance().getDisplayWidth();
        int imageSize = (int) (screenWidth * 0.35);
        
        fullName.getAllStyles().setMargin(Component.TOP, 10);
        fullName.setUIID("CenterLabel");
        email.setUIID("CenterLabel");
        phone.setUIID("CenterLabel");
        bio.setUIID("CenterLabel");
        Container headContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        if(currentUser.getPhotoPath() != null && currentUser.getPhotoPath().length() > 0){
            byte[] imageData = Base64.decode(currentUser.getPhotoPath().getBytes());
            Image image = EncodedImage.createImage(imageData, 0, imageData.length);
            //Get screen width and set the image size

            image = image.scaled(imageSize, imageSize);
            ImageViewer imageViewer = new ImageViewer(image);
            //styles
            imageViewer.getAllStyles().setMargin(Component.TOP, 20);
            imageViewer.getAllStyles().setMargin(Component.BOTTOM, 10);
            imageViewer.getAllStyles().setAlignment(Component.CENTER);
            
            headContainer.addAll(imageViewer, fullName);
        }else{
            headContainer.add(fullName);
        }
        
        
        Container line = new Container();
        line.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        line.getStyle().setBgColor(0xCCCCCC);
        line.setPreferredSize(new Dimension(screenWidth, 2));
        line.getAllStyles().setMarginBottom(20);
        headContainer.add(line);
        Container content = BoxLayout.encloseY(
                headContainer,
                emailLabel,
                email,
                phoneLabel,
                phone,
                bioLabel,
                bio,
                editProfile,
                editPhoneNumber,
                logoutButton
        );
        logoutButton.addActionListener(e->{
            Session.setCurrentUser(null);
            Session.setToken(null);
            new StartUpInterface().show();
        });
        editProfile.addActionListener(e -> {
            new EditProfileInterface().show();
            //String SMS = SMSService.sendVerificationSMS("+21654801411");
        });
        editPhoneNumber.addActionListener(e -> {
            new EditPhoneNumberForm().show();
        });
        add(content);
    }
}
