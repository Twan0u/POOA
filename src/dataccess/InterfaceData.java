package dataccess;

import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;

public interface InterfaceData {

  ArrayList<Client> getAllClients()throws ClientException;
  Client getClient(int id, ArrayList<Client> clients)throws ClientException;
  ArrayList<Beer> getAllBeers()throws BeerException;
  ArrayList<BusinessUnit> getBusinessOf(int id)throws BusinessUnitException,LocalityException;
}