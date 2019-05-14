package dataccess;

import java.util.*;

import composants.*;
import exceptions.*;

public class DBAccess implements InterfaceData {
    private ArrayList<Client> clients;
    private ArrayList<Beer> beers;
    private ArrayList<Locality> localities;
    private ArrayList<BusinessUnit> businesses;
    private ArrayList<Order> orders;
    private ArrayList<OrderLine> orderLines;

    public DBAccess()/*throws ClientException, BeerException, LocalityException, BusinessUnitException */ {
        reloadData();
    }

    public void reloadData() {
        clients = ClientDBAccess.getAllClients();
        beers = BeerDBAccess.getAllBeers();
        localities = LocalityDBAccess.getAllLocalities();
        businesses = BusinessDBAccess.getAllBusinesses(clients, localities);
        orders = OrderDBAccess.getAllOrders(clients, businesses);
        orderLines = OrderLineDBAccess.getAllOrderLines(beers, orders);
    }

    public ArrayList<Client> getAllClients()/*throws ClientException*/ {
        return clients;
    }

    public Client getClient(int id){
        return ClientDBAccess.getClient(id, clients);
    }

    public ArrayList<Beer> getAllBeers()/*throws BeerException*/ {
        return beers;
    }

    public ArrayList<BusinessUnit> getBusinessOf(int id) /*throws BusinessUnitException, LocalityException */{
        return BusinessDBAccess.getBusinessOf(id, clients);
    }

    public ArrayList<Order> getOrdersWithState(String state) {
        return OrderDBAccess.getOrdersWithState(state, orders);
    }

    public ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) {
        return OrderDBAccess.getOrdersWithDates(dateMin, dateMax, orders);
    }

    public ArrayList<Order> getOrdersWithClient(int clientID) {
        Client client = this.getClient(clientID);
        return OrderDBAccess.getOrdersWithClient(client, orders);
    }

    public ArrayList<Order> getOrdersToDeliver(int localityID) {
        return OrderDBAccess.getOrdersToDeliver(localityID, orders);
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    private ArrayList<OrderLine> getAllOrderLines() {
        return orderLines;
    }

    private Order getOrder(int orderID) {
        return OrderDBAccess.getOrder(orderID, orders);
    }

    public void setOrderState(String newState, int orderId) {
        OrderDBAccess.setOrderState(newState, orderId);
    }

    public void saveOrder(Order order){
        orders.add(order);
        OrderDBAccess.saveOrder(order);
    }

    public void deleteOrder(int orderId) {
        orders.remove(getOrder(orderId));
        OrderDBAccess.deleteOrder(orderId);
    }

    private OrderLine getOrderLine(int orderId, String beerName) {
        return OrderLineDBAccess.getOrderLine(orderId, beerName, orderLines);
    }

    public void saveOrderLine(int orderId, String beerName){
        orderLines.add(getOrderLine(orderId, beerName));
        OrderLineDBAccess.saveOrderLine(orderId, beerName, getOrderLine(orderId, beerName));
    }

    public void deleteOrderLine(int orderId, String beerName) {
        orderLines.remove(getOrderLine(orderId, beerName));
        OrderLineDBAccess.deleteOrderLine(orderId, beerName);
    }
}