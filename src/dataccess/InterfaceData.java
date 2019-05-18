package dataccess;

import composants.*;
import exceptions.*;

import java.util.*;

public interface InterfaceData {

  ArrayList<Client> getAllClients() throws DataAccessException, CorruptedDataException;
  Client getClient(int id) throws DataAccessException, CorruptedDataException;
  ArrayList<Beer> getAllBeers() throws DataAccessException, CorruptedDataException;
  ArrayList<BusinessUnit> getBusinessOf(int iD) throws DataAccessException, CorruptedDataException;
  ArrayList<Order> getOrdersWithState(String state) throws DataAccessException, CorruptedDataException;
  ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) throws DataAccessException, CorruptedDataException;
  ArrayList<Order> getOrdersWithClient(int clientID) throws DataAccessException, CorruptedDataException;
  ArrayList<Order> getOrdersWithStateAndDates(String state, String dataMin, String dateMax)throws DataAccessException, CorruptedDataException;
  ArrayList<Order> getOrdersToDeliver(int localityID) throws DataAccessException, CorruptedDataException;
  ArrayList<Order> getAllOrders(String state) throws DataAccessException, CorruptedDataException;
  Order getOrder(int orderID) throws DataAccessException, CorruptedDataException;
  int saveOrder(Order order) throws DataAccessException, CorruptedDataException, DataBackupException;
  void deleteOrder(int orderID) throws DataAccessException, CorruptedDataException, DataDeletionException;
  void saveOrderLine(int orderID, String beerName) throws DataAccessException, CorruptedDataException, DataBackupException;
  void deleteOrderLine(int orderID, String beerName) throws DataAccessException, CorruptedDataException, DataDeletionException;
  void setOrderState(String newState, int orderId) throws DataAccessException, DataModificationException;
  void closeConnection() throws DataAccessException;
}