package dataccess;

import java.util.*;
import java.sql.*;

import exceptions.*;

public class SingletonConnection{

    private static Connection uniqueConnection;

    public static Connection getInstance() throws ProgramErrorException, DataAccessException {
        if(uniqueConnection == null) {
            try{
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectDB?serverTimezone=" + TimeZone.getDefault().getID(), "root", "AntNat");
            }
            catch(SQLException e){
                throw new DataAccessException("Impossible de se connecter à la base de donnée");
            }
        }
        return uniqueConnection;
    }

    public static void closeConnection() throws ProgramErrorException, DataAccessException {
        try {
            uniqueConnection.close();
        }
        catch(SQLException e) {
            throw new DataAccessException("Erreur à la fermeture de la connection à la BD");
        }
    }
}
