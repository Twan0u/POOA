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
        int idNumber;
        String clientName;
        String phoneNumber;
        double discount;
        String vatNumber;
        String sql = "select * from Client;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                idNumber = data.getInt("idNumber");
                clientName = data.getString("clientName");
                phoneNumber = data.getString("phoneNumber");
                discount = data.getDouble("discount");
                client = new Client(idNumber, clientName, phoneNumber, discount);
                vatNumber = data.getString("vatNumber");
                if(!data.wasNull()) client.setVATNumber(vatNumber);
                clients.add(client);
            }
        }
        catch(Exception e){

        }
        return clients;
    }

    public static Client getClient(int id, ArrayList<Client> clients) {//throws ClientException{
        for(Client client : clients) {
            if(client.getId() == id) {
                return client;
            }
        }
        //throw new ClientException("No client matching the given id was found");
        return(null);
    }
}
