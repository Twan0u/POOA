package composants;
import exceptions.OrderException;
import exceptions.OrderLineException;
import java.util.*;
/**
* <b>classe de l'objet Order</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 1.6
*
* L'objet se compose de:
* <ul>
*   <li>un numéro d'identification</li>
*   <li>une adresse de livraison associée</li>
*   <li>un client qui effectue la commande</li>
*   <li>une identification de commande prioritaire</li>
*   <li>une date de commande </li>
*   <li>un état de cette commande</li>
*   <li>une limite (facultative) pour la livraison</li>
* </ul>
*
*/
public class Order{
  /*____VARIABLES____*/

  /**numero d'identification de la commande*/
  private int id = 0;
  /**adresse (facultative) de livraison*/
  private BusinessUnit businessUnitId = null;
  /**Client lié à cette commande*/
  private Client client = null;
  /**Commande prioritaire*/
  private boolean hasPriority = false;
  /**Date à laquelle la commande à été passée*/
  private String orderDate = null;
  /**Etat de la commande (limité à qq choix)*/
  private String state = null;
  /**limite de jours pour effectuer la commande (facultatif)*/
  private int timeLimit = -1;
  /**Liste des éléments commandés*/
  private ArrayList<OrderLine> orderList = new ArrayList<>();

  /*____Constructeurs____*/

  /** Methode constructeur pour les objets Order
  * @param id
  *             Numero d'identification.
  * @param businessUnitId
  *             adresse de livraison de la commande (null si pas à livrer)
  * @param client
  *             Client ayant effectué la commande
  * @param hasPriority
  *             la commande est elle prioritaire
  * @param orderDate
  *             date à laquelle la commande a été effectuée
  * @param state
  *             état de la commande
  * @param timeLimit
  *             temps (en jours) pour préparer la commande
  * @see BusinessUnit
  * @see Client
  * @throws OrderException erreur envoyée en cas de données erronnées
  * @since 1.0
  */
  public Order(int id, BusinessUnit businessUnitId, Client client, boolean hasPriority, String orderDate, String state, int timeLimit)throws OrderException{
    setId(id);
    setBusinessUnitId(businessUnitId);
    setClient(client);
    setHasPriority(hasPriority);
    setOrderDate(orderDate);
    setState(state);
    setTimeLimit(timeLimit);
  }

  /** Methode constructeur pour les objets Order
  * @param id
  *             Numero d'identification.
  * @param client
  *             Client ayant effectué la commande
  * @param hasPriority
  *             la commande est elle prioritaire
  * @param orderDate
  *             date à laquelle la commande a été effectuée
  * @param state
  *             état de la commande
  * @param timeLimit
  *             temps (en jours) pour préparer la commande
  * @see Client
  * @throws OrderException erreur envoyée en cas de données erronnées
  * @since 1.0
  */
  public Order(int id, Client client, boolean hasPriority, String orderDate, String state, int timeLimit)throws OrderException{
    this(id, null, client, hasPriority, orderDate, state, timeLimit);
  }

  /** Methode constructeur pour les objets Order
  * @param id
  *             Numero d'identification.
  * @param businessUnitId
  *             adresse de livraison de la commande (null si pas à livrer)
  * @param client
  *             Client ayant effectué la commande
  * @param hasPriority
  *             la commande est elle prioritaire
  * @param orderDate
  *             date à laquelle la commande a été effectuée
  * @param state
  *             état de la commande
  * @see BusinessUnit
  * @see Client
  * @throws OrderException erreur envoyée en cas de données erronnées
  * @since 1.0
  */
  public Order(int id, BusinessUnit businessUnitId, Client client, boolean hasPriority, String orderDate, String state)throws OrderException{
    this(id, businessUnitId, client, hasPriority, orderDate, state, -1);
  }
  /** Methode constructeur pour les objets Order
  * @since 1.4
  */
  public Order(){
    this.setState("New");
    this.setHasPriority(false);
    this.setBusinessUnitId(null);
    this.setOrderDate("2019-05-15");
  }

  /*____METHODES____*/

  /** Ajoute une ligne de commande à la commande
  * @param item
  *         ligne de commande
  * @see OrderLine
  * @since 1.5
  */
  public void additem(OrderLine item) throws OrderException, OrderLineException{
    if (item == null){
      throw new OrderException("Ajout d'une ligne vide à la commande");
    }
      for(int i=0; i<orderList.size();i++){
        OrderLine current = orderList.get(i);
        if (item.getBeer().getName().compareTo(current.getBeer().getName()) == 0){
          int totalQuantity =current.getQuantity()+item.getQuantity();
          current.setQuantity(totalQuantity);
          orderList.set(i,current);
          return;
        };
      }
    this.orderList.add(item);
  }

