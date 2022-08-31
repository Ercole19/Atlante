package com.example.athena.boundaries;

import com.example.athena.entities.Student;
import com.example.athena.entities.User;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.EventBean;


import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SetReminderEmailBoundary extends SocketBoundary
{
    private SetReminderEmailBoundary()
    {

    }

    public static void sendToServer(EventBean eventInfo, boolean remove) throws SendEmailException
    {
        Timestamp moment;
        try {
             moment = eventInfo.getDateOfReminder() ;
        }
        catch (EventException e)
        {
            throw new SendEmailException("Unable to access reminder's date.");
        }

        String name = eventInfo.getName() ;
        LocalDate day = eventInfo.getDate() ;
        LocalTime start = eventInfo.getStart() ;
        LocalTime end = eventInfo.getEnd() ;
        String description = eventInfo.getDescription() ;

        String query = prepareQueryForServer(moment.toString(), Student.getInstance().getEmail(), name, day, start, end, description) ;

        if(remove)
        {
            query = "R" + query.substring(1) ;
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

    private static String prepareQueryForServer(String day, String email, String eventName, LocalDate eventDay, LocalTime eventStart, LocalTime eventEnd, String eventDescription) {

        return String.format("Nathena.services;%s;%s;Reminder of your event;" +
                "This email is a reminder for your event: \n" +
                "%s\n" +
                "on %s from %s to %s .\n" +
                "Details: %s",day, email,  eventName, eventDay, eventStart, eventEnd, eventDescription) ;
    }
}
