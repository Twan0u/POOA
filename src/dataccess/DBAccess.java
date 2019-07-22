package dataccess;

import composants.*;
import exceptions.*;

import java.util.ArrayList;

public class DBAccess implements InterfaceData {

    private ClientAccess clientAccess = new ClientDBAccess();
    private BeerAccess beerAccess = new BeerDBAccess();
    private OrderAccess orderAccess = new OrderDBAccess();
    private BusinessAccess businessAccess = new BusinessDBAccess();


    public ArrayList<Order> getAllOrders() throws DataAccessException, CorruptedDataException {
        return orderAccess.getOrders(null, null, null, null, null, null, false, 1);
    }
    public ArrayList<Order> getOrdersWithState(String state) throws DataAccessException, CorruptedDataException {
        return orderAccess.getOrders(state, null, null, null, null, null, false, 2);
    }
    public ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) throws DataAccessException, CorruptedDataException {
        return orderAccess.getOrders(null, null, null, dateMin, dateMax, null, false, 3);
    }
    public ArrayList<Order> getOrdersWithStateAndDates(String state, String dateMin, String dateMax) throws DataAccessException, CorruptedDataException {
        return orderAccess.getOrders(state, null, null, dateMin, dateMax, null, false, 4);
    }
    public ArrayList<Order> getOrdersWithClientId(int idClient) throws DataAccessException, CorruptedDataException {
        return orderAccess.getOrders(null, null, null, null, null, idClient, false, 5);
    }
    public ArrayList<Order> getOrdersToDeliverWithLocalityId(int idLocality) throws DataAccessException, CorruptedDataException {
        return orderAccess.getOrders(null, idLocality, null, null, null, null, true, 6);
    }
    public Order getOrder(int idOrder) throws DataAccessException, CorruptedDataException {
        return orderAccess.getOrders(null, null, idOrder, null, null, null, false, 7).get(0);
    }
    public int saveOrder(Order order) throws DataAccessException, CorruptedDataException, DataBackupException {
        return orderAccess.saveOrder(order);
    }
    public void deleteOrder(int orderID) throws DataAccessException, CorruptedDataException, DataDeletionException {
        orderAccess.deleteOrder(orderID);
    }
    public void modifyOrder(Order order) throws DataAccessException, DataModificationException {
        orderAccess.modifyOrder(order);
    }


    public ArrayList<Beer> getAllBeers() throws DataAccessException, CorruptedDataException {
        return beerAccess.getAllBeers();
    }


    public ArrayList<Client> getAllClients() throws DataAccessException, CorruptedDataException {
        return clientAccess.getClients(null);
    }
    public Client getClient(int id) throws DataAccessException, CorruptedDataException {
        return clientAccess.getClients(id).get(0);
    }


    public ArrayList<BusinessUnit> getBusinessOf(int id) throws DataAccessException, CorruptedDataException {
        return businessAccess.getBusinessWithClientID(id);
    }


    public void closeConnection() throws DataAccessException {
        SingletonConnection.closeConnection();
    }
}