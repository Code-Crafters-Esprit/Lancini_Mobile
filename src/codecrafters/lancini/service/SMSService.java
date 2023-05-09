/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codecrafters.lancini.service;

import com.codename1.components.ToastBar;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.util.Base64;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Youssef Ayed
 */
public class SMSService {

    private static String accountSID = "AC4a703984c8886fddf975968fab746ca7";
    private static String authToken = "34e1797d57c21aa81f8eca6ce3db769a";
    private static String fromPhone = "+12766002234";

    public static String generateVerificationCode() {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder code = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(chars.length());
            code.append(chars.charAt(index));
        }
        System.out.println(code);
        return code.toString();
    }

    public static String sendVerificationSMS(String destinationPhone) {
        String val = generateVerificationCode();
        Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
                queryParam("To", destinationPhone).
                queryParam("From", fromPhone).
                queryParam("Body", val).
                header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
                getAsJsonMap();
        if (result.getResponseData() != null) {
            String error = (String) result.getResponseData().get("error_message");
            if (error != null) {
                ToastBar.showErrorMessage(error);
            }
        } else {
            ToastBar.showErrorMessage("Error sending SMS: " + result.getResponseCode());
        }
        return val;
    }

}
