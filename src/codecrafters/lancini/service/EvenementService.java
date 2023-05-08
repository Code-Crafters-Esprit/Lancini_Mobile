/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.service;

import codecrafters.lancini.entities.Evenement;
import codecrafters.lancini.tools.MaConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
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
public class EvenementService {
    
    
        private ConnectionRequest req;
    private boolean resultOK;
    private static EvenementService instance = null;
    private Resources res;
    private ArrayList<Evenement> Evenements;
    
    private EvenementService() {
        req = new ConnectionRequest();
    }
    
    public static EvenementService getInstance() {
        if (instance == null) {
            instance = new EvenementService();
        }
        return instance;
    }
    
    public ArrayList<Evenement> parseEvenement(String jsonText) {
        try {
            Evenements = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) userListJson.get("root");
            for (Map<String, Object> obj : list) {
                //Création des Users et récupération de leurs données
                Evenement e = new Evenement();
                
                
                 float id =  Float.parseFloat(obj.get("id").toString());
                 int idAsInt = (int) id;
                e.setIdEvent((int) idAsInt);
                
                String titre = obj.get("titre").toString();
                e.setTitre(titre);
                
                String sujet = obj.get("sujet").toString();
                e.setSujet(sujet);
                
                String lieu = obj.get("lieu").toString();
                e.setLieu(lieu);
                
                String horaire = obj.get("horaire").toString();
                e.setHoraire(horaire);
                
                String date = obj.get("date").toString();
                e.setDateEvent(date);
                
              //  float proprietaire  =  Float.parseFloat(obj.get("proprietaire ").toString());
            //  int idAsIntP = (int) proprietaire ;              
                e.setProprietaire(2);
                
                
//                v.setCouleur(obj.get("couleur").toString());
//                v.setEtat(obj.get("etat").toString());

                //Ajouter l event extrait de la réponse Json à la liste
                Evenements.add(e);
            }

        } catch (IOException ex) {

        }
        
        return Evenements;
    }

    public ArrayList<Evenement> getAllEvenements() {
       //  ArrayList<Evenement> listEvenement = new ArrayList<>();
    //    int id = 2;
        String url = MaConnection.BASE_URL + "/jsonEvenement";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Evenements = parseEvenement(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Evenements ;
    }
    
     public boolean addEvenement(Evenement e) {
        String date = e.getDateEvent().toString().substring(0, 10);
        String url = MaConnection.BASE_URL + "/api/ajoutEvenement?titre=" + e.getTitre()+ "&sujet=" + e.getSujet()+"&lieu=" + e.getLieu()+ "&horaire=" + e.getHoraire()+ "&date=" + e.getDateEvent()+ "&proprietaire=" + 2; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
      public void deleteEvenement(float id) {

        Dialog d = new Dialog();
        if (d.show("Delete Evenement"
                + "..", "Do you really want to remove this Event", "Yes", "No")) {

            req.setUrl(MaConnection.BASE_URL + "/api/delete/evenement/" + id);
       
            NetworkManager.getInstance().addToQueueAndWait(req);
        d.dispose();
    }
}
     
     
    
}
