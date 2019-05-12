package dataccess;

import java.util.*;
import java.sql.*;

import composants.*;
import exceptions.*;

public class ClientDBAccess {

    public static ArrayList<Client> getAllClients(){
        Connection connection = SingletonConnection.getInstance();
        ArrayList<Client> clients = new ArrayList<>();
        Client client;
        String vatNumber;

        String sql = "select * from Client;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                client = new Client(data.getInt("idNumber"), data.getString("clientName"), data.getString("phoneNumber"), data.getDouble("discount"));
                vatNumber = data.getString("vatNumber");
                if(!data.wasNull()) client.setVATNumber(vatNumber);
                clients.add(client);
            }
        }
        catch(Exception e){

        }
        return clients;
    }
}
