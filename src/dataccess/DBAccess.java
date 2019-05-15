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
    private void loadClients() throws ProgramErrorException{
        clients = ClientDBAccess.getAllClients();
    }
    private void loadBeers() throws ProgramErrorException{
        beers = BeerDBAccess.getAllBeers();
    }
    private void loadLocalities() throws ProgramErrorException{
        localities = LocalityDBAccess.getAllLocalities();
    }
    private void loadBusinesses() throws ProgramErrorException{
        if(clients == null)
            loadClients();
        if(localities == null)
            loadLocalities();
        businesses = BusinessDBAccess.getAllBusinesses(clients, localities);
    }
    private void loadOrders() throws ProgramErrorException{
        if(clients == null)
            loadClients();
        if(businesses == null)
            loadBusinesses();
        orders = OrderDBAccess.getAllOrders(clients, businesses);
    }
    private void loadOrderLines() throws ProgramErrorException{
        if(beers == null)
            loadBeers();
        if(orders == null)
            loadOrders();
        orderLines = OrderLineDBAccess.getAllOrderLines(beers, orders);
    }


    public ArrayList<Client> getAllClients() throws ProgramErrorException {
        if(clients == null)
            loadClients();
        return clients;
    }

    public Client getClient(int id) throws ProgramErrorException{
        if(clients == null)
            loadClients();
        return ClientDBAccess.getClient(id, clients);
    }

    public ArrayList<Beer> getAllBeers() throws ProgramErrorException{
        if(beers == null)
            loadBeers();
        return beers;
    }

    public ArrayList<BusinessUnit> getBusinessOf(int id) throws ProgramErrorException{
        if(businesses == null)
            loadBusinesses();
        return BusinessDBAccess.getBusinessOf(id, businesses);
    }

    public ArrayList<Order> getOrdersWithState(String state) throws ProgramErrorException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrdersWithState(state, orders);
    }

    public ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) throws ProgramErrorException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrdersWithDates(dateMin, dateMax, orders);
    }

    public ArrayList<Order> getOrdersWithClient(int clientID) throws ProgramErrorException {
        if(orders == null)
            loadOrders();
        Client client = this.getClient(clientID);
        return OrderDBAccess.getOrdersWithClient(client, orders);
    }

    public ArrayList<Order> getOrdersToDeliver(int localityID) throws ProgramErrorException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrdersToDeliver(localityID, orders);
    }

    public ArrayList<Order> getAllOrders() throws ProgramErrorException {
        if(orders == null)
            loadOrders();
        return orders;
    }

    public Order getOrder(int orderID) throws ProgramErrorException {
        if(orders == null)
            loadOrders();
        return OrderDBAccess.getOrder(orderID, orders);
    }

    public void setOrderState(String newState, int orderId) throws ProgramErrorException {
        OrderDBAccess.setOrderState(newState, orderId);
    }

    public int saveOrder(Order order) throws ProgramErrorException {
        if(orders == null)
            loadOrders();
        int id = OrderDBAccess.saveOrder(order);
        order.setId(id);
        orders.add(order);
        OrderLine orderLine;
        for(int i = 0; i < order.getOrderLinesSize(); i++) {
            orderLine = order.getOrderLine(i);
            OrderLineDBAccess.saveOrderLine(id, orderLine.getBeer().getName(), orderLine);
        }
        return id;
    }

    public void deleteOrder(int orderId) throws ProgramErrorException {
        if(orders == null)
            loadOrders();
        orders.remove(getOrder(orderId));
        OrderDBAccess.deleteOrder(orderId);
    }

    private OrderLine getOrderLine(int orderId, String beerName) throws ProgramErrorException {
        if(orderLines == null)
            loadOrderLines();
        return OrderLineDBAccess.getOrderLine(orderId, beerName, orderLines);
    }

    public void saveOrderLine(int orderId, String beerName) throws ProgramErrorException{
        if(orderLines == null)
            loadOrderLines();
        orderLines.add(getOrderLine(orderId, beerName));
        OrderLineDBAccess.saveOrderLine(orderId, beerName, getOrderLine(orderId, beerName));
    }

    public void deleteOrderLine(int orderId, String beerName) throws ProgramErrorException {
        if(orderLines == null)
            loadOrderLines();
        orderLines.remove(getOrderLine(orderId, beerName));
        OrderLineDBAccess.deleteOrderLine(orderId, beerName);
    }

    public void closeConnection() throws ProgramErrorException {
        SingletonConnection.closeConnection();
    }
}