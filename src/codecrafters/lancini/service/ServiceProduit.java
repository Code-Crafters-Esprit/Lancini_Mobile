/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.service;

import codecrafters.lancini.entities.Produit;
import codecrafters.lancini.tools.MaConnection;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;


/**
 *
 * @author bhk
 */
public class ServiceProduit {

    public ArrayList<Produit> produits;

    public static ServiceProduit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceProduit() {
        req = new ConnectionRequest();
    }

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

 public  ArrayList<Produit>getAllProduits() {
        ArrayList<Produit> result = new ArrayList<>();
        
        String url = MaConnection.BASE_URL+"/produit/AllProduits";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapProduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapProduits.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Produit p = new Produit();
                        float id = Float.parseFloat(obj.get("idProduit").toString());
                        
                    p.setIdProduit((int)id);
                    p.setCategorie(obj.get("categorie").toString());
                    p.setNom(obj.get("nom").toString());
                    p.setDescription(obj.get("description").toString());
                    p.setImage(obj.get("image").toString());
                    p.setPrix(Float.parseFloat(obj.get("prix").toString()));
                        
                     // Date
                    String dateConverter = obj.get("date").toString();
int startIndex = dateConverter.indexOf("timestamp") + 10;
int endIndex = dateConverter.lastIndexOf("}");
if (startIndex >= 0 && endIndex >= 0 && startIndex < endIndex) {
    String timestampString = dateConverter.substring(startIndex, endIndex);
    try {
        long timestamp = Long.parseLong(timestampString);
        Date date = new Date(timestamp * 1000);
        p.setDate(date);
    } catch (NumberFormatException e) {
        // Handle the case where the timestamp cannot be parsed
        // Display an error message or use a default value for the date
     p.setDate(new Date());
    }
} else {
    
    // Handle the case where the substring indices are invalid
    // Display an error message or use a default value for the date
    p.setDate(new Date());
}

//             String dateConverter = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().lastIndexOf("}"));
//long timestamp = Long.parseLong(dateConverter);
//Date date = new Date(timestamp * 1000);
//fe.setDate(date);


//             String dateConverter = obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10, obj.get("date").toString().lastIndexOf("}"));
//long timestamp = Long.parseLong(dateConverter);
//Date date = new Date(timestamp * 1000);
//fe.setDate(date);

                        //insert data into ArrayList result
                        result.add(p);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    
    
    
//    Detail Reclamation bensba l detail n5alihoa lel5r ba3d delete+update
    
   public Produit DetailProduit(int idProduit, Produit p) {
    String url = MaConnection.BASE_URL + "/produit/Produits?" + idProduit;
    req.setUrl(url);

    req.addResponseListener(((evt) -> {
        String str = new String(req.getResponseData());
        if (str != null) {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(str.toCharArray()));

                p.setCategorie(obj.get("categorie").toString());
                p.setNom(obj.get("nom").toString());
                p.setDescription(obj.get("description").toString());
                p.setImage(obj.get("image").toString());
                p.setPrix(Float.parseFloat(obj.get("prix").toString()));

            } catch (IOException ex) {
                System.out.println("error related to sql 🙁 " + ex.getMessage());
            }
            System.out.println("data === " + str);
        }
    }));

    NetworkManager.getInstance().addToQueueAndWait(req);
    return p;
}


}
