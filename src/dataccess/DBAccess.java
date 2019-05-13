package dataccess;

import java.util.*;

import composants.*;
import exceptions.*;

public class DBAccess implements InterfaceData {
    private ArrayList<Client> clients;
    private ArrayList<Beer> beers;
    private ArrayList<Locality> localities;

    public DBAccess()/*throws ClientException, BeerException, LocalityException, BusinessUnitException */ {
        clients = getAllClients();
        beers = getAllBeers();
        localities = LocalityDBAccess.getAllLocalities();
        BusinessDBAccess.linkBusinessesToClients(clients, localities);
    }

    public ArrayList<Client> getAllClients()/*throws ClientException*/ {
        return ClientDBAccess.getAllClients();
    }

    public Client getClient(int id){
        return ClientDBAccess.getClient(id, clients);
    }

    public ArrayList<Beer> getAllBeers()/*throws BeerException*/ {
        return BeerDBAccess.getAllBeers();
    }

    public ArrayList<BusinessUnit> getBusinessOf(int id) /*throws BusinessUnitException, LocalityException */{
        return BusinessDBAccess.getBusinessOf(id, clients);
    }

    public void saveOrder(Order order){
        OrderDBAccess.saveOrder(order);
    }
}