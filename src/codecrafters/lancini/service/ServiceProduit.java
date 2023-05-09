/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.service;

import codecrafters.lancini.entities.Produit;
import codecrafters.lancini.gui.User;
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

    public ArrayList<Produit> getAllProduits() {
        ArrayList<Produit> result = new ArrayList<>();

        String url = MaConnection.BASE_URL + "/produit/AllProduits";
        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapProduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapProduits.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Produit p = new Produit();
                        float id = Float.parseFloat(obj.get("idProduit").toString());

                        p.setIdProduit((int) id);
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

                            p.setDate(new Date());
                        }

                        // Vendeur
                        Map<String, Object> vendeurMap = (Map<String, Object>) obj.get("vendeur");
                        User vendeur = new User();
                        float vendeurid =Float.parseFloat(vendeurMap.get("idUser").toString());
                        vendeur.setIdUser((int)vendeurid);
                        vendeur.setNom(vendeurMap.get("nom").toString());
                        vendeur.setPrenom(vendeurMap.get("prenom").toString());
                        vendeur.setEmail(vendeurMap.get("email").toString());
                        p.setVendeur(vendeur);

                        result.add(p);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;

    }

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

                // Vendeur
                Map<String, Object> vendeurMap = (Map<String, Object>) obj.get("vendeur");
                User vendeur = new User();
                vendeur.setIdUser(Integer.parseInt(vendeurMap.get("idUser").toString()));
                vendeur.setNom(vendeurMap.get("nom").toString());
                vendeur.setPrenom(vendeurMap.get("prenom").toString());
                vendeur.setEmail(vendeurMap.get("email").toString());
                p.setVendeur(vendeur);

            } catch (IOException ex) {
                System.out.println("error related to sql 🙁 " + ex.getMessage());
            }
            System.out.println("data === " + str);
        }
    }));

    NetworkManager.getInstance().addToQueueAndWait(req);
    return p;
}

    public boolean addProduit(Produit p) {

        String url = MaConnection.BASE_URL + "/produit/addProduitJSON/new?categorie=" + p.getCategorie() + "&nom=" + p.getNom() + "&description=" + p.getDescription() + "&image=" + p.getImage() + "&prix=" + p.getPrix() + "&date=" + p.getDate() + "&=vendeur" + p.getVendeur();

        req.setUrl(url);
        req.setPost(false);

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

    public boolean deleteProduit(int idProduit) {
        String url = MaConnection.BASE_URL + "/produit/deleteProduitJSON/" + idProduit;

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    //Update 
    public boolean updateProduit(Produit p) {
        String url = MaConnection.BASE_URL + "/produit/updateProduitJSON?idProduit=" + p.getIdProduit() + "&categorie=" + p.getCategorie() + "&nom=" + p.getNom() + "&description=" + p.getDescription() + "&image=" + p.getImage() + "&prix=" + p.getPrix() + "&date=" + p.getDate() + "&vendeur=" + p.getVendeur();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOK;

    }
}
