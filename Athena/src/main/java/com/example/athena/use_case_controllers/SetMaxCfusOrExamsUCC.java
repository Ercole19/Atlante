package com.example.athena.use_case_controllers;

import com.example.athena.beans.SetMaxCfusOrExamsBean;
import com.example.athena.entities.ExamsSubject;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.entities.Student;
import com.example.athena.exceptions.CareerStatusException;
import com.example.athena.exceptions.ExamException;
import com.example.athena.exceptions.StudentInfoException;
import com.example.athena.exceptions.UserInfoException;

public class SetMaxCfusOrExamsUCC {


    public void setInfos(SetMaxCfusOrExamsBean bean) throws  StudentInfoException, ExamException {
        LoggedStudent.getInstance().getCurrentStudent().setNewMax(bean.getNewMax(), bean.getType());
    }
}
