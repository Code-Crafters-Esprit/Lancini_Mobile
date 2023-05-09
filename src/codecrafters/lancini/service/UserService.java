/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.service;

import codecrafters.lancini.entities.User;
import codecrafters.lancini.tools.MaConnection;
import codecrafters.lancini.tools.Session;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import java.util.ArrayList;
import com.codename1.io.JSONParser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import com.codename1.io.Preferences;
import java.util.List;

/**
 *
 * @author Youssef-Ayed
 */
public class UserService {

    public ArrayList<User> users;
    public boolean success;
    public String message;
    private ConnectionRequest req;
    public static UserService instance = null;
    JSONParser parser = new JSONParser();

    private UserService() {
        req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
    
    public boolean editProfile(User user){
        String url = MaConnection.BASE_URL + "/api/user/update/edit/" + user.getIdUser();
        ConnectionRequest request = new ConnectionRequest();
        
        request.setUrl(url);
        request.setPost(true);
        request.setContentType("application/json");
        String jsonBody = "{\"email\": \"" + user.getEmail() + "\", \"bio\": \"" + user.getBio()+ "\", \"nom\": \"" + user.getNom() + "\", \"prenom\": \"" + user.getPrenom() + "\", \"numtel\": \"" + user.getNumTel()+ "\"}";
        System.out.println(jsonBody);
        request.setRequestBody(jsonBody);
        
        request.addResponseListener(e -> {
            if(request.getResponseCode() == 200){
                success = true;
            }else {
                success = false;
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        
        return success;
    }

    public boolean Login(String username, String password) {
        String url = MaConnection.BASE_URL + "/api/login_check";
        req.setUrl(url);
        req.setPost(true);
        req.setContentType("application/json");
        String jsonBody = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
        //String jsonBody = "{\"username\": \"" + "ayedy40@gmail.com" + "\", \"password\": \"" + "test1234" + "\"}";
        System.out.println(jsonBody);
        req.setRequestBody(jsonBody);

        req.addResponseListener((e) -> {
            if (req.getResponseCode() == 200) {
                try {
                    String response = new String(req.getResponseData());
                    Map<String, Object> json = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(response.getBytes())));
                    String token = (String) json.get("token");
                    System.out.println("Token: " + token);
                    authenticateUser(token);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                System.out.println("Error response code: " + req.getResponseCode());
                success = false;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return success;
    }

    public String Register(User user) {
        String url = MaConnection.BASE_URL + "/api/register";
        req.setUrl(url);
        req.setPost(true);
        req.setContentType("application/json");
        String jsonBody = "{\"email\": \"" + user.getEmail() + "\", \"motdepasse\": \"" + user.getMotDePasse() + "\", \"nom\": \"" + user.getNom() + "\", \"prenom\": \"" + user.getPrenom() + "\", \"role\": \"" + user.getRole() + "\"}";
        req.setRequestBody(jsonBody);

        req.addResponseListener((e) -> {
            if (req.getResponseCode() == 200) {
                try {
                    String response = new String(req.getResponseData());
                    JSONParser parser = new JSONParser();
                    Map<String, Object> json = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(response.getBytes())));
                    System.out.println("Response: " + json);
                    boolean success = Boolean.parseBoolean(json.get("success").toString());
                    if (success) {
                        message = "Account created successfully";
                        String token = (String) json.get("token");
                        authenticateUser(token);
                    } else {
                        List<Map<String, Object>> errors = (List<Map<String, Object>>) json.get("errors");
                        Map<String, Object> firstError = errors.get(0);
                        message = "";
                        for (Map<String, Object> error : errors) {
                            System.out.println("error: " + error);
                            message += error.get("message") + "\n";
                        }
                    }

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } else {
                System.out.println("Error response code: " + req.getResponseCode());
                message = "Failed to connect to server";
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println("message: " + message);
        return message;
    }

    public boolean authenticateUser(String token) {
        String url = MaConnection.BASE_URL + "/api/user/info/" + token;
        ConnectionRequest req2 = new ConnectionRequest();
        req2.setUrl(url);
        req2.setPost(true);
        req2.addResponseListener((e) -> {
            if (req2.getResponseCode() == 200) {
                try {
                    JSONParser parser = new JSONParser();
                    Map<String, Object> json = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(new String(req2.getResponseData()).getBytes())));
                    System.out.println(json);
                    //List<Map<String, Object>> UserInfo = (List<Map<String, Object>>) json.get("root");
                    //Map<String, Object> userinfo = UserInfo.get(0);
                    User user = new User();
                    long Id = Math.round((Double) json.get("idUser"));
                    user.setIdUser((int) Id);
                    user.setNom((String) json.get("nom"));
                    user.setPrenom((String) json.get("prenom"));
                    user.setEmail((String) json.get("email"));
                    user.setRole((String) json.get("role"));
                    user.setPhotoPath((String) json.get("photopath"));
                    user.setNumTel((String) json.get("numtel"));
                    System.out.println(user);
                    Session.setToken(token);
                    Session.setCurrentUser(user);
                    success = true;
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            } else {
                System.out.println("Error response code: " + req.getResponseCode());
                System.out.println(req2.getResponseErrorMessage());
                success = false;
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req2);
        return success;
    }
}
