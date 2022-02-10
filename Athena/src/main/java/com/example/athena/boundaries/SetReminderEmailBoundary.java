package com.example.athena.boundaries;

import com.example.athena.entities.User;
import com.example.athena.exceptions.SendEmailException;
import com.example.athena.graphical_controller.EventBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SetReminderEmailBoundary
{
    private SetReminderEmailBoundary()
    {

    }

    private static final byte[] buff = new byte[128] ;

    public static void sendToServer(EventBean eventInfo) throws SendEmailException
    {
        LocalDateTime moment = eventInfo.getDateOfReminder() ;
        String name = eventInfo.getName() ;
        LocalDate day = eventInfo.getDate() ;
        LocalTime start = eventInfo.getStart() ;
        LocalTime end = eventInfo.getEnd() ;
        String description = eventInfo.getDescription() ;

        String query = String.format("N%s;%s;%s;%s;%s;%s;%s;", moment.toString(), User.getUser().getEmail(), name, day, start, end, description) ;

        try
        {
            bZero() ;
            writeQuery(query) ;
            Socket socket = new Socket("localhost", 4545);
            OutputStream out = socket.getOutputStream();
            out.write(buff) ;

            bZero() ;
            InputStream in = socket.getInputStream();
            int readChars = in.read(buff) ;
            if(readChars != 2) throw new SendEmailException("Unknown response from server") ;

            if(buff[0] == 'F') throw new SendEmailException("Server error...") ;

            in.close() ;
            out.close() ;
            socket.close() ;
        }
        catch(IOException e)
        {
            throw new SendEmailException("Error in connection to server: " + e.getMessage()) ;
        }
    }

    private static void bZero()
    {
        for(int i = 0; i < 128; i++)
        {
            buff[i] = 0 ;
        }
    }

    private static void writeQuery(String query) throws SendEmailException
    {
        if(query.length() > 128) throw new SendEmailException("Error, the message is too long") ;

        int i = 0;
        for(byte b : query.getBytes(StandardCharsets.UTF_8))
        {
            buff[i] = b ;
            i++ ;
        }
    }
}
