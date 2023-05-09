/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.tools;

import codecrafters.lancini.service.UserService;

/**
 *
 * @author Youssef Ayed
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserService us = UserService.getInstance();
        us.Login("ayedy40@gmail.com", "test");
    }

}
