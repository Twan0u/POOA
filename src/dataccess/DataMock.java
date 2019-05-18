package dataccess;

import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;

public class DataMock  {

  private static ArrayList<Client> clients = new ArrayList<>();
  private static ArrayList<Beer> beers = new ArrayList<>();
  private static ArrayList<Order> orders = new ArrayList<>();
  private static ArrayList<BusinessUnit> business = new ArrayList<>();

  private int load = 0;

  public DataMock(){
    try{
      clients.clear();
      clients.add(new Client (0, "Nathan", "0032007007005", 10, "TVA_025522095251"));
      clients.add(new Client (1, "Antoine", "0032498194975", 80, "TVA_062232095251"));
      clients.add(new Client (2, "Benjamin", "0032498191234", 50, "TVA_062232095223"));
      clients.add(new Client (3, "Corentin", "0032498195678", 10, "TVA_062232095654"));
      clients.add(new Client (4, "Emilie", "0032498198910", 25, "TVA_0622320956789"));
      clients.add(new Client (5, "BOB", "0032498191112", 66, "TVA_062232095765"));

    Locality local = new Locality (1, "Localité du petit bois", "7911");
    Locality local2 = new Locality (2, "Localité du saucisson", "7900");
    business.add(new BusinessUnit (1, clients.get(0), local, "Rue de la faim", "12"));
    business.add(new BusinessUnit (2, clients.get(0), local2, "Rue des potiers", "3"));
    business.add(new BusinessUnit (1, clients.get(1), local, "Rue des marchands", "22"));
    business.add(new BusinessUnit (2, clients.get(1), local2, "Rue Raymond le gros", "16"));
  }catch(Exception ignore){}
  }

  public ArrayList<Client> getAllClients()throws DataAccessException, CorruptedDataException{
    return clients;
  }

  public Client getClient(int id)throws DataAccessException, CorruptedDataException{
      for(int i=0;i<clients.size();i++){
        if (clients.get(i).getId() == id){
          return clients.get(id);
        }
      }
      return null;
  }

  public ArrayList<BusinessUnit> getBusinessOf(int id)throws DataAccessException, CorruptedDataException{
    try{
      return getClient(id).getBusiness();
    }catch(Exception e){
      System.out.println(e.getMessage());
    }
    return null;
  }

  public ArrayList<Beer> getAllBeers()throws DataAccessException, CorruptedDataException{
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

    }
    return null;
  }

  public ArrayList<Order> getOrdersWithState(String state) throws DataAccessException, CorruptedDataException{
    if (state == "prepared"){
      try{
        ArrayList<Order> orders = new ArrayList<>();;
        Locality local = new Locality (1, "Localité du petit bois", "7911");
        Locality local2 = new Locality (2, "Localité truc", "7000");
        Locality local3 = new Locality (3, "Bidouille les bains", "1000");
        BusinessUnit bubu = new BusinessUnit (1, clients.get(0), local, "Rue de la faim", "12");
        BusinessUnit business2 = new BusinessUnit (1, clients.get(1), local2, "Rue de la faim", "12");
        BusinessUnit business3 = new BusinessUnit (1, clients.get(2), local2, "Rue de la faim", "12");
        Client client = new Client (0, "Nathan", "0032007007005", 10, "TVA_025522095251");
        orders.add(new Order(0, bubu, client, false, "2019-06-30", "ToDeliver"));
        orders.add(new Order(1, business2, client, true, "2019-06-30", "ToDeliver"));
        orders.add(new Order(2, bubu, client, false, "2019-06-30", "ToDeliver"));
        orders.add(new Order(3, business3, client, true, "2019-06-30", "ToDeliver"));
        orders.add(new Order(4, bubu, client, false, "2019-06-30", "ToDeliver"));
        return orders;
      }catch(Exception e){}
    }
    return null;
  }
  public ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax) throws DataAccessException, CorruptedDataException{return null;}
  public ArrayList<Order> getOrdersWithClient(int clientID) throws DataAccessException, CorruptedDataException{return null;}
  public ArrayList<Order> getOrdersToDeliver(int localityID) throws DataAccessException, CorruptedDataException{return null;}

  public ArrayList<Order> getAllOrders() throws DataAccessException, CorruptedDataException{
    Order newOrder = null;
    try{
      Locality local = new Locality (1, "Localité du petit bois", "7911");
      BusinessUnit bubu = new BusinessUnit (1, clients.get(0), local, "Rue de la faim", "12");
      Client client = new Client (0, "Nathan", "0032007007005", 10, "TVA_025522095251");
      newOrder = new Order(0, bubu, client, false, "2019-06-30", "New");
      orders.add(newOrder);
    }catch (Exception e){}
    return this.orders;
  }

  public Order getOrder(int orderID) throws DataAccessException, CorruptedDataException{
    for (int i=0;i<orders.size();i++){
      if (orderID == orders.get(i).getId()){
        return orders.get(i);
      }
    }
    return null;
  }

  public int saveOrder(Order order) throws DataAccessException, CorruptedDataException, DataBackupException{
    try{
      order.setId(42);
      orders.add(order);
    }catch(Exception error){
      throw new DataBackupException("erreur dans l'enregistrement");
    }
    return 42;
  }

  public void deleteOrder(int orderID) throws DataAccessException, CorruptedDataException, DataDeletionException{}
  public void saveOrderLine(int orderID, String beerName) throws DataAccessException, CorruptedDataException, DataBackupException{}
  public void deleteOrderLine(int orderID, String beerName) throws DataAccessException, CorruptedDataException, DataDeletionException{}
    public ArrayList<Order> getOrdersWithStateAndDates(String state, String dataMin, String dateMax)throws DataAccessException, CorruptedDataException{
      return null;
    }
      public void closeConnection() throws DataAccessException{}
        public void setOrderState(String newState, int orderId) throws DataAccessException, DataModificationException{}
}
