package com.example.athena.engineering_classes;

import com.example.athena.beans.MailServerResponseBean;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SecureObjectInputStream extends ObjectInputStream {

    public SecureObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    protected SecureObjectInputStream() throws IOException, SecurityException {
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass osc) throws IOException, ClassNotFoundException {

        List<String> approvedClasses = new ArrayList<>();
        approvedClasses.add(MailServerResponseBean.class.getName());

        if (!approvedClasses.contains(osc.getName())) {
            throw new InvalidClassException("Unauthorized deserialization", osc.getName());
        }

        return super.resolveClass(osc);
    }
}
