/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.gui;


import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.sun.java.accessibility.util.SwingEventMonitor;

/**
 *
 * @author Samar
 */
public class CommunityHome extends  Form{
    public CommunityHome(Form previous) {
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                ev -> {previous.showBack();} );
//        Resources res = null ;
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Looking to see?"));
        Button btnEvents = new Button("Events");
        Button btnPubs = new Button("Content");
        Button Map = new Button("Map");
        
//        
//        Tabs swipe = new Tabs();
//        Label spacer1 = new Label();
//        
//        addTab(swipe,res.getImage("sda1.jpg"),spacer1," ",""," ");


        btnEvents.addActionListener(e-> new EvenementListForm(this).show());
        btnPubs.addActionListener(e-> new PublicationListForm(this).show());
        Map.addActionListener(e-> new MapForm(this));



       
        addAll(btnEvents,btnPubs , Map );
        
       
       
    }
    
    
//    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
//        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
//        if(img.getHeight() < size) {
//            img = img.scaledHeight(size);
//        }
//        Label likes = new Label(likesStr);
//        Style heartStyle = new Style(likes.getUnselectedStyle());
//        heartStyle.setFgColor(0xff2d55);
//        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
//        likes.setIcon(heartImage);
//        likes.setTextPosition(RIGHT);
//
//        Label comments = new Label(commentsStr);
//        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
//        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
//            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
//        }
//        ScaleImageLabel image = new ScaleImageLabel(img);
//        image.setUIID("Container");
//        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
//        Label overlay = new Label(" ", "ImageOverlay");
//        
//        Container page1 = 
//            LayeredLayout.encloseIn(
//                image,
//                overlay,
//                BorderLayout.south(
//                    BoxLayout.encloseY(
//                            new SpanLabel(text, "LargeWhiteText"),
//                            spacer
//                        )
//                )
//            );
//
//        swipe.addTab("", page1);
//    }
//    
    
     public void showCommunityHome() {
            CommunityHome form = new CommunityHome(this);
        form.show();
    }
}
