package com.example.athena.use_case_controllers;

import com.example.athena.beans.StudentInfosBean;
import com.example.athena.entities.Student;
import com.example.athena.exceptions.StudentInfoException;

public class GetStudentInfosUCC {
    public StudentInfosBean getStudentInfos(StudentInfosBean bean) throws StudentInfoException {
        Student student = new Student();
        student.setEmail(bean.getStudent());
        bean.setFullName(student.getFullName());
        bean.setRepNum(student.getReportNumber());
        return bean;
    }
}