  /*____GETTEURS____*/

  /** Getteur pour la variable id
  * @return numéro d'identification de la commande
  * @since 1.0
  */
  public int getId(){
    return this.id;
  }

  /** Getteur pour la variable businessUnitId
  * @return l'adresse de livraison(si à livrer) et null si la commande n'est pas a livrer
  * @since 1.0
  */
  public BusinessUnit getBusinessUnitId(){
    return this.businessUnitId;
  }

  /** Getteur pour la variable client
  * @return le client à qui appartient la commande
  * @since 1.0
  */
  public Client getClient(){
    return this.client;
  }

  /** Getteur pour la variable hasPriority
  * @return si la commande est prioritaire ou non
  * @since 1.0
  */
  public boolean getHasPriority(){
    return this.hasPriority;
  }

  /** Getteur pour la variable orderDate
  * @return la date à laquelle la commande à été effectuée
  * @since 1.0
  */
  public String getOrderDate(){
    return this.orderDate;
  }

  /** Getteur pour la variable state
  * @return l'état actuel de la commande
  * @since 1.0
  */
  public String getState(){
    return this.state;
  }

  /** Getteur pour la variable timeLimit
  * @return nombre de jours maximum pour livrer la commande
  * @since 1.0
  */
  public int getTimeLimit(){
    return this.timeLimit;
  }

  //TODO
  public int getOrderLinesSize(){
    return this.orderList.size();
  }

  //TODO
  public void removeLastOrderLine(){
    if (orderList.size() > 0){
      this.orderList.remove(orderList.size()-1);
    }
  }

  //TODO
  public OrderLine getOrderLine(int i){
    return this.orderList.get(i);
  }

  /*____SETTEURS____*/

  /** modifie le numéro d'identification d'une commande
  * @param id
  *           numéro d'identification
  * @since 1.0
  */
  public void setId(int id){
    this.id = id;
  }

  /** modifie l'adresse de livraison
  * @param businessUnit
  *           adresse de livraison de la commande (peut être null si pas à livrer)
  * @since 1.0
  */
  public void setBusinessUnitId(BusinessUnit businessUnit){
    this.businessUnitId=businessUnit;
  }

  /** modifie le client associé à une commande
  * @param client
  *           le client à qui appartient cette commande
  * @throws OrderException si client vaut null
  * @since 1.0
  */
  public void setClient(Client client)throws OrderException{
    if (client == null){
      throw new OrderException("Il n'y a aucun client Spécifié pour cette commande");
    }
    this.client = client;
  }

  /** modifie si la commande est prioritaire ou non
  * @param priority
  *           la commande doit elle être traitée en priorité
  * @since 1.0
  */
  public void setHasPriority(boolean priority){
    this.hasPriority = priority;
  }

  /** modifie la date de création de la commande
  * @param orderDate
  *           date à laquelle la commande à été effectuée
  * @since 1.0
  */
  public void setOrderDate(String orderDate){
    this.orderDate= orderDate;
  }

  /** modifie l'état de la commande
  * @param state
  *           etat de la commande
  * @since 1.0
  */
  public void setState(String state){ // PAID
    this.state = state;
  }

  /** modifie le nombre de jours pour traiter la commande
  * @param timeLimit
  *          nombre de jours pour traiter la commande si il est négatif, il n'y en a pas.
  * @since 1.0
  */
  public void setTimeLimit(int timeLimit){// facultatif
    this.timeLimit = timeLimit;
  }


  /*____TO STRING____*/

  /** génère une chaine de caractère décrivant la commande
  *
  * @see Client
  * @see BusinessUnit
  * @return chaine de caractère décrivant la commande
  * @since 1.2
  */
  public String toString(){
    String borders = "\n*****************************************\n"; // délimiteur entre 2 commandes
    String spacer = "\n\t=========================\n"; // Délimiteur entre 2 parties d'une commande
    String output;
    output = borders + "\t\tCommande n°" + this.getId() + spacer;
    output += this.getClient().toString()+spacer;
    if (getBusinessUnitId() != null){
      output += this.getBusinessUnitId().toString() + spacer;
    }else{
      output += "Pas d'adresse de livraison\n";
    }
    output += "Priority :" + this.getHasPriority() + spacer;
    output += "ORDER :\n";
    for(int i = 0; i<this.orderList.size();i++){
      output += "\t* " + this.orderList.get(i).toString() + "\n";
    }
    output += borders;
    return output;
  }
}
