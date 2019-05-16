package dataccess;
import composants.*;
import exceptions.*;

import java.sql.*;
import java.util.ArrayList;

public class OrderLineDBAccess {

    public static ArrayList<OrderLine> getAllOrderLines(ArrayList<Beer> beers, ArrayList<Order> orders) throws DataAccessException, CorruptedDataException {
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

                i = 0;
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
        catch(SQLException e) {
            throw new DataAccessException("Erreur lors de la récupération de données sur les lignes de commande dans la BD");
        }
        catch(OrderLineException e){
            throw new CorruptedDataException("Des données incohérentes concernant les lignes de commande se trouvent dans la base de donnée");
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

    public static void saveOrderLine(int orderID, String beerName, OrderLine orderLine) throws DataAccessException, DataBackupException {
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
        catch(SQLException e) {
            throw new DataBackupException("Erreur lors de la sauvegarde d'une ligne de commande dans la BD");
        }
    }

    public static void deleteOrderLine(int orderId, String beerName) throws DataAccessException, DataDeletionException {
        Connection connection = SingletonConnection.getInstance();
        String sql = "DELETE FROM OrderLine WHERE (beerName = ? AND orderNumber = ?);";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, beerName);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }

        catch(SQLException e) {
            throw new DataDeletionException("Erreur lors de la suppression d'une ligne de commande dans la BD");
        }
    }

    public static ArrayList<OrderLine> getOrderLinesWithOrder(Order order) throws DataAccessException, CorruptedDataException {
        Connection connection = SingletonConnection.getInstance();
        String sql = "SELECT * FROM OrderLine WHERE orderNumber = ?";
        ArrayList<OrderLine> selectedOrderLines = new ArrayList<>();
        Beer beer;
        int quantity;
        double price;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            ResultSet data = statement.executeQuery();

            while(data.next()) {
                beer = BeerDBAccess.getBeer(data.getString("beerName"));
                quantity = data.getInt("quantity");
                price = data.getDouble("price");
                selectedOrderLines.add(new OrderLine(beer, order, quantity, price));
            }
        }
        catch(SQLException e) {
            throw new DataAccessException("Erreur lors de la récupération de données concernant une ligne de commande dans la BD");
        }
        catch(OrderLineException e){
            throw new CorruptedDataException("Des données incohérentes concernant une ligne de commande se trouve dans BD");
        }
        return selectedOrderLines;
    }
}