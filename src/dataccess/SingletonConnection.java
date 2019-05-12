package dataccess;

import java.util.*;
import java.sql.*;

import composants.*;
import exceptions.*;

public class SingletonConnection{

    private static Connection uniqueConnection;

    public static Connection getInstance() {
        if(uniqueConnection == null) {
            try{
                uniqueConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectDB?serverTimezone=" + TimeZone.getDefault().getID(), "root", "AntNat");
            }
            catch(Exception e){

            }
        }
        return uniqueConnection;
    }
}
