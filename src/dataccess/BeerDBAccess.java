package dataccess;

import java.util.*;
import java.sql.*;

import composants.*;
import exceptions.*;

public class BeerDBAccess {

    public static ArrayList<Beer> getAllBeers() throws DataAccessException, CorruptedDataException{
        Connection connection = SingletonConnection.getInstance();
        ArrayList<Beer> beers = new ArrayList<>();
        Beer beer;
        String name;
        double stockPrice;
        int qtInStock;
        int lowTreshold;
        String sql = "select * from Beer";

        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                name = data.getString("idName");
                stockPrice = data.getDouble("stockPrice");
                qtInStock = data.getInt("qtInStock");
                lowTreshold = data.getInt("lowTreshold");
                beer = new Beer(name, stockPrice, qtInStock, lowTreshold);
                beers.add(beer);
            }
        }
        catch(SQLException e){
            throw new DataAccessException("Erreur lors de la récupération de données sur les bières dans la BD");
        }
        catch(BeerException e) {
            throw new CorruptedDataException("Des données incohérentes concernant les bières se trouvent dans la base de donnée");
        }
        return beers;
    }
}
