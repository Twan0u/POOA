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
        Client client = null;
        Locality locality = null;
        String sql ="select * from BusinessUnit;";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                idBusinessUnit = data.getInt("idBusinessUnit");
                clientNumber = data.getInt("clientNumber");
                idLocality = data.getInt("locality");
                streetName = data.getString("streetName");
                streetNumber = data.getString("streetNumber");

                for(Client c : clients) {
                    if(c.getId() == clientNumber)
                        client = c;
                }

                for(Locality l : localities) {
                    if(l.getIdLocality() == idLocality) {
                        locality = l;
                    }
                }
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

    public static ArrayList<BusinessUnit> getBusinessOf(int ownerId, Client client) throws DataAccessException, CorruptedDataException {
        Connection connection = SingletonConnection.getInstance();
        String sql = "SELECT * FROM BusinessUnit WHERE clientNumber = ?";
        ArrayList<BusinessUnit> selectedBusinesses = new ArrayList<>();
        BusinessUnit business;
        Locality locality;
        int idBusiness;
        int localityNumber;
        String streetName;
        String streetNumber;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, ownerId);
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
}