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

    public DBAccess()/*throws ClientException, BeerException, LocalityException, BusinessUnitException */ {
        reloadData();
    }

    public void reloadData() {
        clients = ClientDBAccess.getAllClients();
        beers = BeerDBAccess.getAllBeers();
        localities = LocalityDBAccess.getAllLocalities();
        businesses = BusinessDBAccess.getAllBusinesses(clients, localities);
        orders = OrderDBAccess.getAllOrders(clients, businesses);
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

    public void saveOrder(Order order){
        OrderDBAccess.saveOrder(order);
        orders.add(order);
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
}