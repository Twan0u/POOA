package dataccess;

import composants.*;
import exceptions.*;

import java.util.*;

public interface InterfaceData {
  public ArrayList<Order> getAllOrders() throws DataAccessException, CorruptedDataException;
  public ArrayList<Order> getOrdersWithState(String state) throws DataAccessException, CorruptedDataException;
  public ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) throws DataAccessException, CorruptedDataException;
  public ArrayList<Order> getOrdersWithStateAndDates(String state, String dateMin, String dateMax) throws DataAccessException, CorruptedDataException;
  public ArrayList<Order> getOrdersWithClientId(int idClient) throws DataAccessException, CorruptedDataException;
  public ArrayList<Order> getOrdersToDeliverWithLocalityId(int idLocality) throws DataAccessException, CorruptedDataException;
  public Order getOrder(int idOrder) throws DataAccessException, CorruptedDataException;

  public ArrayList<Beer> getAllBeers() throws DataAccessException, CorruptedDataException;
  public ArrayList<Client> getAllClients() throws DataAccessException, CorruptedDataException;
  public Client getClient(int id) throws DataAccessException, CorruptedDataException;
  public ArrayList<BusinessUnit> getBusinessOf(int id) throws DataAccessException, CorruptedDataException ;
  public int saveOrder(Order order) throws DataAccessException, CorruptedDataException, DataBackupException;
  public void deleteOrder(int orderID) throws DataAccessException, CorruptedDataException, DataDeletionException ;
  public void modifyOrder(Order order) throws DataAccessException, DataModificationException;
  public void closeConnection() throws DataAccessException;
}