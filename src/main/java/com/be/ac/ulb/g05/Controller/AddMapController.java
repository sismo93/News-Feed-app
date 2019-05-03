package com.be.ac.ulb.g05.Controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import netscape.javascript.JSObject;

import java.text.DecimalFormat;

import static com.be.ac.ulb.g05.Controller.AddMapController.Locations.*;

public class AddMapController extends AbstractController {

    @FXML
    private Label latitudeLabel;

    @FXML
    private Label longitudeLabel;

    @FXML
    private GoogleMapView googleMapView;

    enum Locations {

        Brussels(50.8, 4.3), Paris(48.8, 2.33331);

        double latitude;
        double longitude;

        Locations(Double la, Double lo) {
            latitude = la;
            longitude = lo;
        }
    }

    private GoogleMap map;
    private LatLong brusselsLatLong;
    private int zoom = 5;

    @Override
    public void setupView() {
        googleMapView.addMapInializedListener(() -> configureMap());
    }

    protected void configureMap() {
        MapOptions mapOptions = new MapOptions();

        Marker paris = new Marker(new MarkerOptions().position(new LatLong(Paris.latitude, Paris.longitude)).title("paris"));
        Marker brussels = new Marker(new MarkerOptions().position(new LatLong(Brussels.latitude, Brussels.longitude)).title("Brussels"));


        mapOptions.center(new LatLong(Paris.latitude, Paris.longitude))
                .mapType(MapTypeIdEnum.ROADMAP)
                .zoom(zoom);

        map = googleMapView.createMap(mapOptions, false);

        map.addUIEventHandler(UIEventType.click, (JSObject obj) -> {
            LatLong ll = new LatLong((JSObject) obj.getMember("latLng"));
            System.out.println("LatLong: lat: " + ll.getLatitude() + " lng: " + ll.getLongitude());
        });

        map.addMarker(paris);
        map.addMarker(brussels);
    }

}
