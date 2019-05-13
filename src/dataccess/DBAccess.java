package dataccess;

import java.util.*;

import composants.*;
import exceptions.*;

import java.util.HashMap;

public class DBAccess implements InterfaceData {
    private ArrayList<Client> clients;
    private ArrayList<Beer> beers;
    private ArrayList<Locality> localities;

    public DBAccess()throws ClientException, BeerException, LocalityException, BusinessUnitException {
        clients = getAllClients();
        beers = getAllBeers();
        localities = LocalityDBAccess.getAllLocalities();
        BusinessDBAccess.linkBusinessesToClients(clients, localities);
    }

    public ArrayList<Client> getAllClients()throws ClientException {
        return ClientDBAccess.getAllClients();
    }

    public Client getClient(int id, ArrayList<Client> clients) throws ClientException{
        return ClientDBAccess.getClient(id, clients);
    }

    public ArrayList<Beer> getAllBeers()throws BeerException {
        return BeerDBAccess.getAllBeers();
    }

    public ArrayList<BusinessUnit> getBusinessOf(int id) throws BusinessUnitException, LocalityException {
        return BusinessDBAccess.getBusinessOf(id, clients);
    }
}