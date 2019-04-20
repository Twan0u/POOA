package composants;
import composants.exceptions.OrderException;
import java.util.*;
/**
* <b>classe de l'objet Order</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 1.3
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

* <b>A implementer</b>
* <ul>
*   <li>Implementation des IDs</li>
*   <li>Verification des numéros d'identification</li>
*   <li>erreur de commande vide</li>
*   <li>le client null</li>
*   <li>verification sur la validité de la date</li>
*   <li>verification du state</li>
*   <li>verification du time limit</li>
* </ul>
*
*/
public class Order{
  /*____VARIABLES____*/

  /**numero d'identification de la commande*/
  private int number;
  /**adresse (facultative) de livraison*/
  private BusinessUnit businessUnitId;
  /**Client lié à cette commande*/
  private Client client;
  /**Commande prioritaire*/
  private boolean hasPriority;
  /**Date à laquelle la commande à été passée*/
  private String orderDate;
  /**Etat de la commande (limité à qq choix)*/
  private String state;
  /**limite de jours pour effectuer la commande (facultatif)*/
  private int timeLimit;
  /**Liste des éléments commandés*/
  private ArrayList<OrderLine> orderList = new ArrayList<>();

  /*____Constructeurs____*/

  /** Methode constructeur pour les objets Order
  * @param number
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
  public Order(int number, BusinessUnit businessUnitId, Client client, boolean hasPriority, String orderDate, String state, int timeLimit)throws OrderException{
    setNumber(number);
    setBusinessUnitId(businessUnitId);
    setClient(client);
    setHasPriority(hasPriority);
    setOrderDate(orderDate);
    setState(state);
    setTimeLimit(timeLimit);
  }

  /** Methode constructeur pour les objets Order
  * @param number
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
  public Order(int number, Client client, boolean hasPriority, String orderDate, String state, int timeLimit)throws OrderException{
    this(number, null, client, hasPriority, orderDate, state, timeLimit);
  }

  /** Methode constructeur pour les objets Order
  * @param number
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
  public Order(int number, BusinessUnit businessUnitId, Client client, boolean hasPriority, String orderDate, String state)throws OrderException{
    this(number, businessUnitId, client, hasPriority, orderDate, state, -1);
  }

  /*____METHODES____*/

  /** Ajoute une ligne de commande à la commande
  * @param item
  *         ligne de commande
  * @see OrderLine
  * @since 1.3
  */
  void additem(OrderLine item){ // ajouter le cas ou le tableau serait trop petit
    this.orderList.add(item);
  }

  /*____GETTEURS____*/

  public int getNumber(){
    return this.number;
  }
  public BusinessUnit getBusinessUnitId(){
    return this.businessUnitId;
  }
  public Client getClient(){
    return this.client;
  }
  public boolean getHasPriority(){
    return this.hasPriority;
  }
  public String getOrderDate(){
    return this.orderDate;
  }
  public String getState(){
    return this.state;
  }
  public int getTimeLimit(){
    return this.timeLimit;
  }

  /* SETTEURS */
  public void setNumber(int number){
    this.number = number;
  }
  public void setBusinessUnitId(BusinessUnit businessUnit){ // Si le BusinessUnit n'existe pas, alors on ne doit pas livrer
    this.businessUnitId=businessUnit;
  }
  public void setClient(Client client)throws OrderException{ // différent de null
    if (client == null){
      throw new OrderException("Il n'y a aucun client Spécifié pour cette commande");
    }
    this.client = client;
  }
  public void setHasPriority(boolean hasPriority){
    this.hasPriority = hasPriority;
  }
  public void setOrderDate(String orderDate){
    this.orderDate= orderDate;
  }
  public void setState(String state){ // PAID
    this.state = state;
  }
  public void setTimeLimit(int timeLimit){// facultatif
    this.timeLimit = timeLimit;
  }

  public String toString(){
    String borders = "\n*****************************************\n"; // délimiteur entre 2 commandes
    String spacer = "\n\t=========================\n"; // Délimiteur entre 2 parties d'une commande
    String output;
    output = borders + "\t\tCommande n°" + this.getNumber() + spacer;
    output += this.getBusinessUnitId().toString() + spacer;
    output += "Priority :" + this.getHasPriority() + spacer;
    output += "ORDER :\n";
    for(int i = 0; i<this.orderList.size();i++){
      output += "\t* " + this.orderList.get(i).toString() + "\n";
    }
    output += borders;
    return output;
  }
}
