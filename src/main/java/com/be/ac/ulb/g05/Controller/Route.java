package com.be.ac.ulb.g05.Controller;

import javafx.scene.Node;

/**
 * Contain information about the navigation from view to view
 * @author @borsalinoK
 * @codereview @Tanvir.Hoque
 */
public class Route {


    /**
     * UI object displayed on screen
     */
    private Node root;

    /**
     * UI manager handle user interaction and communicate with model
     */
    private AbstractController controller;

    public Route(Node root, AbstractController controller) {
        this.root = root;
        this.controller = controller;
    }

    /**
     * Node getter
     * @return root node
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Abstract controller
     * @return abstract controller
     */
    public AbstractController getController() {
        return controller;
    }

    /**
     * Controller setter
     * @param controller abstract controller
     */
    public void setController(AbstractController controller) {
        this.controller = controller;
    }
}
