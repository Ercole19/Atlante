package com.example.athena.view.oracle_view;

import com.example.athena.beans.BookBean;
import com.example.athena.graphical_controller.oracle_interface.OracleUpdateBookGC;
import com.example.athena.view.LabelBuilder;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ModifyParametersView extends UpdatedShiftImageView<OracleUpdateBookGC> {

    private TextField name;
    private CheckBox negotiability;
    private TextField price;

    public ModifyParametersView (OracleUpdateBookGC controller) {
        super(controller) ;
        BookBean bean = controller.getSelectedBean();
        this.name = new TextField();
        this.negotiability = new CheckBox();
        this.price = new TextField();

        this.name.setText(bean.getBookTitle());
        this.negotiability.setSelected(bean.getNegotiable());
        this.price.setText(bean.getPrice());
        initializeGraphicalElements();

        VBox box = new VBox();
        HBox nameBox = new HBox();
        nameBox.getChildren().addAll(LabelBuilder.buildLabel("Insert book name: "), name);

        HBox negotiabilityBox = new HBox() ;
        negotiabilityBox.getChildren().addAll(LabelBuilder.buildLabel("Select negotiability: "), negotiability) ;

        HBox priceBox = new HBox() ;
        priceBox.getChildren().addAll(LabelBuilder.buildLabel("Insert new price : "), price) ;

        box.getChildren().addAll(nameBox, negotiabilityBox, priceBox) ;
        box.getChildren().add(super.getRoot()) ;

        HBox.setMargin(name, new Insets(10,0,0,0));
        HBox.setMargin(negotiability, new Insets(5,0,0,0));
        HBox.setMargin(price, new Insets(10,0,0,0));

        super.root = box ;
    }

    @Override
    protected void finalizeCollection() {
        this.updatedController.getUpdatedInformation(this) ;
    }

    @Override
    protected void initializeGraphicalElements() {
        this.updatedController.getUploadControls(this);
    }
    @Override
    public ImageView getLeftArrowImage() {
        return this.leftArrowImage ;
    }
    @Override
    public ImageView getRightArrowImage() {
        return this.rightArrowImage ;
    }
    @Override
    public ImageView getBookImageView() {
        return this.bookImage ;
    }

    public String getBookTitle() {
        return this.name.getText() ;
    }

    public boolean getNegotiability() {
        return this.negotiability.isSelected();
    }
    public String getPrice(){
        return this.price.getText();
    }

    @Override
    public Parent getRoot () {return this.root; }

}
