package com.example.athena.view.oracle_view;

import com.example.athena.graphical_controller.oracle_interface.OracleBookPageGC;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BookPageView extends ShiftImageView {
    private Label sellerName;
    private Label sellerSurname;
    private Label sellerEmail;
    private Label price;
    private Label negotiability;
    private Label title;
    private Label totalRepNumber;
    private Label isbn;
    private Button button;
    private OracleBookPageGC controller;
    private Parent root;

    public BookPageView(OracleBookPageGC controller) {
        super(controller);
        this.controller = controller;
        sellerName = new Label();
        sellerSurname = new Label();
        sellerEmail = new Label();
        price = new Label();
        negotiability = new Label();
        title = new Label();
        totalRepNumber = new Label();
        isbn = new Label();
        button = new Button();
        initializeGraphicalElements();

        HBox box = new HBox();
        box.getChildren().addAll(sellerName, sellerSurname, sellerEmail);
        box.setSpacing(50);
        HBox box2 = new HBox();
        box2.getChildren().addAll(title, isbn, price, negotiability);
        box2.setSpacing(50);
        VBox finalBox = new VBox();
        finalBox.getChildren().addAll(box, box2, super.getRoot(), totalRepNumber, button);
        finalBox.setSpacing(50);
        this.root = finalBox;

        setView();
    }

    private void setView()  {
        this.controller.populateView(this);
    }

    public Label getSellerName() {
        return sellerName;
    }

    public Button getButton() {
        return button;
    }

    public Label getNegotiability() {
        return negotiability;
    }

    public Label getPrice() {
        return price;
    }

    public Label getSellerEmail() {
        return sellerEmail;
    }

    public Label getSellerSurname() {
        return sellerSurname;
    }

    public Label getTitle() {
        return title;
    }

    public Label getTotalRepNumber() {
        return totalRepNumber;
    }
    public Parent getRoot() {
        return this.root;
    }

    @Override
    protected void initializeGraphicalElements() {
        this.controller.intiGraphicElem(this);
    }
}
