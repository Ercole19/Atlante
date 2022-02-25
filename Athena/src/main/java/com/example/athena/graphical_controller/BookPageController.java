package com.example.athena.graphical_controller;

import com.example.athena.entities.User;
import com.example.athena.use_case_controllers.BookPageUCC;
import com.example.athena.use_case_controllers.BuyControllerUCC;
import com.example.servers.FakePaymentSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookPageController extends shiftImageController implements PostInitialize{


    @FXML
    private Button buyButton;
    @FXML
    private Text title;
    @FXML
    private Text isbn;
    @FXML
    private Text price;
    @FXML
    private Text email;
    @FXML
    private CheckBox negotiable;
    @FXML
    private Label nome;
    @FXML
    private Label cognome;
    @FXML
    private Button backBtn;
    @FXML
    private ImageView image;
    @FXML
    private Button LArrow;
    @FXML
    private Button RArrow;
    @FXML
    private ImageView LArrowImage;
    @FXML
    private ImageView RArrowImage;

    private BookEntityBean book;

    private List<Image> bookImages;

    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;

    @Override
    public void postInitialize(ArrayList<Object> params) {

        BookPageUCC controller = new BookPageUCC() ;
        this.book = controller.getBookInfos((String) params.get(0), (String) params.get(1));
        this.bookImages = book.getImageList();

        super.bookImage = this.image;
        super.images = bookImages;
        super.leftArrow = LArrow;
        super.rightArrow = RArrow ;
        super.leftArrowImage = LArrowImage ;
        super.rightArrowImage = RArrowImage ;

        populatePage(book);

        if(!((boolean) params.get(2))) {
            buyButton.setVisible(true);
            buyButton.setDisable(false);
            backBtn.setOnAction(this::onBackBtnClick);
        }
    }

    public void populatePage(BookEntityBean book){
        BookPageUCC controller = new BookPageUCC() ;
        String[] vendorFullName = controller.getUserName(book.getOwner());
        nome.setText(vendorFullName[0]);
        cognome.setText(vendorFullName[1]);

        title.setText(book.getBookTitle());
        isbn.setText(book.getIsbn());
        price.setText(book.getPrice());
        email.setText(book.getOwner());
        negotiable.setSelected(book.getNegotiable());
        if(this.bookImages.isEmpty()){
            image.setImage(null);
        }
        else {
            image.setImage(this.bookImages.get(0));
        }

        super.shiftIndex(0);

    }
    public void onBackBtnClick(ActionEvent event)  {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage, "buy-view.fxml");

    }

    public void onBackBtnClickSeller(ActionEvent event)
    {
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow() ;
        switcher.switcher(stage , "sell-view.fxml");
    }

    public void onBuyBookButtonClick(){
        BuyControllerUCC controller = new BuyControllerUCC();
        controller.purchase(this.book);
    }

}
