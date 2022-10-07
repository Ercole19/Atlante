package com.example.athena.graphical_controller.normal_interface;

import javafx.scene.image.Image;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.util.List;

public abstract class UpdatedShiftImageController extends ShiftImageController {

    protected List<File> files;

    public void deleteImageOnScreen (){
        this.files.remove(super.index);
        super.images.remove(super.index);
        super.shiftIndex(--super.index);
        if ((super.index == 0) && (super.images.size()>0) ){
            super.bookImage.setImage(super.images.get(super.index)) ;
        }
        else if(super.index != 0 && super.images.size()>0 && super.index<super.images.size()){
            super.bookImage.setImage(super.images.get(super.index));
        }
        else {
            Image icon = new Image(new File("src/main/resources/assets/upload2.jpg").toURI().toString());
            super.bookImage.setImage(icon);
            checkIndex();
        }
    }


    public void onUploadBtnClick() {

        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            super.images.add(new Image(String.valueOf(fc.getSelectedFile().toURI()))) ;
            this.files.add(fc.getSelectedFile());
            this.bookImage.setImage(super.images.get(images.size() - 1));
            super.shiftIndex(images.size() - 1);
        }

    }
}
