/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.service;

import codecrafters.lancini.entities.Evenement;
import codecrafters.lancini.entities.Publication;
import codecrafters.lancini.entities.User;
import codecrafters.lancini.tools.MaConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Samar
 */
public class PublicationService {
    
     private ConnectionRequest req;
    private boolean resultOK;
    private static PublicationService instance = null;
    private Resources res;
    private ArrayList<Publication> publications;
    
     private PublicationService() {
        req = new ConnectionRequest();
    }
     
      public static PublicationService getInstance() {
        if (instance == null) {
            instance = new PublicationService();
        }
        return instance;
    }
      
         public ArrayList<Publication> parsePublication(String jsonText) {
        try {
            publications = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) userListJson.get("root");
            for (Map<String, Object> obj : list) {
                //Création des Users et récupération de leurs données
                Publication e = new Publication();
                
                
                 float id =  Float.parseFloat(obj.get("id").toString());
                 int idAsInt = (int) id;
                e.setIdPub((int) idAsInt);
                
                String libelle = obj.get("libelle").toString();
                e.setLibelle(libelle);
                
                String description = obj.get("description").toString();
                e.setDescription(description);
        

                
                String date = obj.get("datepub").toString();
                e.setDatePub(date);
                
              //  float proprietaire  =  Float.parseFloat(obj.get("proprietaire ").toString());
            //  int idAsIntP = (int) proprietaire ;              
              //  e.setProprietaire(4);
                
                
//                v.setCouleur(obj.get("couleur").toString());
//                v.setEtat(obj.get("etat").toString());

                //Ajouter l event extrait de la réponse Json à la liste
                publications.add(e);
            }

        } catch (IOException ex) {

        }
        
        return publications;
    }
      
  public ArrayList<Publication> getAllPublications() {
       //  ArrayList<Evenement> listEvenement = new ArrayList<>();
    //    int id = 2;
        String url = MaConnection.BASE_URL + "/jsonPublication";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                publications = parsePublication(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return publications ;
    }
      
      
      
      

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
