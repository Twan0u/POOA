package dataccess;
import composants.*;
import exceptions.ProgramErrorException;

import java.sql.*;
import java.util.ArrayList;

public class OrderDBAccess {

    public static int saveOrder(Order order) throws ProgramErrorException {
        Connection connection = SingletonConnection.getInstance();

        String sql = "INSERT INTO ClientOrder (businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)"
                + " VALUES (?,?,?,?,?,?);";
        int hasPriority = order.getHasPriority() ? 1 : 0;
        BusinessUnit business = order.getBusinessUnitId();

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(2, order.getClient().getId());
            statement.setInt(3, hasPriority);
            statement.setString(4, order.getOrderDate());
            statement.setString(5, order.getState());
            if(business != null) {
                statement.setInt(1, business.getIdBusinessUnit());
            }
            else {
                statement.setNull(1, Types.INTEGER);
            }
            if(order.getTimeLimit() > 0) {
                statement.setInt(6,order.getTimeLimit());
            }
            else {
                statement.setNull(6, Types.INTEGER);
            }
            int id = statement.executeUpdate();
            return id;
        }
        catch (Exception e) {
            throw new ProgramErrorException("Erreur lors de la sauvegarde d'une commande dans la BD");
        }
    }

    public static ArrayList<Order> getAllOrders(ArrayList<Client> clients, ArrayList<BusinessUnit> businesses) throws ProgramErrorException{
        Connection connection = SingletonConnection.getInstance();
        ArrayList<Order> orders = new ArrayList<>();
        Order order;
        int idNumber;
        int businessUnitId;
        BusinessUnit business = null;
        int clientNumber;
        boolean hasPriority;
        String orderDate;
        String state;
        int timeLimit;
        int tailleArrayBusiness;
        int tailleArrayClients;
        Client client;
        int i;
        String sql = "select * from ClientOrder";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            while(data.next()) {
                idNumber = data.getInt("idNumber");
                hasPriority = data.getInt("hasPriority") == 1;
                orderDate = data.getString("orderDate");
                state = data.getString("state");

                businessUnitId = data.getInt("businessUnit");
                if(!data.wasNull()) {
                    i = 0;
                    business = businesses.get(i);
                    tailleArrayBusiness = businesses.size();
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
                        tailleArrayClients = clients.size();
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
            throw new ProgramErrorException("Erreur lors de la récupération des commandes dans la BD");
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

    public static Order getOrder(int orderId, ArrayList<Order> orders) {
        for(Order o : orders) {
            if(o.getId() == orderId)
                return o;
        }
        return null;
    }

    public static void deleteOrder(int orderId) throws ProgramErrorException {
        Connection connection = SingletonConnection.getInstance();
        String sql = "DELETE FROM ClientOrder WHERE idNumber = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }

        catch(Exception e) {
            throw new ProgramErrorException("Erreur lors de la suppression d'une commande dans la BD");
        }
    }

    public static void setOrderState(String newState, int orderId) throws ProgramErrorException {
        Connection connection = SingletonConnection.getInstance();
        String sql = "UPDATE ClientOrder SET state = ? WHERE idNumber = ?;";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newState);
            statement.setInt(2, orderId);
            statement.executeUpdate();
        }
        catch(Exception e) {
            throw new ProgramErrorException("Erreur lors de la modification de l'état d'une commande dans la BD");
        }
    }
}