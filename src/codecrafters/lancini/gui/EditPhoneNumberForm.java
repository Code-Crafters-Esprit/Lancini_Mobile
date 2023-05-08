/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;

import codecrafters.lancini.entities.User;
import codecrafters.lancini.service.SMSService;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.UIManager;
import com.codename1.util.Base64;

/**
 *
 * @author Youssef Ayed
 */
public class EditPhoneNumberForm extends Form {

    private User currentUser = Session.getCurrentUser();
    private boolean isVerfiying = false;
    private String verificationCode;
    private String NumTel;

    public EditPhoneNumberForm() {
        Toolbar tb = new Toolbar();
        setToolbar(tb);
        Command returnCommand = new Command("") {
            public void actionPerformed(ActionEvent evt) {
                new ProfileInterface().show();
            }
        };
        returnCommand.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK_IOS, UIManager.getInstance().getComponentStyle("TitleCommand")));
        tb.addCommandToLeftBar(returnCommand);
        setTitle("Phone");

        if (currentUser != null) {
            Label fullName = new Label(currentUser.getNom() + " " + currentUser.getPrenom());

            byte[] imageData = Base64.decode(currentUser.getPhotoPath().getBytes());
            Image image = EncodedImage.createImage(imageData, 0, imageData.length);
            //Get screen width
            int screenWidth = Display.getInstance().getDisplayWidth();
            int imageSize = (int) (screenWidth * 0.35);
            image = image.scaled(imageSize, imageSize);
            ImageViewer imageViewer = new ImageViewer(image);

            //styles
            imageViewer.getAllStyles().setMargin(Component.TOP, 20);
            imageViewer.getAllStyles().setMargin(Component.BOTTOM, 10);
            imageViewer.getAllStyles().setAlignment(Component.CENTER);
            fullName.getAllStyles().setMargin(Component.TOP, 10);
            fullName.setUIID("CenterLabel");

            //create header
            Container headContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            headContainer.addAll(imageViewer, fullName);

            //Add header line
            Container line = new Container();
            line.setLayout(new BoxLayout(BoxLayout.X_AXIS));
            line.getStyle().setBgColor(0xCCCCCC);
            line.setPreferredSize(new Dimension(screenWidth, 2));
            line.getAllStyles().setMarginBottom(20);
            headContainer.add(line);
            add(headContainer);
        }
        Container bodyContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        bodyContainer.setUIID("roundedBorderContainer");

        Label phoneLabel = new Label("Phone :");
        TextField phone = new TextField("");
        phone.setHint("Enter your phone number");
        //button
        Button confirmButton = new Button("Confirm");
        confirmButton.addActionListener(e -> {
            if (!isVerfiying) {
                NumTel = phone.getText();
                verificationCode = SMSService.sendVerificationSMS(NumTel);
                phoneLabel.setText("Confirmation code");
                phone.setHint("Enter verification code");
                phone.setText("");
                isVerfiying = true;
            } else {
                if (verificationCode.equals(phone.getText())) {
                    currentUser.setNumTel(NumTel);
                    Session.setCurrentUser(currentUser);
                    boolean succed = UserService.getInstance().editProfile(currentUser);
                    
                    if (!succed) {
                        Dialog.show("Sorry", "There was a connection error", "OK", null);
                    }
                    new ProfileInterface().show();
                }
            }

        });

        bodyContainer.addAll(
                phoneLabel,
                phone,
                confirmButton
        );
        add(bodyContainer);
    }
}
