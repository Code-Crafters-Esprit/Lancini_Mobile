/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.service;

import codecrafters.lancini.entities.Offre;
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
public class service_Offre {
     public ArrayList<Offre> Coachs;
    public static service_Offre instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    public service_Offre() {
        req = new ConnectionRequest();
    }

     
    public static service_Offre getInstance() {
        if (instance == null) {
            instance = new service_Offre();
        }
        return instance;
    }
    
    
     public ArrayList<Offre> parseCoach(String jsonText) {
        try {
             Coachs = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationListJson.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println(obj);
               Offre coach = new Offre();
               float IdOffre = Float.parseFloat(obj.get("idoffre").toString());
               coach.setIdOffre((int) IdOffre);              
               coach.setNom(obj.get("nom").toString());        
               coach.setDescription(obj.get("description").toString());
               coach.setDateDebut(obj.get("datedebut").toString());
               coach.setDateFin(obj.get("datefin").toString());
               coach.setCompetence(obj.get("competence").toString());
               coach.setTypeOffre(obj.get("typeoffre").toString());

               System.out.println("offre créé : " + coach.getIdOffre() + " " + coach.getNom() + " " + coach.getDescription() + " " + coach.getDateDebut() + " " + coach.getDateFin() + " " +coach.getCompetence()+ " " +coach.getTypeOffre());

        Coachs.add(coach);
            }
        } catch (IOException ex) {
            System.out.println("Exception in parsing reclamations ");
        }
        return Coachs;
    }

   public ArrayList<Offre> findAll() {
    String url = MaConnection.BASE_URL + "getOffre";
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
   
//    public void AddSecteur(String nom, String descr, String dateD , String dateF , String type , String compt ) {
//        
//        String url = MaConnection.BASE_URL + "addOffre?nom=" + nom + "&description=" + descr + "&date_debut=" + dateD + "&date_debut=" + ; //création de l'URL
//        req.setUrl(url);
//        System.out.println(url);
//        System.out.println(req);
//
//         req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//    }

   
   
    public boolean deleteSecteur(int idOffre) {
    String url = MaConnection.BASE_URL + "SupprimerOffre/" + idOffre;
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
                System.out.println("Erreur lors de la suppression de l'offre : " + status);
            }
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
    
    public boolean ModifierSecteur( Secteur c) {
        
       String url = MaConnection.BASE_URL + "updateSecteur?idSecteur=" + c.getIdSecteur()+"&nom=" + c.getNom()+"&description=" + c.getDescription()+"&DateCreation=" + c.getDateCreation()+"&DateModification=" + c.getDateModification();
              
        req.setUrl(url); 
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        //System.out.println("url ==> " + url);
        //System.out.println("data ==> " + req);
        return resultOK;

    }  
    
    
}
