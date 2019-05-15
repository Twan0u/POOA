package dataccess;

import java.util.*;
import java.sql.*;

import exceptions.*;

public class SingletonConnection{

    private static Connection uniqueConnection;

    public static Connection getInstance() throws ProgramErrorException {
        if(uniqueConnection == null) {
            try{
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectDB?serverTimezone=" + TimeZone.getDefault().getID(), "root", "AntNat");
            }
            catch(Exception e){
                throw new ProgramErrorException("Erreur lors de la connection à la BD");
            }
        }
        return uniqueConnection;
    }

    public static void closeConnection() throws ProgramErrorException{
        try {
            uniqueConnection.close();
        }
        catch(SQLException e) {
            throw new ProgramErrorException("Erreur à la fermeture de la connection avec la BD");
        }
    }
}
