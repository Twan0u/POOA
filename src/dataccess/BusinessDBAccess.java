package dataccess;

import java.util.*;
import java.sql.*;

import composants.*;
import exceptions.*;

public class BusinessDBAccess {

    public static BusinessUnit getBusinessOf(int id){
        Connection connection = SingletonConnection.getInstance();
        BusinessUnit business;

        String sql = "select idBusinessUnit, clientNumber, locality, streetName, streetNumber, ";
        return(null);
    }
}

