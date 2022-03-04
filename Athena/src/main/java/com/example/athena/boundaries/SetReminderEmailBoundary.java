package com.example.athena.boundaries;

import com.example.athena.entities.User;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.EventBean;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SetReminderEmailBoundary extends SocketBoundary
{
    private SetReminderEmailBoundary()
    {

    }

    public static void sendToServer(EventBean eventInfo, boolean update) throws SendEmailException
    {
        LocalDateTime moment = eventInfo.getDateOfReminder() ;
        String name = eventInfo.getName() ;
        LocalDate day = eventInfo.getDate() ;
        LocalTime start = eventInfo.getStart() ;
        LocalTime end = eventInfo.getEnd() ;
        String description = eventInfo.getDescription() ;

        String query = String.format("N%s;%s;%s;%s;%s;%s;%s;", moment.toString(), User.getUser().getEmail(), name, day, start, end, description) ;

        if(update)
        {
            query = "U" + query.substring(1) ;
        }

        try
        {
            String retVal = sendMessageGetResponse(query, 4545) ;
            if(!retVal.equals("OK"))
            {
                throw new SendEmailException(retVal) ;
            }
        }
        catch(IOException e)
        {
            throw new SendEmailException("Error in connection to server: " + e.getMessage()) ;
        }
    }
}
