package dataccess;

import composants.*;
import exceptions.*;

import java.util.ArrayList;

public class DBAccess implements InterfaceData {

    public ArrayList<Beer> getAllBeers() throws DataAccessException, CorruptedDataException {
        return BeerDBAccess.getAllBeers();
    }
    public ArrayList<Order> getAllOrders() throws DataAccessException, CorruptedDataException {
        return OrderDBAccess.getAllOrders();
    }
    public ArrayList<Client> getAllClients() throws DataAccessException, CorruptedDataException {
        return null;
    }
    public Client getClient(int id) throws DataAccessException, CorruptedDataException {
        return null;
    }
    public ArrayList<BusinessUnit> getBusinessOf(int iD) throws DataAccessException, CorruptedDataException {
        return null;
    }
    public ArrayList<Order> getOrdersWithState(String state) throws DataAccessException, CorruptedDataException {
        return null;
    }
    public ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) throws DataAccessException, CorruptedDataException {
        return null;
    }
    public ArrayList<Order> getOrdersWithClient(int clientID) throws DataAccessException, CorruptedDataException {
        return null;
    }
    public ArrayList<Order> getOrdersWithStateAndDates(String state, String dataMin, String dateMax)throws DataAccessException, CorruptedDataException {
        return null;
    }
    public ArrayList<Order> getOrdersToDeliver(int localityID) throws DataAccessException, CorruptedDataException {
        return null;
    }
    public Order getOrder(int orderID) throws DataAccessException, CorruptedDataException {
        return null;
    }
    public int saveOrder(Order order) throws DataAccessException, CorruptedDataException, DataBackupException {
       return 2;
    }
    public void deleteOrder(int orderID) throws DataAccessException, CorruptedDataException, DataDeletionException {

    }
    public void saveOrderLine(int orderID, String beerName) throws DataAccessException, CorruptedDataException, DataBackupException {

    }
    public void deleteOrderLine(int orderID, String beerName) throws DataAccessException, CorruptedDataException, DataDeletionException {

    }
    public void setOrderState(String newState, int orderId) throws DataAccessException, DataModificationException {

    }
    public void closeConnection() throws DataAccessException {

    }
}