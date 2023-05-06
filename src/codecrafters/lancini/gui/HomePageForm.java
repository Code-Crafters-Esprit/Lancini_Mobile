package codecrafters.lancini.gui;

import codecrafters.lancini.gui.AjoutAviForm;
import codecrafters.lancini.gui.AjoutRecForm;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

public class HomePageForm extends Form {

    public HomePageForm() {
        super("Lancini", BoxLayout.y());

  
        Button btnRec = new Button("Poser une réclamation");
        btnRec.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
                AjoutRecForm ajoutRecForm = new AjoutRecForm();
                ajoutRecForm.show();
            }
        });
        add(btnRec);

  
        Button btnAvi = new Button("Ajouter un avis");
        btnAvi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
                AjoutAviForm ajoutAviForm = new AjoutAviForm();
                ajoutAviForm.show();
            }
        });
        add(btnAvi);
    }
}
