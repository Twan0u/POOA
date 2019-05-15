package dataccess;

import composants.*;
import exceptions.*;

import java.util.*;

public interface InterfaceData {

  ArrayList<Client> getAllClients() throws ProgramErrorException;
  Client getClient(int id) throws ProgramErrorException;
  ArrayList<Beer> getAllBeers() throws ProgramErrorException;
  ArrayList<BusinessUnit> getBusinessOf(int iD) throws ProgramErrorException;
  ArrayList<Order> getOrdersWithState(String state) throws ProgramErrorException;
  ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) throws ProgramErrorException;
  ArrayList<Order> getOrdersWithClient(int clientID) throws ProgramErrorException;
  ArrayList<Order> getOrdersToDeliver(int localityID) throws ProgramErrorException;
  ArrayList<Order> getAllOrders() throws ProgramErrorException;
  void saveOrder(Order order) throws ProgramErrorException;
  void deleteOrder(int orderID) throws ProgramErrorException;
  void saveOrderLine(int orderID, String beerName) throws ProgramErrorException;
  void deleteOrderLine(int orderID, String beerName) throws ProgramErrorException;
  void setOrderState(String newState, int orderId) throws ProgramErrorException;
}