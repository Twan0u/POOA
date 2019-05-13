package dataccess;

import java.util.*;
import java.sql.*;

import business.Business;
import composants.*;
import exceptions.*;

public class BusinessDBAccess {

    public static void linkBusinessesToClients(ArrayList<Client> clients, ArrayList<Locality> localities){  // appeller la methode businessunit setclient sur tous les businessunit en mettant tous les clients
        Connection connection = SingletonConnection.getInstance();
        ArrayList<Business> businesses = new ArrayList<>();
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

                business = new BusinessUnit(idBusinessUnit, client, locality, streetName, streetNumber);
            }
        }
        catch(Exception e){

        }
    }

    public static ArrayList<BusinessUnit> getBusinessOf(int id, ArrayList<Client> clients) {

        return(null);
    }
}

