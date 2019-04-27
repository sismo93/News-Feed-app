package com.be.ac.ulb.g05.Controller;

import javafx.scene.Node;

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

    public void setRoot(Node root) {
        this.root = root;
    }

    public AbstractController getController() {
        return controller;
    }

    public void setController(AbstractController controller) {
        this.controller = controller;
    }
}
