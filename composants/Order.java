package composants;
/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

import composants.exceptions.OrderException;

// Doit on obligatoirement avoir un BusinessUnit ??

// erreur commande vide
// le client n'existe pas
// VERIFICATIONS SUR LA DATE
// Gestion des IDS
// VERification State
// Verification TimeLimit

public class Order{

  private static int MAX_ITEMS_PER_ORDER = 100;

  /*Variables d'instances*/
  private int number;
  private BusinessUnit businessUnitId; //facultatif
  private Client client;
  private boolean hasPriority;
  private String orderDate;
  private String state;
  private int timeLimit; // (facultatif) temps maximum pour faire une commande

  /*Variables Liés au programme JAVA*/

  private OrderLine [] items;// ???????
  private int nbItems;


/*Methode constructeur pour les objets Client*/
  public Order(int number, BusinessUnit businessUnitId, Client client, boolean hasPriority, String orderDate, String state, int timeLimit)throws OrderException{
    setNumber(number);
    setBusinessUnitId(businessUnitId);
    setClient(client);
    setHasPriority(hasPriority);
    setOrderDate(orderDate);
    setState(state);
    setTimeLimit(timeLimit);
    items = new OrderLine[MAX_ITEMS_PER_ORDER];
    nbItems = 0;
  }

  public Order(int number, Client client, boolean hasPriority, String orderDate, String state, int timeLimit)throws OrderException{
    this(number, null, client, hasPriority, orderDate, state, timeLimit);
  }

  public Order(int number, BusinessUnit businessUnitId, Client client, boolean hasPriority, String orderDate, String state)throws OrderException{
    this(number, businessUnitId, client, hasPriority, orderDate, state, -1);
  }

  public Order(int number, Client client, boolean hasPriority, String orderDate, String state)throws OrderException{
    this(number, null, client, hasPriority, orderDate, state, -1);
  }

  void additem(OrderLine item){ // ajouter le cas ou le tableau serait trop petit
    this.items[this.nbItems] = item;
    this.nbItems++;
  }


  /* GETTEURS */
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
  public void setNumber(int number){//?? on doit set nous meme la primary key
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
    for(int i = 0; i<this.nbItems;i++){
      output += "\t* " + this.items[i].toString() + "\n";
    }
    output += borders;
    return output;
  }
}
