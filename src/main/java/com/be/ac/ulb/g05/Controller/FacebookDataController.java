package com.be.ac.ulb.g05.Controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Controller for FacebookData
 * @author @iyamani
 * @codereview @otrangan
 */
public class FacebookDataController extends AbstractController {

    /**
     * Text Field on the FXML
     */
    @FXML
    public JFXTextField accountToFollow;


    /**
     * AccessToken given us by Facebook
     */
    private static final String accessToken = "EAAJ0YUQvIMQBAGBxCRFOk8PM6IOoSfmabqARoUCbjEt3lAziOMnrQjnhfvVibUjUVH3I" +
            "svyQS8vNV22YmseJJ4UTgExXvLzSi8ZAlsaTvyzsa0Urd2G1u6EpXroFAH8Jf5OhnXmGZB0OIfbyjPDhCmVWK8ROUZB1bgunyuIfOvZCe" +
            "k4ZCyEvxKJVoJ1OGr7xPGZA3zL6aSTgcAQHFP5rzOj4kwCepXdpfBSM4MWCMFzAZDZD";

    /**
     * Follow an account on facebook
     */
    @SuppressWarnings({"unused"})
    public void followAccount() {
        try {
            String followAccount = accountToFollow.getText();
            String url = "https://graph.facebook.com/Pages/search?q=" + followAccount + "&access_token=" + accessToken;

            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject apiResponse = new JSONObject(response.toString());

        }
        catch (IOException e){
            Router.showAlert("FeedBuzz App is still waiting for the approval of facebook \n Come back later maybe"
            ,"Information");
        }

    }
}
