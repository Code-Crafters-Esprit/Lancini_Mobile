/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.service;

import codecrafters.lancini.entities.Secteur;
import codecrafters.lancini.tools.MaConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author user
 */
public class Service_Secteur {
    public ArrayList<Secteur> Coachs;
    public static Service_Secteur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public Service_Secteur() {
        req = new ConnectionRequest();
    }

     
    public static Service_Secteur getInstance() {
        if (instance == null) {
            instance = new Service_Secteur();
        }
        return instance;
    }
    
    
     public ArrayList<Secteur> parseCoach(String jsonText) {
        try {
             Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(obj);
               Secteur coach = new Secteur();
               float IdSecteur = Float.parseFloat(obj.get("idsecteur").toString());
               coach.setIdSecteur((int) IdSecteur);              
               coach.setNom(obj.get("nom").toString());        
               coach.setDescription(obj.get("description").toString());
               coach.setDateCreation(obj.get("datecreation").toString());
               coach.setDateModification(obj.get("datemodification").toString());
               System.out.println("Secteur créé : " + coach.getIdSecteur() + " " + coach.getNom() + " " + coach.getDescription() + " " + coach.getDateCreation() + " " + coach.getDateModification());

        Coachs.add(coach);
            }
        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }
        return Coachs;
    }

   public ArrayList<Secteur> findAll() {
    String url = MaConnection.BASE_URL + "mobileSecteur";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            Coachs = parseCoach(new String(req.getResponseData()));
            System.out.println(Coachs);
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return Coachs;
    }

   
   
public boolean deleteSecteur(int idSecteur) {
    String url = MaConnection.BASE_URL + "SupprimerSecteur/" + idSecteur;
    req.setUrl(url);

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            req.removeResponseListener(this);
            int status = evt.getResponseCode();
            if (status == 200) {
                String response = new String(req.getResponseData());
                System.out.println(response);
                resultOK = true;
            } else {
                System.out.println("Erreur lors de la suppression du secteur: " + status);
            }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}

    
    
    
    
    
    
    
    public void AddSecteur(String nom, String descr, String date) {
        
        String url = MaConnection.BASE_URL + "addSecteurM?nom=" + nom + "&description=" + descr + "&date_creation=" + date; 
        req.setUrl(url);
        req.setPost(true);
        System.out.println(url);
        System.out.println(req);

         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
     
       
//    public boolean ModifierSecteur( String s) {
//       String url = MaConnection.BASE_URL + "updateSecteur?idSecteur="+ nom + "&description=" + descr + "&date_creation=" + date;
//       req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //code success  http 200 ok
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
//        //System.out.println("url ==> " + url);
//        //System.out.println("data ==> " + req);
//        return resultOK;
//
//    }  
    
}
