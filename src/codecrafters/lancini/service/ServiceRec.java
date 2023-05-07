/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.service;

import codecrafters.lancini.entities.Reclamation;
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
 * @author ezzar
 */
public class ServiceRec {

    private ConnectionRequest req;
    private boolean resultOK;
    private static ServiceRec instance = null;
    private Resources res;
    private ArrayList<Reclamation> reclamations;

    private ServiceRec() {
        req = new ConnectionRequest();
    }

    public static ServiceRec getInstance() {
        if (instance == null) {
            instance = new ServiceRec();
        }
        return instance;
    }

    public ArrayList<Reclamation> parseReclamation(String jsonText) {
        try {
            reclamations = new ArrayList<>();
            JSONParser j = new JSONParser(); // Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> reclamationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reclamationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                //Création des Réclamations et récupération de leurs données
                Reclamation r = new Reclamation();

                float id = Float.parseFloat(obj.get("id").toString());
                int idAsInt = (int) id;
                r.setId(idAsInt);

                String nom = obj.get("nom").toString();
                r.setNom(nom);

                String prenom = obj.get("prenom").toString();
                r.setPrenom(prenom);

                String description = obj.get("description").toString();
                r.setDescription(description);

                String sujet = obj.get("sujet").toString();
                r.setSujet(sujet);

                String email = obj.get("email").toString();
                r.setEmail(email);

                String tel = obj.get("tel").toString();
                r.setTel(tel);

                String etat = obj.get("etat").toString();
                r.setEtat(etat);

                //Ajouter la réclamation extraite de la réponse Json à la liste
                reclamations.add(r);
            }

        } catch (IOException ex) {

        }

        return reclamations;
    }

    public ArrayList<Reclamation> getAllReclamations() {
        String url = MaConnection.BASE_URL + "/jsonReclamation";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseReclamation(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }

    public boolean addReclamation(Reclamation r) {
        String url = MaConnection.BASE_URL + "/api/ajoutReclamation?nom=" + r.getNom() + "&prenom=" + r.getPrenom() + "&description=" + r.getDescription() + "&sujet=" + r.getSujet() + "&email=" + r.getEmail() + "&tel=" + r.getTel() + "&etat=" + r.getEtat();
        req.setUrl(url); //création de l'URL
        req.setPost(false);
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
}

