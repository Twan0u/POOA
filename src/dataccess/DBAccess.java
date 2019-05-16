package dataccess;

import java.util.*;

import composants.*;
import exceptions.*;

public class DBAccess implements InterfaceData {

    private ArrayList<Client> clients = null;
    private ArrayList<Beer> beers = null;
    private ArrayList<Locality> localities = null;
    private ArrayList<BusinessUnit> businesses = null;
    private ArrayList<Order> orders = null;
    private ArrayList<OrderLine> orderLines = null;


    private void loadClients() throws DataAccessException, CorruptedDataException {
        clients = ClientDBAccess.getAllClients();
    }
    private void loadBeers() throws DataAccessException, CorruptedDataException {
        beers = BeerDBAccess.getAllBeers();
    }
    private void loadLocalities() throws DataAccessException, CorruptedDataException {
        localities = LocalityDBAccess.getAllLocalities();
    }
    private void loadBusinesses() throws DataAccessException, CorruptedDataException {
        if(clients == null)
            loadClients();
        if(localities == null)
            loadLocalities();
        businesses = BusinessDBAccess.getAllBusinesses(clients, localities);
    }
    private void loadOrders() throws DataAccessException, CorruptedDataException {
        if(clients == null)
            loadClients();
        if(businesses == null)
            loadBusinesses();
        orders = OrderDBAccess.getAllOrders(clients, businesses);
    }
    private void loadOrderLines() throws DataAccessException, CorruptedDataException {
        if(beers == null)
            loadBeers();
        if(orders == null)
            loadOrders();
        orderLines = OrderLineDBAccess.getAllOrderLines(beers, orders);
    }




    public ArrayList<Client> getAllClients() throws DataAccessException, CorruptedDataException {
        if(clients == null)
            loadClients();
        return clients;
    }
    public ArrayList<Beer> getAllBeers() throws DataAccessException, CorruptedDataException {
        if(beers == null)
            loadBeers();
        return beers;
    }
    public ArrayList<Order> getAllOrders() throws DataAccessException, CorruptedDataException {
        if(orders == null)
            loadOrders();
        return orders;
    }




    public Client getClient(int id) throws DataAccessException, CorruptedDataException {
        return ClientDBAccess.getClient(id);
    }

    public ArrayList<BusinessUnit> getBusinessOf(int idClient) throws DataAccessException, CorruptedDataException {
        Client client = ClientDBAccess.getClient(idClient);
        return BusinessDBAccess.getBusinessOf(idClient, client);
    }

    public ArrayList<Order> getOrdersWithState(String state) throws DataAccessException, CorruptedDataException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrdersWithState(state, orders);
    }

    public ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) throws DataAccessException, CorruptedDataException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrdersWithDates(dateMin, dateMax, orders);
    }

    public ArrayList<Order> getOrdersWithClient(int clientID) throws DataAccessException, CorruptedDataException {
        if(orders == null)
            loadOrders();
        Client client = this.getClient(clientID);
        return OrderDBAccess.getOrdersWithClient(client, orders);
    }

    public ArrayList<Order> getOrdersToDeliver(int localityID) throws DataAccessException, CorruptedDataException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrdersToDeliver(localityID, orders);
    }

    public Order getOrder(int orderID) throws DataAccessException, CorruptedDataException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrder(orderID, orders);
    }

    public void setOrderState(String newState, int orderId) throws DataAccessException, DataModificationException {
        OrderDBAccess.setOrderState(newState, orderId);
    }

    public int saveOrder(Order order) throws DataAccessException, CorruptedDataException, DataBackupException {
        if(orders == null)
            loadOrders();
        int id = OrderDBAccess.saveOrder(order);
        order.setId(id);
        orders.add(order);
        OrderLine orderLine;
        int nbOrderLines = order.getOrderLinesSize();
        for(int i = 0; i < nbOrderLines; i++) {
            orderLine = order.getOrderLine(i);
            OrderLineDBAccess.saveOrderLine(id, orderLine.getBeer().getName(), orderLine);
        }
        return id;
    }

    public void deleteOrder(int orderId) throws DataAccessException, CorruptedDataException, DataDeletionException {
        if(orders == null)
            loadOrders();
        Order order = getOrder(orderId);
        OrderLine orderLine;
        int nbOrderLines = order.getOrderLinesSize();
        for(int i = 0; i < nbOrderLines; i++) {
            orderLine = order.getOrderLine(orderId);
            deleteOrderLine(orderId, orderLine.getBeer().getName());
            orderLines.remove(orderLine);
        }
        orders.remove(order);
        OrderDBAccess.deleteOrder(orderId);
    }

    private OrderLine getOrderLine(int orderId, String beerName) throws DataAccessException, CorruptedDataException {
        if(orderLines == null)
            loadOrderLines();
        return OrderLineDBAccess.getOrderLine(orderId, beerName, orderLines);
    }

    public void saveOrderLine(int orderId, String beerName) throws DataAccessException, CorruptedDataException, DataBackupException {
        if(orderLines == null)
            loadOrderLines();
        OrderLine orderLine = getOrderLine(orderId, beerName);
        orderLines.add(orderLine);
        OrderLineDBAccess.saveOrderLine(orderId, beerName, orderLine);
    }

    public void deleteOrderLine(int orderId, String beerName) throws DataAccessException, CorruptedDataException, DataDeletionException {
        if(orderLines == null)
            loadOrderLines();
        OrderLineDBAccess.deleteOrderLine(orderId, beerName);
        orderLines.remove(getOrderLine(orderId, beerName));
    }

    public void closeConnection() throws DataAccessException {
        SingletonConnection.closeConnection();
    }
}