/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import codecrafters.lancini.entities.User;
import codecrafters.lancini.service.UserService;
import codecrafters.lancini.tools.Session;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.util.Base64;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.plaf.UIManager;

/**
 *
 * @author Youssef Ayed
 */
public class EditProfileInterface extends Form{
    User currentUser = Session.getCurrentUser();
    public EditProfileInterface() {
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        Command returnCommand = new Command("") {
            public void actionPerformed(ActionEvent evt) {
                new ProfileInterface().show();
            }
        };
        returnCommand.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK_IOS, UIManager.getInstance().getComponentStyle("TitleCommand")));
        tb.addCommandToLeftBar(returnCommand);
        setTitle("Edit Profile");
        Label fullName = new Label(currentUser.getNom() + " " + currentUser.getPrenom());
        Label emailLabel = new Label("Email :");
        TextField name = new TextField(currentUser.getNom());
        Label nameLabel = new Label("First Name :");
        TextField lastName = new TextField(currentUser.getPrenom());
        Label lastNameLabel = new Label("Last Name :");
        TextField email = new TextField(currentUser.getEmail());
        Label bioLabel = new Label("Bio :");
        TextField bio = new TextField(currentUser.getBio());
        Label phoneLabel = new Label("Phone :");
        TextField phone = new TextField(currentUser.getNumTel());
        Button editProfile = new Button("Save");
        int screenWidth = Display.getInstance().getDisplayWidth();
        int imageSize = (int) (screenWidth * 0.35);
        
        fullName.getAllStyles().setMargin(Component.TOP, 10);
        fullName.setUIID("CenterLabel");
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
                nameLabel,
                name,
                lastNameLabel,
                lastName,
                emailLabel,
                email,
                phoneLabel,
                phone,
                bioLabel,
                bio,
                editProfile
        );
        editProfile.addActionListener(e -> {
            User updatedUser = currentUser;
            int sameCounter = 0;
            if(currentUser.getEmail() == null ? "".equals(email.getText()) : currentUser.getEmail().equals(email.getText())){
                sameCounter++;
            }else{
                updatedUser.setEmail(email.getText());
            }
            if(currentUser.getBio() == null ? "".equals(bio.getText()) : currentUser.getBio().equals(bio.getText())){
                sameCounter++;
            }else{
                updatedUser.setBio(bio.getText());
            }
            if(currentUser.getNumTel() == null ? "".equals(phone.getText()) : currentUser.getNumTel().equals(phone.getText())){
                sameCounter++;
            }else{
                updatedUser.setNumTel(phone.getText());
            }
            if(currentUser.getNom() == null ? "".equals(name.getText()) : currentUser.getNom().equals(name.getText())){
                sameCounter++;
            }else{
                updatedUser.setNom(name.getText());
            }
            if(currentUser.getPrenom() == null ? "".equals(lastName.getText()) : currentUser.getPrenom().equals(lastName.getText())){
                sameCounter++;
            }else{
                updatedUser.setPrenom(lastName.getText());
            }
            if(sameCounter == 5){
                Dialog.show("Error", "You didn't change anything", "OK", null);
            }else{
                boolean succed = UserService.getInstance().editProfile(updatedUser);
                if(succed){
                    Session.setCurrentUser(updatedUser);
                    currentUser = updatedUser;
                    new ProfileInterface().show();
                }else{
                    Dialog.show("Sorry", "There was a connection error", "OK", null);
                }
            }
        });
        add(content);
    }
    
}
