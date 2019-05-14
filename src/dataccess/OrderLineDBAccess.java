package dataccess;
import composants.*;

import java.sql.*;
import java.util.ArrayList;

public class OrderLineDBAccess {

    public static ArrayList<OrderLine> getAllOrderLines(ArrayList<Beer> beers, ArrayList<Order> orders) {
        Connection connection = SingletonConnection.getInstance();
        ArrayList<OrderLine> orderLines = new ArrayList<>();
        OrderLine orderLine;
        String beerName;
        Beer beer;
        int orderNumber;
        Order order;
        int quantity;
        double price;
        int i;
        int tailleArrayBeers;
        int tailleArrayOrders;
        String sql = "select * from OrderLine";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()) {
                beerName = data.getString("beerName");
                orderNumber = data.getInt("orderNumber");
                quantity = data.getInt("quantity");
                price = data.getDouble("price");

                i = 0;                      // trouver la biere correspondant au nom de bière récupéré
                beer = beers.get(i);
                tailleArrayBeers = beers.size();
                while(i < tailleArrayBeers && !beer.getName().equals(beerName)) {
                    i++;
                    beer = beers.get(i);
                }

                i = 0;
                order = orders.get(i);
                tailleArrayOrders = orders.size();
                while(i < tailleArrayOrders && order.getId() != orderNumber) {
                    i++;
                    order = orders.get(i);
                }
                orderLines.add(new OrderLine(beer, order, quantity, price));
            }
        }
        catch(Exception e) {

        }
        return orderLines;
    }
}
