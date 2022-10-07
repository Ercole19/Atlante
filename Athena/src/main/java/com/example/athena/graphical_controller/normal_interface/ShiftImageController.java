package com.example.athena.graphical_controller.normal_interface;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.List;

public abstract class ShiftImageController {

    protected ImageView bookImage;
    protected int index;
    protected List<Image> images;
    protected Button rightArrow ;
    protected Button leftArrow ;
    protected ImageView leftArrowImage ;
    protected ImageView rightArrowImage ;

    @FXML
    protected void onLeftArrowClick()
    {
        shiftIndex(--index) ;
        this.bookImage.setImage(this.images.get(this.index)) ;
    }

    @FXML
    protected void onRightArrowClick()
    {
        shiftIndex(++index) ;
        this.bookImage.setImage(this.images.get(index)) ;
    }

    private void disable(Node node)
    {
        node.setDisable(true) ;
        node.setVisible(false) ;
    }

    private void enable(Node node)
    {
        node.setVisible(true) ;
        node.setDisable(false) ;
    }

    protected void shiftIndex(int position)
    {
        if(position < 0) {position = 0 ;}
        else{
            if(position > this.images.size() -1) position = this.images.size() -1 ;
        }
        this.index = position ;
        checkIndex() ;
    }

    protected void checkIndex()
    {
        if(images.size() == 0 || this.index == images.size() -1)
        {
            disable(rightArrow) ;
            disable(rightArrowImage) ;
        }
        else
        {
            enable(rightArrow) ;
            enable(rightArrowImage) ;
        }

        if(this.index == 0 || this.index == -1)
        {
            disable(leftArrow) ;
            disable(leftArrowImage) ;
        }
        else
        {
            enable(leftArrow);
            enable(leftArrowImage);
        }
    }


}
