package codecrafters.lancini.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
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
 * @author LENOVO
 */
public class HomeInterface  extends  Form {
   
     public HomeInterface() {
//        Resources res = null ;
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Welcome to LANCINI MOBILE"));
        Button btnOffers = new Button("Offers");
        Button btnCommunity = new Button("Community");
        Button btnMarket = new Button("Lancini Market");
        Button btnVerified = new Button("Get Verified");
        Button btnSupport = new Button("Support");
       
       
//        
//        Tabs swipe = new Tabs();
//        Label spacer1 = new Label();
//        
//        addTab(swipe,res.getImage("sda1.jpg"),spacer1," ",""," ");


        btnOffers.addActionListener(e-> new EvenementListForm(this).show());
        btnCommunity.addActionListener(e-> new CommunityHome(this).show());
        btnMarket.addActionListener(e-> new CommunityHome(this).show());
        btnVerified.addActionListener(e-> new CommunityHome(this).show());
        btnSupport.addActionListener(e-> new CommunityHome(this).show());
       
       



       
        addAll(btnOffers, btnCommunity, btnMarket, btnVerified, btnSupport);
       
       
       
    }
   
      public void showHomeInterface() {
            HomeInterface form = new HomeInterface();
        form.show();
    }
   
   
   
}
