package dataccess;

import java.util.*;
import java.sql.*;

import composants.*;
import exceptions.*;

public class ClientDBAccess {

    public static ArrayList<Client> getAllClients() throws DataAccessException, CorruptedDataException {
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
        catch(SQLException e){
            throw new DataAccessException("Erreur lors de la récupération de données sur les clients dans la BD");
        }
        catch(ClientException e){
            throw new CorruptedDataException("Des données incohérentes concernant les clients se trouvent dans la base de donnée");
        }
        return clients;
    }

    public static Client getClient(int idClient) throws DataAccessException, CorruptedDataException{
        Connection connection = SingletonConnection.getInstance();
        String sql = "SELECT * FROM Client WHERE idNumber = ?";
        Client client = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idClient);
            ResultSet data = statement.executeQuery();

            while(data.next()) {
                String phoneNumber = data.getString("phoneNumber");
                String clientName = data.getString("clientName");
                double discount = data.getDouble("discount");
                client = new Client(idClient, clientName, phoneNumber, discount);
                String vatNumber = data.getString("vatNumber");
                if(!data.wasNull())
                    client.setVATNumber(vatNumber);
            }
        }
        catch(SQLException e){
            throw new DataAccessException("Erreur lors de la récupération des données d'un client dans la BD");
        }
        catch(ClientException e) {
            throw new CorruptedDataException("Des données incohérentes concernant un client se trouvent dans la base de donnée");
        }

        return client;
    }
}
