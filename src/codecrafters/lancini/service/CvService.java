/*
 * to change this license header, choose License Headers in Project Properties.
 * to change this template file, choose tools | templates
 * and open the template in the editor.
 */
package codecrafters.lancini.service;

import codecrafters.lancini.entities.Cv;
import codecrafters.lancini.tools.MaConnection;
import com.codename1.components.ScaleImageLabel;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.google.zxing.qrcode.encoder.QRCode;
import java.io.OutputStream;

/**
 *
 * @author LENOVO
 */
public class CvService {

    public ArrayList<Cv> cv;
    private ConnectionRequest req;
    private boolean resultOK;
    public static CvService instance = null;
    private Resources res;
    private ArrayList<Cv> cvs;

    private CvService() {
        req = new ConnectionRequest();
    }

    public static CvService getInstance() {
        if (instance == null) {
            instance = new CvService();
        }
        return instance;
    }

    public ArrayList<Cv> parsecv(String jsonText) {
        try {
            cvs = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> userListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) userListJson.get("root");
            for (Map<String, Object> obj : list) {
                //Création des Users et récupération de leurs données
                Cv cv = new Cv();

                Double idCv = Double.parseDouble(obj.get("idcv").toString());
                int id = (int) Math.round(idCv);

                cv.setIdCv(id);

                String nom = obj.get("nom").toString();
                cv.setnom(nom);

                String prenom = obj.get("prenom").toString();
                cv.setprenom(prenom);

                // String datedenaissance = obj.get("DateDeNaissance").toString();
                cv.setdateNaissance("2023-10-10");

                String sexe = obj.get("sexe").toString();
                cv.setsexe(sexe);

                String cin = obj.get("cin").toString();
                cv.setCin(cin);

                String adresse = obj.get("adresse").toString();
                cv.setadresse(adresse);

                String email = obj.get("email").toString();
                cv.setemail(email);

                String langue = obj.get("langue").toString();
                cv.setLangue(langue);

                String education = obj.get("education").toString();
                cv.setEducation(education);

                //Ajouter le cv  extrait de la réponse Json à la liste
                cvs.add(cv);
            }

        } catch (IOException ex) {

        }

        return cvs;
    }

    public ArrayList<Cv> getAllcvs() {

        String url = MaConnection.BASE_URL + "cv/showcvsmobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cvs = parsecv(new String(req.getResponseData()));
                System.out.println(cvs);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cvs;
    }

    public boolean addCv(Cv cv) {
        String date = cv.getDateNaissance().toString().substring(0, 10);
        String url = MaConnection.BASE_URL + "cv/addcvmobile/1" + "?nom=" + cv.getNom() + "&prenom=" + cv.getPrenom() + "&sexe=" + cv.getSexe() + "&dateNaissance=" + cv.getDateNaissance() + "&cin=" + cv.getCin() + "&adresse=" + cv.getAdresse() + "&email=" + cv.getEmail() + "&langue=" + cv.getLangue() + "&education=" + cv.getEducation();

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

    public void deleteCv(int idCv) {

        Dialog d = new Dialog();
        if (d.show("Delete cv"
                + "..", "Do you really want to remove this cv", "Yes", "No")) {

            req.setUrl(MaConnection.BASE_URL + "cv/deletecvmobile/" + idCv);

            NetworkManager.getInstance().addToQueueAndWait(req);
            d.dispose();
        }
    }

    public boolean editCv(Cv cv) {
        String url = MaConnection.BASE_URL + "cv/editcvmobile/" + cv.getIdCv() + "?nom=" + cv.getNom() + "&prenom=" + cv.getPrenom() + "&sexe=" + cv.getSexe() + "&dateNaissance=" + cv.getDateNaissance() + "&cin=" + cv.getCin() + "&adresse=" + cv.getAdresse() + "&email=" + cv.getEmail() + "&langue=" + cv.getLangue() + "&education=" + cv.getEducation();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
    
    
    public boolean pdf(Cv cv) {
    String url = MaConnection.BASE_URL + "cv/printcvmobile/" +cv.getIdCv();
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setHttpMethod("GET");
    System.out.println(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            byte[] data = (byte[]) req.getResponseData();
            try {
                String filePath = FileSystemStorage.getInstance().getAppHomePath() + "cv.pdf";
OutputStream os = FileSystemStorage.getInstance().openOutputStream(filePath);

                os.write(data);
                os.close();
                // Display the PDF file using the default PDF viewer
                Display.getInstance().execute(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            resultOK = req.getResponseCode() == 200; 
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}

    
}
