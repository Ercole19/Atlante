package com.example.athena.boundaries;

import com.example.athena.beans.normal.ISBNBean;
import com.example.athena.beans.normal.ISBNCheckResultBean;
import com.example.athena.beans.normal.PurchaseResultBean;
import com.example.athena.exceptions.ISBNException;
import com.example.athena.exceptions.PurchaseException;
import java.io.IOException;


public class IsbnCheckBoundary extends SocketBoundary {


   public static ISBNCheckResultBean isbnCheck(ISBNBean inBean) throws ISBNException {
            try
            {
                String retVal = sendMessageGetResponse(inBean.getISBN(), 9876) ;
                ISBNCheckResultBean bean = new ISBNCheckResultBean();
                bean.setResult(retVal.equals("OK")) ;
                return bean ;
            }
            catch (IOException e)
            {
                throw new ISBNException("Connection to server failed") ;
            }
        }


}
