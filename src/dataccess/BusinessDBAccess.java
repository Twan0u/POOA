package dataccess;

import java.util.*;
import java.sql.*;

import composants.*;
import exceptions.*;

public class BusinessDBAccess {

    public static ArrayList<BusinessUnit> getAllBusinesses (ArrayList<Client> clients, ArrayList<Locality> localities){
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
                    if(l.getIdLocality() == idLocality)
                        locality = l;
                }

                business = new BusinessUnit(idBusinessUnit, client, locality, streetName, streetNumber);  // le constructeur BusinnessUnit appelle la methode client.SetBusinessUnit
                businesses.add(business);
            }
        }
        catch(Exception e){

        }
        return businesses;
    }

    public static ArrayList<BusinessUnit> getBusinessOf(int id, ArrayList<Client> clients) {
        ArrayList<BusinessUnit> businesses;
        for(Client client : clients) {
            if(client.getId() == id)
                return client.getBusiness();
        }
        return(null); // TODO : throw une erreur car client pas trouvé
    }
}

