package com.be.ac.ulb.g05.Controller;

import javafx.scene.Node;

/**
 * Contain information about the rootage for the navigation from view to view
 */
public class Route {

    private Node root;
    private AbstractController controller;

    public Route(Node root, AbstractController controller) {
        this.root = root;
        this.controller = controller;
    }

    public Node getRoot() {
        return root;
    }

    public AbstractController getController() {
        return controller;
    }

    public void setController(AbstractController controller) {
        this.controller = controller;
    }
}
