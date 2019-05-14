package dataccess;
import composants.*;

import java.sql.*;
import java.util.ArrayList;

public class OrderDBAccess {
    public static void saveOrder(Order order) {
        Connection connection = SingletonConnection.getInstance();

        String sql = "INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)"
                + " VALUES (?,?,?,?,?,?,?);";
        int hasPriority = order.getHasPriority() ? 1 : 0;
        BusinessUnit business = order.getBusinessUnitId();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            statement.setInt(3, order.getClient().getId());
            statement.setInt(4, hasPriority);
            statement.setString(5, order.getOrderDate());
            statement.setString(6, order.getState());
            if(business != null) {
                statement.setInt(2, order.getBusinessUnitId().getIdBusinessUnit());
            }
            else {
                statement.setNull(2, Types.INTEGER);
            }
            if(order.getTimeLimit() > 0) {
                statement.setInt(7,order.getTimeLimit());
            }
            else {
                statement.setNull(7, Types.INTEGER);
            }

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public static ArrayList<Order> getAllOrders(ArrayList<Client> clients, ArrayList<BusinessUnit> businesses){
        Connection connection = SingletonConnection.getInstance();
        ArrayList<Order> orders = new ArrayList<>();
        Order order;
        int idNumber;
        int businessUnitId;
        int clientNumber;
        boolean hasPriority;
        String orderDate;
        String state;
        int timeLimit;
        Client client;
        BusinessUnit business = null;  // contiendra la référence vers le bon business dans l'arraylist
        String sql = "select * from ClientOrder";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()) {
                idNumber = data.getInt("idNumber");
                hasPriority = data.getInt("hasPriority") == 1;
                orderDate = data.getString("orderDate");
                state = data.getString("state");
                int i;

                businessUnitId = data.getInt("businessUnit");
                if(!data.wasNull()) {
                    i = 0;
                    business = businesses.get(i);
                    int tailleArrayBusiness = businesses.size();
                    while(i < tailleArrayBusiness && business.getIdBusinessUnit() != businessUnitId) {
                        i++;
                        business = businesses.get(i);
                    }
                    client = business.getClient();
                }
                else {
                        clientNumber = data.getInt("clientNumber");
                        i = 0;
                        client = clients.get(i);
                        int tailleArrayClients = clients.size();
                        while(i < tailleArrayClients && client.getId() != clientNumber) {
                            i++;
                            client = clients.get(i);
                    }
                }
                order = new Order(idNumber, business, client, hasPriority, orderDate, state);

                timeLimit = data.getInt("timeLimit");
                if(!data.wasNull()){
                    order.setTimeLimit(timeLimit);
                }
                orders.add(order);
            }
        }

        catch(Exception e) {

        }
        return orders;
    }

    public static ArrayList<Order> getOrdersWithState(String state, ArrayList<Order> orders) {
        ArrayList<Order> selectedOrders = new ArrayList<>();
        for(Order o : orders) {
            if(o.getState().equals(state))
                selectedOrders.add(o);
        }
        return selectedOrders;
    }

    public static ArrayList<Order> getOrdersWithDates(String dateMinimum, String dateMaximum, ArrayList<Order> orders) {
        ArrayList<Order> selectedOrders = new ArrayList<>();

        Date dateMin = Date.valueOf(dateMinimum);
        Date dateMax = Date.valueOf(dateMaximum);

        for (Order o : orders) {
            Date oDate = Date.valueOf(o.getOrderDate());
            if (oDate.after(dateMin) && oDate.before(dateMax))
                selectedOrders.add(o);
        }
        return selectedOrders;
    }

    public static ArrayList<Order> getOrdersWithClient(Client client, ArrayList<Order> orders) {
        ArrayList<Order> selectedOrders = new ArrayList<>();

        for(Order o : orders) {
            if(o.getClient() == client)
                selectedOrders.add(o);
        }
        return selectedOrders;
    }

    public static ArrayList<Order> getOrdersToDeliver(int localityID, ArrayList<Order> orders) {
        ArrayList<Order> selectedOrders = new ArrayList<>();

        for(Order o : orders) {
            if(o.getBusinessUnitId() != null) {
                if(o.getBusinessUnitId().getLocality().getIdLocality() == localityID && o.getState().equals("prepared")) {
                        selectedOrders.add(o);
                }
            }
        }
        return selectedOrders;
    }
}