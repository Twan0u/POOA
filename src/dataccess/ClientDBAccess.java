package dataccess;
import composants.*;
import exceptions.*;

import java.util.*;
import java.sql.*;

public class ClientDBAccess {

    public static ArrayList<Client> getClients(Integer clientID) throws DataAccessException, CorruptedDataException {
        Connection connection = SingletonConnection.getInstance();
        ArrayList<Client> clients = new ArrayList<>();

        String sql = "SELECT * FROM Client as c" +
                " LEFT JOIN BusinessUnit bU on bU.clientNumber = c.idClient" +
                " LEFT JOIN Locality l on l.idLocality = bU.locality";
        if(clientID != null)
            sql += " WHERE c.idClient = ?";

        sql += " ORDER BY c.idClient";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            if(clientID != null)
                statement.setInt(1, clientID);
            ResultSet data = statement.executeQuery();

            Client client = null;
            int idClient = -1;
            String name;
            String phoneNumber;
            double discount;
            String vatNumber;

            Locality locality = null;
            Integer idLocality = -1;
            String localityName;
            String postCode;

            Integer idBusiness;
            String streetName;
            String streetNumber;

            while(data.next()) {
                if(idClient != data.getInt("idClient")) {
                    idClient = data.getInt("idClient");
                    phoneNumber = data.getString("phoneNumber");
                    discount = data.getDouble("discount");
                    name = data.getString("clientName");
                    client = new Client(idClient, name, phoneNumber, discount);
                    vatNumber = data.getString("vatNumber");
                    if(!data.wasNull()) {
                        client.setVATNumber(vatNumber);
                    }
                    clients.add(client);
                }

                idLocality = data.getInt("idLocality");
                if(!data.wasNull()) {
                    postCode = data.getString("postalCode");
                    localityName = data.getString("localityName");
                    locality = new Locality(idLocality, localityName, postCode);
                }
                idBusiness = data.getInt("idBusinessUnit");
                if(!data.wasNull()) {
                    streetName = data.getString("streetName");
                    streetNumber = data.getString("streetNumber");
                    new BusinessUnit(idBusiness, client, locality, streetName, streetNumber);    // le business est automatiquement ajouté au client
                }
            }
        }
        catch(SQLException e){
            throw new DataAccessException("Erreur lors de la récupération de données concernant les clients");
        }
        catch (ClientException e) {
            throw new CorruptedDataException("Des données incohérentes concernant les clients sont présentes dans la BD");
        }
        catch (LocalityException e) {
            throw new CorruptedDataException("Des données incohérentes concernant les localités sont présentes dans la BD");
        }
        catch (BusinessUnitException e) {
            throw new CorruptedDataException("Des données incohérentes concernant les business sont présentes dans la BD");
        }
        return clients;
    }
}