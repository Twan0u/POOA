package business;
import dataccess.*;
import composants.*;
import exceptions.*;

import java.util.ArrayList;

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

    private static InterfaceData dataLayer = new DataMock();

    public Client[] getAllClients()throws ProgramErrorException{
      ArrayList<Client> clients = null;

      try{clients=  dataLayer.getAllClients();
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


    public BusinessUnit[] getBusinessOf(int id)throws ProgramErrorException{
<<<<<<< HEAD
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
=======
        ArrayList<BusinessUnit> businesss =dataLayer.getBusinessOf(id);
      if (businesss == null){return null;}
      if (businesss.size() == 0){return null;}
      BusinessUnit [] out = new BusinessUnit[businesss.size()];
      for(int i = 0; i<out.length;i++){

        out[i] = businesss.get(i);

>>>>>>> 73aff75519900e6484ad2afdccaf1cf5e32d4be9
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
}
