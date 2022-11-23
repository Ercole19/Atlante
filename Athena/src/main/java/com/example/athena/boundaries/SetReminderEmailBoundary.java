package com.example.athena.boundaries;

import com.example.athena.beans.normal.MailServerBean;
import com.example.athena.beans.normal.MailServerResponseBean;
import com.example.athena.entities.Student;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.beans.normal.EventBean;


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
        LocalDateTime momentForServer = moment.toLocalDateTime() ;

        MailServerBean query = prepareQueryForServer(momentForServer.toString(), Student.getInstance().getEmail(), name, day, start, end, description, remove) ;

        try
        {
            MailServerResponseBean retVal = sendMessageGetResponse(query, 4545) ;
            if(!retVal.getMessage().equals("OK"))
            {
                throw new SendEmailException(retVal.getMessage()) ;
            }
        }
        catch(IOException e)
        {
            throw new SendEmailException("Error in connection to server: " + e.getMessage()) ;
        }
    }

    private static MailServerBean prepareQueryForServer(String day, String email, String eventName, LocalDate eventDay, LocalTime eventStart, LocalTime eventEnd, String eventDescription, boolean remove) {

        MailServerBean bean = new MailServerBean() ;
        if (remove) bean.setClassName("R");
        else bean.setClassName("N");

        bean.setMailAccount("athena.services") ;
        bean.setSendMoment(day);
        bean.setRecipient(email);
        bean.setMailObject("Reminder of your event");
        bean.setContent(String.format("This email is a reminder for your event: \n" +
                "%s\n" +
                "on %s from %s to %s .\n" +
                "Details: %s", eventName, eventDay, eventStart, eventEnd, eventDescription));

        return bean ;
    }
}
