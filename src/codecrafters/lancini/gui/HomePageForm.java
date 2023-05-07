package codecrafters.lancini.gui;

import codecrafters.lancini.myapp.MyApplication;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

public class HomePageForm extends Form {
Form current;
    public HomePageForm() {
      
        super("Lancini", BoxLayout.y());
  current=this;
        Button btnRec = new Button("Poser une réclamation");
        btnRec.addActionListener(e->new AjoutRecForm(current).show());
        add(btnRec);

        Button btnAvi = new Button("Ajouter un avis");
        btnAvi.addActionListener(e->new AjoutAviForm(current).show());
         
        add(btnAvi);

        Button btnAfficherRec = new Button("Afficher Réclamation");
        btnAfficherRec.addActionListener(e->new AfficherRecForm(current).show());
          
        add(btnAfficherRec);

        Button btnAfficherAvi = new Button("Afficher Avis");
        btnAfficherAvi.addActionListener(e->new ListAviForm(current).show());
         
        add(btnAfficherAvi);
    }
}
