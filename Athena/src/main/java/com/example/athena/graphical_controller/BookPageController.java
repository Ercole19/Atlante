package com.example.athena.graphical_controller;

import com.example.athena.entities.BooksSubject;
import com.example.athena.entities.SellerOrBuyerEnum;
import com.example.athena.use_case_controllers.BookPageUCC;
import com.example.athena.use_case_controllers.BuyControllerUCC;
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

import java.util.ArrayList;
import java.util.List;

public class BookPageController extends ShiftImageController implements PostInitialize{


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
    private Button lArrow;
    @FXML
    private Button rArrow;
    @FXML
    private ImageView lArrowImage;
    @FXML
    private ImageView rArrowImage;

    private BookBean book;
    private List<Image> bookImages;
    private String sellerName;
    private String sellerSurname;

    private final SceneSwitcher switcher = new SceneSwitcher();
    private Stage stage;

    @Override
    public void postInitialize(ArrayList<Object> params) {

        this.book = (BookBean) params.get(1);

        switch ((SellerOrBuyerEnum)params.get(0))
        {
            case SELLER:
                this.sellerName = (BooksSubject.getInstance().getSellerName());
                this.sellerSurname = (BooksSubject.getInstance().getSellerSurname());
                break;
            case BUYER:
                BookPageUCC controller = new BookPageUCC() ;
                String[] vendorFullName = controller.getUserName(book.getOwner());
                this.sellerName = vendorFullName[0];
                this.sellerSurname = vendorFullName[1];
                buyButton.setVisible(true);
                buyButton.setDisable(false);
                backBtn.setOnAction(this::onBackBtnClick);
                break;
        }

        this.bookImages = book.getImageList();

        super.bookImage = this.image;
        super.images = bookImages;
        super.leftArrow = lArrow;
        super.rightArrow = rArrow;
        super.leftArrowImage = lArrowImage;
        super.rightArrowImage = rArrowImage;

        populatePage(book);

    }

    public void populatePage(BookBean book){


        nome.setText(sellerName);
        cognome.setText(sellerSurname);

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
