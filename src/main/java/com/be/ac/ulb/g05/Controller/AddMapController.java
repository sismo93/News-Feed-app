package com.be.ac.ulb.g05.Controller;


import com.be.ac.ulb.g05.Model.Address;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import net.java.html.BrwsrCtx;
import net.java.html.boot.fx.FXBrowsers;
import net.java.html.leaflet.*;

import java.net.URL;

public class AddMapController extends AbstractController {
    public BorderPane borderPane;
    private WebView webView;
    private Map map;

    @Override
    public void setupView() {
        // we define a regular JavaFX WebView that DukeScript can use for rendering

        webView = new WebView();

        WebEngine webEngine = webView.getEngine();
        URL url = this.getClass().getResource("/Pages/index.html");


        FXBrowsers.load(webView, url, () -> {

            map = new Map("map");

            // from here we just use the Leaflet API to show some stuff on the map
            map.setView(new LatLng(51.505, -0.09), 13);
            map.addLayer(new TileLayer("http://{s}.tile.thunderforest.com/cycle/{z}/{x}/{y}.png",
                    new TileLayerOptions()
                            .setAttribution(
                                    "Map data &copy; <a href='http://www.thunderforest.com/opencyclemap/'>OpenCycleMap</a> contributors, "
                                            + "<a href='http://creativecommons.org/licenses/by-sa/2.0/'>CC-BY-SA</a>, "
                                            + "Imagery © <a href='http://www.thunderforest.com/'>Thunderforest</a>")
                            .setMaxZoom(18)
                            .setId("eppleton.ia9c2p12")
            ));

            // sample code showing how to use the Java API

            map.addLayer(new Circle(new LatLng(51.508, -0.11), 500,
                    new PathOptions().setColor("red").setFillColor("#f03").setOpacity(0.5)
            ).bindPopup("I am a Circle"));

            map.addLayer(new Polygon(new LatLng[]{
                    new LatLng(51.509, -0.08),
                    new LatLng(51.503, -0.06),
                    new LatLng(51.51, -0.047)
            }).bindPopup("I am a Polygon"));


        });


        borderPane.setCenter(webView);

        // a regular JavaFX ListView
        ListView<Address> listView = new ListView<>();
        listView.getItems().addAll(new Address("Toni", 48.1322840, 11.5361690),
                new Address("Jarda", 50.0284060, 14.4934400),
                new Address("JUG Münster", 51.94906770000001, 7.613701100000071));
        // we listen for the selected item and update the map accordingly
        // as a demo of how to interact between JavaFX and DukeScript
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Address>() {
            @Override
            public void changed(ObservableValue<? extends Address> ov, Address old_val, final Address new_val) {
                FXBrowsers.runInBrowser(webView, new Runnable() {
                    @Override
                    public void run() {
                        LatLng pos = new LatLng(new_val.getLat(), new_val.getLng());
                        map.setView(pos, 20);
                        map.openPopup("Here is " + new_val, pos);
                    }
                });
            }
        });

        borderPane.setLeft(listView);

    }

    public Map getMap() {
        return map;
    }

    public WebView getWebView() {
        return webView;
    }
}
