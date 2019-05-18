package dataccess;

import composants.*;
import exceptions.*;

import java.util.*;
import java.sql.*;

import java.sql.Connection;

public class BusinessDBAccess {
    public static ArrayList<BusinessUnit> getBusinessWithClientID(int idClient) throws DataAccessException, CorruptedDataException {
        Connection connection = SingletonConnection.getInstance();
        ArrayList<BusinessUnit> businesses = new ArrayList<>();
        String sql = "SELECT * FROM BusinessUnit as bU"
                + " INNER JOIN Client c on c.idClient = bU.clientNumber"
                + " INNER JOIN Locality l on l.idLocality = bU.locality"
                + " WHERE idClient = ?"
                + " ORDER BY locality;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idClient);
            ResultSet data = statement.executeQuery();

            Locality locality = null;
            int idLocality;
            String localityName;
            String postalCode;

            BusinessUnit business;
            int idBusiness;
            int localityNumber = -1;
            String streetNumber;
            String streetName;

            Client client = null;

            while(data.next()) {
                if(client == null) {
                    String clientName = data.getString("clientName");
                    double discount = data.getDouble("discount");
                    String phoneNumber = data.getString("phoneNumber");
                    client = new Client(idClient, clientName, phoneNumber, discount);
                    String vatNumber = data.getString("vatNumber");
                    if(!data.wasNull())
                        client.setVATNumber(vatNumber);
                }
                if(localityNumber != data.getInt("idLocality")){
                    localityName = data.getString("localityName");
                    idLocality = data.getInt("idLocality");
                    postalCode = data.getString("postalCode");
                    locality = new Locality(idLocality, localityName, postalCode);
                }
                idBusiness = data.getInt("idBusinessUnit");
                streetNumber = data.getString("streetNumber");
                streetName = data.getString("streetName");
                business = new BusinessUnit(idBusiness, client, locality, streetName, streetNumber);
                businesses.add(business);
            }
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
            throw new DataAccessException("Erreur lors de la récupération de données concernant les business");
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
        return businesses;
    }
}