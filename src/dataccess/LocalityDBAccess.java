package dataccess;

import composants.Locality;

import java.util.*;
import java.sql.*;

import java.util.ArrayList;

public class LocalityDBAccess {

    public static ArrayList<Locality> getAllLocalities(){
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

        }
        return localities;
    }
}
