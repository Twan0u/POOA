package dataccess;

import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;

public class DataMock implements InterfaceData {

  private static ArrayList<Client> clients = new ArrayList<>();
  private static ArrayList<Beer> beers = new ArrayList<>();
  private static ArrayList<Order> orders = new ArrayList<>();

  private int load = 0;



  public  ArrayList<Client> getAllClients()throws ProgramErrorException{
    try{
          clients.clear();
          clients.add(new Client (0, "Nathan", "0032007007005", 10, "TVA_025522095251"));
          clients.add(new Client (1, "Antoine", "0032498194975", 80, "TVA_062232095251"));
          clients.add(new Client (2, "Benjamin", "0032498191234", 50, "TVA_062232095223"));
          clients.add(new Client (3, "Corentin", "0032498195678", 10, "TVA_062232095654"));
          clients.add(new Client (4, "Emilie", "0032498198910", 25, "TVA_0622320956789"));
          clients.add(new Client (5, "BOB", "0032498191112", 66, "TVA_062232095765"));
          return clients;
    }catch(Exception e){
      throw new ProgramErrorException(e.getMessage());
    }
  }



  public Client getClient(int id)throws ProgramErrorException{
    try{
      return clients.get(id);
    }catch(Exception e){}
      return null;
  }

  private void loadbusiness()throws BusinessUnitException,LocalityException{
    if (load == 0){
      Locality local = new Locality (1, "Localité du petit bois", "7911");
      Locality local2 = new Locality (2, "Localité du saucisson", "7900");
      BusinessUnit bubu = new BusinessUnit (1, clients.get(0), local, "Rue de la faim", "12");
      BusinessUnit blyat = new BusinessUnit (2, clients.get(0), local2, "Rue des potiers", "3");
      BusinessUnit bubui = new BusinessUnit (1, clients.get(1), local, "Rue des marchands", "22");
      BusinessUnit blyati = new BusinessUnit (2, clients.get(1), local2, "Rue Raymond le gros", "16");
      load = 1;
    }
  }


  public ArrayList<BusinessUnit> getBusinessOf(int id)throws ProgramErrorException{
    try{
      loadbusiness();
      return clients.get(id).getBusiness();
    }catch(Exception e){
      throw new ProgramErrorException(e.getMessage());
    }
  }

  public ArrayList<Beer> getAllBeers()throws ProgramErrorException{
    try{
      beers.clear();
      beers.add(new Beer("Bush Blonde",2.20,50,10));
      beers.add(new Beer("Blonde ambrée",1.50,2));
      beers.add(new Beer("Bush double",2.50,50,100));
      beers.add(new Beer("Bush triple",3.10,11));
      beers.add(new Beer("Trap Triple",2.20,10));
      beers.add(new Beer("Trap Quadruple",1.50,2));
      beers.add(new Beer("Orval",2.50,50,100));
      beers.add(new Beer("Chouffe",3.10,11));
      beers.add(new Beer("Chouffe Verte",3.10,10,11));
      return beers;
    }catch(Exception e){
      throw new ProgramErrorException(e.getMessage());
    }
  }

  public ArrayList<Order> getOrdersWithState(String state) throws ProgramErrorException{return null;}
  public ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) throws ProgramErrorException{return null;}
  public ArrayList<Order> getOrdersWithClient(int clientID) throws ProgramErrorException{return null;}
  public ArrayList<Order> getOrdersToDeliver(int localityID) throws ProgramErrorException{return null;}

  public ArrayList<Order> getAllOrders() throws ProgramErrorException{
    try{
      Locality local = new Locality (1, "Localité du petit bois", "7911");
      BusinessUnit bubu = new BusinessUnit (1, clients.get(0), local, "Rue de la faim", "12");
      Client client = new Client (0, "Nathan", "0032007007005", 10, "TVA_025522095251");
      orders.add(new Order(0, bubu, client, false, "2019-06-30", "New"));
    }catch (Exception e){
      throw new ProgramErrorException(e.getMessage());
    }
    return this.orders;
  }

  public Order getOrder(int orderID) throws ProgramErrorException{
    try{
      Locality local = new Locality (1, "Localité du petit bois", "7911");
      BusinessUnit bubu = new BusinessUnit (1, clients.get(0), local, "Rue de la faim", "12");
      Client client = new Client (0, "Nathan", "0032007007005", 10, "TVA_025522095251");
      return new Order(orderID, bubu, client, false, "2019-06-30", "New");
    }catch (Exception e){
      throw new ProgramErrorException(e.getMessage());
    }
  }

  public void saveOrder(Order order) throws ProgramErrorException{
    System.out.println("saved : "+ order.toString());
  }
  public void deleteOrder(int orderID) throws ProgramErrorException{}
  public void saveOrderLine(int orderID, String beerName) throws ProgramErrorException{}
  public void deleteOrderLine(int orderID, String beerName) throws ProgramErrorException{}
  public void setOrderState(String newState, int orderId) throws ProgramErrorException{}
  public void closeConnection() throws ProgramErrorException{}

}
