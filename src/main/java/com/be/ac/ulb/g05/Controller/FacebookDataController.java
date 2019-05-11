package com.be.ac.ulb.g05.Controller;


import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.be.ac.ulb.g05.Controller.AddFromMapController.showAlert;

/**
 * @author @iyamani
 * @codereview @otrangan
 */
public class FacebookDataController extends AbstractController {

    @FXML
    public JFXTextField accountToFollow;

    private static final String accessToken = "EAAJ0YUQvIMQBAGBxCRFOk8PM6IOoSfmabqARoUCbjEt3lAziOMnrQjnhfvVibUjUVH3I" +
            "svyQS8vNV22YmseJJ4UTgExXvLzSi8ZAlsaTvyzsa0Urd2G1u6EpXroFAH8Jf5OhnXmGZB0OIfbyjPDhCmVWK8ROUZB1bgunyuIfOvZCe" +
            "k4ZCyEvxKJVoJ1OGr7xPGZA3zL6aSTgcAQHFP5rzOj4kwCepXdpfBSM4MWCMFzAZDZD";

    /**
     * @throws IOException
     * Follow an account on facebook
     */
    public void followAccount() {
        try{
            String followAccount = accountToFollow.getText();
            String url = "https://graph.facebook.com/Pages/search?q=" + followAccount + "&access_token=" + accessToken;

            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();


            if (responseCode == 400) {
                // Well obviously it doesn't work, but the code is here
                // We need to wait for the facebook approval
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject apiResponse = new JSONObject(response.toString());

        }
        catch (IOException e){
            showAlert("FeedBuzz App is still waiting for the approval of facebook \n Come back later maybe"
            ,"Information");
        }

    }
}
