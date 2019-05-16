package dataccess;

import composants.Locality;
import exceptions.*;

import java.sql.*;

import java.util.ArrayList;

public class LocalityDBAccess {

    public static ArrayList<Locality> getAllLocalities() throws ProgramErrorException, DataAccessException {
        Connection connection = SingletonConnection.getInstance();
        ArrayList<Locality> localities = new ArrayList<>();
        Locality locality;
        int id;
        String name;
        String postalCode;
        String sql = "select * from Locality;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()) {
                id = data.getInt("idLocality");
                name = data.getString("localityName");
                postalCode = data.getString("postalCode");
                locality = new Locality(id, name, postalCode);
                localities.add(locality);
            }
        }

        catch(Exception e) {
            throw new ProgramErrorException("Erreur lors de la récupération des localités dans la BD");
        }
        return localities;
    }
}
