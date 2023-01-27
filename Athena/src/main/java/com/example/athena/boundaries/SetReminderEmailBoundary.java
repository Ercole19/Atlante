package com.example.athena.boundaries;

import com.example.athena.beans.MailServerBean;
import com.example.athena.beans.MailServerResponseBean;
import com.example.athena.beans.ReminderBean;
import com.example.athena.entities.LoggedStudent;
import com.example.athena.exceptions.EventException;
import com.example.athena.exceptions.SendEmailException;

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

    public static void sendToServer(ReminderBean event) throws SendEmailException
    {
        MailServerBean query = prepareQueryForServer(event, LoggedStudent.getInstance().getEmail().getMail()) ;

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

    private static MailServerBean prepareQueryForServer(ReminderBean eventInfo, String recipient) throws SendEmailException {

        LocalDateTime moment;
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
        LocalDateTime momentForServer = moment ;
        MailServerBean bean = new MailServerBean() ;
        if (eventInfo.isRemove()) bean.setClassName("R");
        else bean.setClassName("N");

        bean.setMailAccount("athena.services") ;
        bean.setSendMoment(momentForServer.toString());
        bean.setRecipient(recipient);
        bean.setMailObject("Reminder of your event");
        bean.setContent(String.format("This email is a reminder for your event: \n" +
                "%s\n" +
                "on %s from %s to %s .\n" +
                "Details: %s", name, day, start, end, description));

        return bean ;
    }
}
