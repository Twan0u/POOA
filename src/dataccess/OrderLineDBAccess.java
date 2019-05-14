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

    public static OrderLine getOrderLine(int orderId, String beerName, ArrayList<OrderLine> orderLines) {
        for(OrderLine o : orderLines) {
            if(o.getBeer().getName().equals(beerName) && o.getOrder().getId() == orderId)
                return o;
        }
        return null;
    }

    public static void saveOrderLine(int orderID, String beerName, OrderLine orderLine) {
        Connection connection = SingletonConnection.getInstance();
        String sql = "INSERT INTO OrderLine (beerName, orderNumber, quantity, price) VALUES (?,?,?,?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, beerName);
            statement.setInt(2, orderID);
            statement.setInt(3, orderLine.getQuantity());
            statement.setDouble(4, orderLine.getPrice());
            statement.executeUpdate();
        }
        catch(Exception e) {

        }
    }

    public static void deleteOrderLine(int orderId, String beerName) {
        Connection connection = SingletonConnection.getInstance();
        String sql = "DELETE FROM OrderLine WHERE (beerName = ? AND orderNumber = ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, beerName);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }

        catch(Exception e) {

        }
    }
}