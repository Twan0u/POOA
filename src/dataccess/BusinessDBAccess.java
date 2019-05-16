package dataccess;

import java.util.*;
import java.sql.*;

import composants.*;
import exceptions.*;

public class BusinessDBAccess {

    public static ArrayList<BusinessUnit> getAllBusinesses (ArrayList<Client> clients, ArrayList<Locality> localities) throws DataAccessException, CorruptedDataException{
        Connection connection = SingletonConnection.getInstance();
        ArrayList<BusinessUnit> businesses = new ArrayList<>();
        BusinessUnit business;
        int idBusinessUnit;
        int clientNumber;
        int idLocality;
        String streetName;
        String streetNumber;
        Client client;
        Locality locality;
        String sql ="select * from BusinessUnit;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                idBusinessUnit = data.getInt("idBusinessUnit");
                clientNumber = data.getInt("clientNumber");
                idLocality = data.getInt("locality");
                locality = LocalityDBAccess.getLocality(idLocality);
                streetName = data.getString("streetName");
                streetNumber = data.getString("streetNumber");
                client = ClientDBAccess.getClient(clientNumber);
                BusinessDBAccess.getBusinessOf(client.getId());

                business = new BusinessUnit(idBusinessUnit, client, locality, streetName, streetNumber);  // le constructeur BusinnessUnit appelle la methode client.SetBusinessUnit
                businesses.add(business);
            }
        }
        catch(SQLException e){
            throw new DataAccessException("Erreur lors de la récupération de données sur les business unit dans la BD");
        }
        catch(BusinessUnitException e) {
            throw new CorruptedDataException("Des données incohérentes concernant les business unit se trouvent dans la base de donnée");
        }
        return businesses;
    }

    public static ArrayList<BusinessUnit> getBusinessOf(int idClient) throws DataAccessException, CorruptedDataException {
        Connection connection = SingletonConnection.getInstance();
        String sql = "SELECT * FROM BusinessUnit WHERE clientNumber = ?";
        ArrayList<BusinessUnit> selectedBusinesses = new ArrayList<>();
        Client client = ClientDBAccess.getClient(idClient);
        BusinessUnit business;
        Locality locality;
        int idBusiness;
        int localityNumber;
        String streetName;
        String streetNumber;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, client.getId());
            ResultSet data = statement.executeQuery();

            while(data.next()) {
                idBusiness = data.getInt("idBusinessUnit");
                localityNumber = data.getInt("locality");
                locality = LocalityDBAccess.getLocality(localityNumber);
                streetName = data.getString("streetName");
                streetNumber = data.getString("streetNumber");
                business = new BusinessUnit(idBusiness, client, locality, streetName, streetNumber);
                selectedBusinesses.add(business);
            }
        }
        catch(SQLException e){
            throw new DataAccessException("Erreur lors de la récupération de données concernant des business unit dans la BD");
        }
        catch(BusinessUnitException e) {
            throw new CorruptedDataException("Des données incohérentes concernant des business unit se trouvent dans BD");
        }

        return selectedBusinesses;
    }

    public static BusinessUnit getBusinessWithId(int idBusiness) throws DataAccessException, CorruptedDataException {
        Connection connection = SingletonConnection.getInstance();
        String sql = "SELECT * FROM BusinessUnit WHERE idBusinessUnit = ?";
        BusinessUnit business = null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, idBusiness);
            ResultSet data = statement.executeQuery();

            if(data.next()) {
                String streetName = data.getString("streetName");
                String streetNumber = data.getString("streetNumber");
                int localityNumber = data.getInt("locality");
                Locality locality = LocalityDBAccess.getLocality(localityNumber);
                int clientNumber = data.getInt("clientNumber");
                Client client = ClientDBAccess.getClient(clientNumber);
                business = new BusinessUnit(idBusiness, client, locality, streetName, streetNumber);
            }
        }
        catch(SQLException e){
            throw new DataAccessException("Erreur lors de la récupération de données concernant des business unit dans la BD");
        }
        catch(BusinessUnitException e) {
            throw new CorruptedDataException("Des données incohérentes concernant des business unit se trouvent dans BD");
        }

        return business;
    }
}