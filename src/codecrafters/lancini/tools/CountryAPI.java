package codecrafters.lancini.tools;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class CountryAPI {
 ArrayList<String> nomsPays  ;
 private ConnectionRequest req;
private boolean resultOK;
private static CountryAPI instance = null;
private Resources res;
    
private CountryAPI() {
    req = new ConnectionRequest();
}
    
public static CountryAPI getInstance() {
    if (instance == null) {
        instance = new CountryAPI();
    }
    return instance;
}
 
 
 
    public ArrayList<String> parsePays(String jsonText) {
    try {
         nomsPays = new ArrayList<>();
        JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

        Map<String, Object> paysListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) paysListJson.get("root");
        for (Map<String, Object> obj : list) {
            // Récupération du nom de pays et ajout à la liste
            String nomPays = ((Map<String, String>)obj.get("name")).get("common");
            nomsPays.add(nomPays);
        }
        
                Collections.sort(nomsPays);


    } catch (IOException ex) {

    }
    
    return nomsPays;
}
    
public ArrayList<String> getAllPays() {
    String url = "https://restcountries.com/v3.1/all";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            nomsPays = parsePays(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return nomsPays;
 }
    
    
}


