package dataccess;

import composants.*;
import exceptions.*;

import java.util.*;

public interface InterfaceData {

  ArrayList<Client> getAllClients() throws ProgramErrorException, DataAccessException;
  Client getClient(int id) throws ProgramErrorException, DataAccessException;
  ArrayList<Beer> getAllBeers() throws ProgramErrorException, DataAccessException;
  ArrayList<BusinessUnit> getBusinessOf(int iD) throws ProgramErrorException, DataAccessException;
  ArrayList<Order> getOrdersWithState(String state) throws ProgramErrorException, DataAccessException;
  ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) throws ProgramErrorException, DataAccessException;
  ArrayList<Order> getOrdersWithClient(int clientID) throws ProgramErrorException, DataAccessException;
  ArrayList<Order> getOrdersToDeliver(int localityID) throws ProgramErrorException, DataAccessException;
  ArrayList<Order> getAllOrders() throws ProgramErrorException, DataAccessException;
  Order getOrder(int orderID) throws ProgramErrorException, DataAccessException;
  int saveOrder(Order order) throws ProgramErrorException, DataAccessException;
  void deleteOrder(int orderID) throws ProgramErrorException, DataAccessException;
  void saveOrderLine(int orderID, String beerName) throws ProgramErrorException, DataAccessException;
  void deleteOrderLine(int orderID, String beerName) throws ProgramErrorException, DataAccessException;
  void setOrderState(String newState, int orderId) throws ProgramErrorException, DataAccessException;
  void closeConnection() throws ProgramErrorException, DataAccessException;
}