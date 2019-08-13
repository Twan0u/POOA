package business;

import dataccess.*;
import composants.*;
import exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
* <b>classe de la couche Business</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 1.5
*
* AMELIORATIONS : Prévenir le Stock qu'une commande importante  va vider son stock
*
*/
public class Business implements BusinessInterface{
    private InterfaceData dataLayer = new DBAccess();
    //private DataMock dataLayer = new DataMock();

    public Client[] getAllClients()throws ProgramErrorException {
      ArrayList<Client> clients = null;
      try{
        clients = dataLayer.getAllClients();
      }catch(Exception e){
        throw new ProgramErrorException("Erreur de chargement de tous les clients depuis la base de donée");
      }

      if (clients == null){
        throw new ProgramErrorException("Il n'y a pas de Client chargé dans la base de Donnée (null)");
      }else if(clients.size()==0){
        throw new ProgramErrorException("Il n'y a pas de Client chargé dans la base de Donnée (0 clients)");
      }else{
        Client [] out = new Client[clients.size()];
        for(int i = 0; i<out.length;i++){
          out[i] = clients.get(i);
        }
        return out;
      }
    }


    public ArrayList<Client> getAllClientsArray()throws ProgramErrorException{
      try{
        return dataLayer.getAllClients();
      }catch(Exception e){
        throw new ProgramErrorException("Erreur de chargement de tous les clients depuis la base de donée");
      }
    }


    public ArrayList<Beer> getAllBeers()throws ProgramErrorException{
      ArrayList<Beer> beers;
      try{
        beers  = dataLayer.getAllBeers();
      }catch(Exception e){
        throw new ProgramErrorException("Il y a eu une erreur dans le chargement des bières depuis la base de donée");
      }
      if (beers == null){
        throw new ProgramErrorException("il n'y a pas de bières dans la base de donneé");
      }else if (beers.size() == 0){
        throw new ProgramErrorException("il n'y a pas de bières dans la base de donneé");
      }

      return beers;
    }

    public ArrayList<BusinessUnit> getArrayBusinessOf(int id)throws ProgramErrorException{
      try{
        return dataLayer.getBusinessOf(id);
      }catch(Exception e){
        throw new ProgramErrorException("il y a eu une erreur lors du chargement des données depuis la base de donnée");
      }

    }

    public BusinessUnit[] getBusinessOf(int id)throws ProgramErrorException{

      ArrayList<BusinessUnit> businesss;
      if (id < 0 ){
        throw new ProgramErrorException("L'identifiant de client est invalide");
      }
      try{
        businesss = dataLayer.getBusinessOf(id);
      }catch(Exception e){
        throw new ProgramErrorException("il y a eu une erreur lors du chargement des données depuis la base de donnée");
      }

      if (businesss == null){
        return null;
      }

      BusinessUnit [] out = new BusinessUnit[businesss.size()];

      for(int i = 0; i<out.length;i++){
        out[i] = businesss.get(i);

      }
      return out;

    }

    public ArrayList<Beer> getLowQuantityBeers()throws ProgramErrorException{
      ArrayList<Beer> lowBeers = new ArrayList<>();
      ArrayList<Beer> allBeers;
      try{
        allBeers = dataLayer.getAllBeers();
      }catch(Exception e){
        throw new ProgramErrorException("Il y a eu une erreur dans le chargement des bières depuis la couche data");
      }
      if (allBeers == null){
        throw new ProgramErrorException("Aucune bière n'a été chargée depuis la base de donnée");
      }
      for(int i = 0; i<allBeers.size();i++){
        if (allBeers.get(i).isLowInQuantity()){
          lowBeers.add(allBeers.get(i));
        }
      }
      return lowBeers;
    }

  public int saveOrder(Order order) throws ProgramErrorException{
    int idSavedOrder=-1;
    if (order == null){
      throw new ProgramErrorException("Il n'y a pas d'order à ajouter à la base de donnée");
    }
    try {
      idSavedOrder = dataLayer.saveOrder(order);
    }catch(Exception e){
      throw new ProgramErrorException("Il y a eu un problème lors de l'enregistrement dans la base de donnée");
    }
    if (idSavedOrder == -1){
      throw new ProgramErrorException("Il y a eu un problème avec la récupération d'un identifiant de commande");
    }
    return idSavedOrder;
  }


  public Order[] getAllOrders()throws ProgramErrorException{
    ArrayList<Order> orders;
    try {
      orders = dataLayer.getAllOrders();
    }catch(Exception e){
      throw new ProgramErrorException("Il y a eu une erreur lors du chargement de toutes les commandes depuis la base de donneé");
    }
    if (orders == null){
      throw new ProgramErrorException("aucune commande n'a pu être chargée depuis la base de donnée");
    }
      Order [] out = new Order[orders.size()];
      for(int i = 0; i<out.length;i++){
        out[i] = orders.get(i);
      }
    return out;
  }

  public ArrayList<Order> getOrdersToDeliver()throws DataAccessException, CorruptedDataException{ //Retirer les orders Qu'il ne faut pas livrer
    return dataLayer.getOrdersWithState("prepared");
  }

  public Order getOrder(int orderId){
    try{
      return dataLayer.getOrder(orderId);//Can be null if no order is found
    }catch(Exception ignore){
      //TODO
    }
    return null;
  }

  public void deleteOrder(int orderID) throws DataAccessException, CorruptedDataException, DataDeletionException{
    dataLayer.deleteOrder(orderID);
  }

  public StatsOnOrders getStatsOnOrders() {
      try {
        ArrayList<Order> orders = dataLayer.getAllOrders();

        HashMap<String, Integer> beersOrderCount = new HashMap<>();
        ArrayList<Beer> beers = dataLayer.getAllBeers();
        for(Beer b : beers){
          beersOrderCount.put(b.getName(), 0);
        }

        double orderAvgValue;
        int nbOrders = 0;
        double totalValue = 0;
        int nbOrderLines;
        OrderLine oL = null;
        String beerName;
        int quantity;

        for(Order o : orders) {
          nbOrders++;
          nbOrderLines = o.getOrderLinesSize();
          for(int i = 0; i < nbOrderLines; i++) {
            oL = o.getOrderLine(i);
            quantity = oL.getQuantity();
            totalValue += quantity * oL.getPrice();
            beerName = oL.getBeer().getName();
            beersOrderCount.put(beerName, beersOrderCount.get(beerName)+quantity);
          }
        }
        orderAvgValue = totalValue / nbOrders;
        return new StatsOnOrders(orderAvgValue, beersOrderCount);
      }
      catch(Exception e){

    }
      return null;
  }

  public void modifyOrder(Order order)throws DataAccessException, DataModificationException{
      dataLayer.modifyOrder(order);
  }

  public ArrayList<Order> getOrdersToDeliverWithLocalityId(int idLocality) throws DataAccessException, CorruptedDataException{
    return dataLayer.getOrdersToDeliverWithLocalityId(idLocality);
  }

  public ArrayList<Order> getOrdersWithDates(String dateMin, String dateMax)throws DataAccessException, CorruptedDataException {
      return dataLayer.getOrdersWithDates(dateMin, dateMax);
  }

  public ArrayList<Order> getOrdersWithStateAndDates(String state, String dateMin, String dateMax) throws DataAccessException, CorruptedDataException{
      return dataLayer.getOrdersWithStateAndDates(state, dateMin, dateMax);
  }
}
