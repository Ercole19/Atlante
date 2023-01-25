package com.example.athena.graphical_controller.normal_interface;

import com.example.athena.entities.BooksSubject;
import com.example.athena.entities.SellerOrBuyerEnum;
import com.example.athena.entities.Student;
import com.example.athena.beans.BookBean;
import com.example.athena.exceptions.BookException;
import com.example.athena.exceptions.SizedAlert;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.graphical_controller.oracle_interface.ParentSubject;
import com.example.athena.use_case_controllers.BookPageUCC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class BookPageController extends ShiftImageController implements PostInitialize {


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
    private Text scamNumber;
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
    private ImageView lArrowImage;
    @FXML
    private ImageView rArrowImage;

    private BookBean book;
    private List<Image> bookImages;
    private String sellerName;
    private String sellerSurname;
    private int reportNumber;
    private String searchQuery ;

    private final SceneSwitcher switcher = SceneSwitcher.getInstance() ;
    private final BookPageUCC controller = new BookPageUCC() ;

    @Override
    public void postInitialize(ArrayList<Object> params) {

        this.book = (BookBean) params.get(1);

        if (params.get(0) == SellerOrBuyerEnum.SELLER) {
            try {
                this.sellerName = (BooksSubject.getInstance().getSellerName());
                this.sellerSurname = (BooksSubject.getInstance().getSellerSurname());
                this.reportNumber = controller.getReportNumber(Student.getInstance().getEmail());
            } catch (BookException | UserInfoException e) {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }


        } else {
            this.searchQuery = (String) params.get(2) ;
            String[] vendorFullName = new String[0];
            try {
                vendorFullName = controller.getUserName(book.getOwner());
            } catch (UserInfoException e) {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait();
            }
            this.sellerName = vendorFullName[0];
            this.sellerSurname = vendorFullName[1];
            try {
                this.reportNumber = controller.getReportNumber(book.getOwner());
            } catch (UserInfoException e) {
                SizedAlert alert = new SizedAlert(Alert.AlertType.ERROR, e.getMessage());
                alert.showAndWait() ;
            }
            buyButton.setVisible(true);
            buyButton.setDisable(false);
            if(Boolean.TRUE.equals(book.getNegotiable())) {
                buyButton.setText("Place bid");
                buyButton.setOnAction(this::onNegotiateButtonClick);
            }
            backBtn.setOnAction(this::onBackBtnClick);
        }

        this.bookImages = book.getImageList();

        super.bookImage = this.image;
        super.images = bookImages;
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
        scamNumber.setText(String.valueOf(reportNumber));
        if(!(this.bookImages.isEmpty())){
            image.setImage(this.bookImages.get(0));
        }
        super.shiftIndex(0);

    }
    
    public void onBackBtnClick(ActionEvent event)  {
        List<Object> params = new ArrayList<>();
        params.add(this.searchQuery);
        switcher.switcher("buy-view2.fxml", params);
    }

    public void onBackBtnClickSeller()
    {
        switcher.switcher("sell-view.fxml");
    }


    public void onBuyBookButtonClick(ActionEvent event){
        List<Object> params = new ArrayList<>();
        params.add(this.book);
        switcher.popup("purchaseConfirm.fxml", "Confirm purchase", params);
        List<Object> goBackParams = new ArrayList<>() ;
        if (System.getProperty("oracle").equals("false")) {
            goBackParams.add(this.searchQuery) ;
            switcher.switcher("buy-view2.fxml", goBackParams);
        }
        else {
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow() ;
            stage.close();
            ParentSubject.getInstance().setCurrentParent(new AnchorPane());
        }
    }

    public void onNegotiateButtonClick(ActionEvent event) {
        List<Object> params = new ArrayList<>();
        params.add(this.book);
        switcher.popup("PlaceBidPage.fxml", "Place bid", params);
    }

    @Override
    public void rightArrowClick() {
        super.onRightArrowClick();
    }

    @Override
    public void leftArrowClick() {
        super.onLeftArrowClick();
    }
}
