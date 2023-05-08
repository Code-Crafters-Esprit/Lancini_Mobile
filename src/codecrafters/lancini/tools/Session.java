/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.tools;

import codecrafters.lancini.entities.User;

/**
 *
 * @author Youssef Ayed
 */
public class Session {

    private static User currentUser;
    private static String Token;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static String getToken() {
        return Token;
    }

    public static void setToken(String Token) {
        Session.Token = Token;
    }

}
