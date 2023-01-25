package com.example.athena.graphical_controller.oracle_interface;

import com.example.athena.beans.ExamAverageInformationBean;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.UserInfoException;
import com.example.athena.use_case_controllers.AverageUCC;
import com.example.athena.view.oracle_view.LabelView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DecimalFormat;

public class OracleAverageGC {

    private final DecimalFormat format = new DecimalFormat("+#.00;-#.00") ;

    public String getAverageInfos() {
        AverageUCC controller = new AverageUCC();
        LabelView view = new LabelView();
        String allAverages = "";
        ObservableList<ExamAverageInformationBean> examsArithmeticAverageInfos = FXCollections.observableArrayList();
        ObservableList<ExamAverageInformationBean> examsWeightedAverageInfos = FXCollections.observableArrayList();
        try {
            examsArithmeticAverageInfos = controller.getExamsArithmeticAverageInformation();
            examsWeightedAverageInfos = controller.getExamsWeightedAverageInformation();
        } catch (ExamException | UserInfoException e) {
            ParentSubject.getInstance().setCurrentParent(view.prepareParent("Error in retrieving infos, details follow: " + e.getMessage()));
        }
        double prevAverage = 0 ;
        allAverages = allAverages.concat("Arithmetic average:\n") ;
        for (ExamAverageInformationBean bean : examsArithmeticAverageInfos) {
            allAverages = allAverages.concat(bean.getDate() +  "  " + this.format.format(bean.getAverage()) + "  " + this.format.format(bean.getAverage() - prevAverage) + "\n");
            prevAverage = bean.getAverage() ;
        }
        prevAverage = 0 ;
        allAverages = allAverages.concat("Weighted average: \n") ;
        for (ExamAverageInformationBean bean : examsWeightedAverageInfos) {
            allAverages = allAverages.concat(bean.getDate() + "  " + this.format.format(bean.getAverage())+ "  " + this.format.format(bean.getAverage()-prevAverage) + "\n");
            prevAverage = bean.getAverage() ;
        }
        return allAverages;

    }

}
