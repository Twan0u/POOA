package dataccess;

import java.util.*;
import java.sql.*;

import composants.*;
import exceptions.*;

public class BeerDBAccess {

    public static ArrayList<Beer> getAllBeers(){
        Connection connection = SingletonConnection.getInstance();
        ArrayList<Beer> beers = new ArrayList<>();
        Beer beer;

        String sql = "select * from Beer";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()){
                beer = new Beer(data.getString("idName"), data.getDouble("stockPrice"), data.getInt("qtInStock"), data.getInt("lowTreshold"));
                beers.add(beer);
            }
        }
        catch(Exception e){

        }
        return beers;
    }
}
