package dataccess;
import composants.*;
import exceptions.*;

import java.util.*;
import java.sql.*;

public class ClientDBAccess {

    public static ArrayList<Client> getAllClients() throws DataAccessException {
        Connection connection = SingletonConnection.getInstance();
        ArrayList<Client> clients = new ArrayList<>();

        String sql = "";
        return null;
    }
}
