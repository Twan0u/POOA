package dataccess;

import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;

public interface InterfaceData {

  ArrayList<Client> getAllClients()throws ClientException;
  Client getClient(int id);
  ArrayList<Beer> getAllBeers()throws BeerException;
  ArrayList<BusinessUnit> getBusinessOf(int iD)throws BusinessUnitException,LocalityException;
  ArrayList<Order> getOrdersWithState(String state);
  ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax);
  ArrayList<Order> getOrdersWithClient(int clientID);
  ArrayList<Order> getOrdersToDeliver(int localityID);
  ArrayList<Order> getAllOrders();
  void saveOrder(Order order);
  void deleteOrder(int orderID); // todo
  void addOrderLine(int orderID, String beerName); // todo
  void deleteOrderLine(int orderID, String beerName); // todo
  void reloadData();
}