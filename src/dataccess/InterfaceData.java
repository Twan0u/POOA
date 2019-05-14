package dataccess;

import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;

public interface InterfaceData {

  ArrayList<Client> getAllClients()throws ClientException;
  Client getClient(int id);
  ArrayList<Beer> getAllBeers()throws BeerException;
  ArrayList<BusinessUnit> getBusinessOf(int id)throws BusinessUnitException,LocalityException;
  ArrayList<Order> getOrdersWithState(String state);
  ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax);
  public ArrayList<Order> getOrdersWithClient(int clientID);
  public ArrayList<Order> getOrdersToDeliver(int localityID);
  void reloadData();
}