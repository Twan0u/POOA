package dataccess;

import java.util.*;

import composants.*;
import exceptions.*;

public class DBAccess implements InterfaceData {
    // arraylist pour retenir ce qui a déjà été chargé
    private ArrayList<Client> clients = null;
    private ArrayList<Beer> beers = null;
    private ArrayList<Locality> localities = null;
    private ArrayList<BusinessUnit> businesses = null;
    private ArrayList<Order> orders = null;
    private ArrayList<OrderLine> orderLines = null;

    // appels de chargement des données
    private void loadClients() throws ProgramErrorException, DataAccessException{
        clients = ClientDBAccess.getAllClients();
    }
    private void loadBeers() throws ProgramErrorException, DataAccessException{
        beers = BeerDBAccess.getAllBeers();
    }
    private void loadLocalities() throws ProgramErrorException, DataAccessException{
        localities = LocalityDBAccess.getAllLocalities();
    }
    private void loadBusinesses() throws ProgramErrorException, DataAccessException{
        if(clients == null)
            loadClients();
        if(localities == null)
            loadLocalities();
        businesses = BusinessDBAccess.getAllBusinesses(clients, localities);
    }
    private void loadOrders() throws ProgramErrorException, DataAccessException{
        if(clients == null)
            loadClients();
        if(businesses == null)
            loadBusinesses();
        orders = OrderDBAccess.getAllOrders(clients, businesses);
    }

    private void loadOrderLines() throws ProgramErrorException, DataAccessException{
        if(beers == null)
            loadBeers();
        if(orders == null)
            loadOrders();
        orderLines = OrderLineDBAccess.getAllOrderLines(beers, orders);
    }

    public ArrayList<Client> getAllClients() throws ProgramErrorException, DataAccessException{
        if(clients == null)
            loadClients();
        return clients;
    }

    public Client getClient(int id) throws ProgramErrorException, DataAccessException{
        if(clients == null)
            loadClients();
        for(Client client : clients) {
            if(client.getId() == id) {
                return client;
            }
        }
        return null;                                        // todo throw erreur client pas trouvé
    }

    public ArrayList<Beer> getAllBeers() throws ProgramErrorException, DataAccessException{
        if(beers == null)
            loadBeers();
        return beers;
    }

    public ArrayList<BusinessUnit> getBusinessOf(int id) throws ProgramErrorException, DataAccessException{
        if(businesses == null)
            loadBusinesses();
        return BusinessDBAccess.getBusinessOf(id, businesses);
    }

    public ArrayList<Order> getOrdersWithState(String state) throws ProgramErrorException, DataAccessException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrdersWithState(state, orders);
    }

    public ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) throws ProgramErrorException, DataAccessException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrdersWithDates(dateMin, dateMax, orders);
    }

    public ArrayList<Order> getOrdersWithClient(int clientID) throws ProgramErrorException, DataAccessException {
        if(orders == null)
            loadOrders();
        Client client = this.getClient(clientID);
        return OrderDBAccess.getOrdersWithClient(client, orders);
    }

    public ArrayList<Order> getOrdersToDeliver(int localityID) throws ProgramErrorException, DataAccessException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrdersToDeliver(localityID, orders);
    }

    public ArrayList<Order> getAllOrders() throws ProgramErrorException, DataAccessException {
        if(orders == null)
            loadOrders();
        return orders;
    }

    public Order getOrder(int orderID) throws ProgramErrorException, DataAccessException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrder(orderID, orders);
    }

    public void setOrderState(String newState, int orderId) throws ProgramErrorException, DataAccessException {
        OrderDBAccess.setOrderState(newState, orderId);
    }

    public int saveOrder(Order order) throws ProgramErrorException, DataAccessException {
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

    public void deleteOrder(int orderId) throws ProgramErrorException, DataAccessException {
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

    private OrderLine getOrderLine(int orderId, String beerName) throws ProgramErrorException, DataAccessException {
        if(orderLines == null)
            loadOrderLines();
        return OrderLineDBAccess.getOrderLine(orderId, beerName, orderLines);
    }

    public void saveOrderLine(int orderId, String beerName) throws ProgramErrorException, DataAccessException {
        if(orderLines == null)
            loadOrderLines();
        OrderLine orderLine = getOrderLine(orderId, beerName);
        orderLines.add(orderLine);
        OrderLineDBAccess.saveOrderLine(orderId, beerName, orderLine);
    }

    public void deleteOrderLine(int orderId, String beerName) throws ProgramErrorException, DataAccessException {
        if(orderLines == null)
            loadOrderLines();
        OrderLineDBAccess.deleteOrderLine(orderId, beerName);
        orderLines.remove(getOrderLine(orderId, beerName));
    }

    public void closeConnection() throws ProgramErrorException, DataAccessException {
        SingletonConnection.closeConnection();
    }
}