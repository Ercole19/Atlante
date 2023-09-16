package com.example.athena.dao;

import com.example.athena.entities.ByCourseOrNameEnum;
import com.example.athena.entities.TutorSearchResult;
import com.example.athena.exceptions.FindException;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TutorSearchResultDAO extends AbstractDAO {

    public List<TutorSearchResult> findTutor(String query, ByCourseOrNameEnum searchEnum, boolean byBestReviews) throws FindException {
        String prepStatement;

        if (searchEnum.toString().equals("BY_COURSE")) {prepStatement = "select utenti.nome , utenti.surname , corsi.nomecorso , tutordescription.Average , utenti.email from athena.tutordescription join athena.corsi on tutordescription.emailuser = corsi.emailtutor join athena.utenti on tutordescription.emailuser = utenti.email where  nomecorso like concat('%' , ? , '%') ";}
        else { prepStatement = "SELECT  utenti.nome ,  utenti.surname , corsi.nomecorso , tutordescription.Average ,  utenti.email FROM athena.utenti join athena.tutordescription on utenti.email = tutordescription.emailuser join athena.corsi on utenti.email = corsi.emailtutor WHERE CONCAT( nome,  ' ', surname ) LIKE  concat ('%' , ? , '%')";}

        if (byBestReviews) {prepStatement = prepStatement + "order by tutordescription.average DESC";}

        List<TutorSearchResult> results = new ArrayList<>() ;
        try (PreparedStatement statement = this.getConnection().prepareStatement(prepStatement)) {

            statement.setString(1, query);

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                results.add(new TutorSearchResult(
                        set.getString(5), set.getString(1),
                        set.getString(2), set.getString(3), set.getFloat(4))) ;
            }

        } catch (SQLException | IOException exc) {
            throw new FindException(exc.getMessage());
        }

        return results ;
    }
}
