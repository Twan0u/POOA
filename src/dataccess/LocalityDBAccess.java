package dataccess;

import composants.Locality;
import exceptions.*;

import java.sql.*;

import java.util.ArrayList;

public class LocalityDBAccess {

    public static ArrayList<Locality> getAllLocalities() throws DataAccessException, CorruptedDataException {
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

        catch(SQLException e) {
            throw new DataAccessException("Erreur lors de la récupération de données sur les localités dans la BD");
        }
        catch(LocalityException e) {
            throw new CorruptedDataException("Des données incohérentes concernant les localités se trouvent dans la BD");
        }
        return localities;
    }

    public static Locality getLocality(int idLocality) throws DataAccessException, CorruptedDataException{
        Connection connection = SingletonConnection.getInstance();
        String sql = "SELECT * FROM Locality WHERE idLocality = ?";
        Locality locality = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idLocality);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                String localityName = data.getString("localityName");
                String postalCode = data.getString("postalCode");
                locality = new Locality(idLocality, localityName, postalCode);
            }
        }
        catch(SQLException e) {
            throw new DataAccessException("Erreur lors de la récupération de données concernant les localiés");
        }
        catch(LocalityException e) {
            throw new CorruptedDataException("Des données incohérentes concernant une localité se trouvent dans la BD");
        }
        return locality;
    }
}
