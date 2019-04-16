package composants;
/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

// Beer != null
// Order != null
// Quantitées non nulles et >0
// prix >=0

public class OrderLine{
  /*Variables d'instances*/
  private Beer beerName;
  private Order order;
  private int quantity;
  private double price;


/*Methode constructeur pour les objets Client*/
  public OrderLine(Beer beerName,Order order, int quantity, double price){
    setBeerName(beerName);
    setOrder(order);
    setQuantity(quantity);
    setPrice(price);
  }
  public OrderLine(Beer beerName,Order order, int quantity){
    this(beerName,order,quantity,beerName.getStockPrice());
  }

  /* GETTEURS */
  public Beer getBeerName(){
    return this.beerName;
  }
  public Order getOrder(){
    return this.order;
  }
  public int  getQuantity(){
    return this.quantity;
  }
  public double getPrice(){
    return this.price;
  }

  /* SETTEURS */
  public void setBeerName(Beer beerName){
    this.beerName = beerName;
  }
  public void setOrder(Order order){
    this.order = order;
    order.additem(this);
  }
  public void setQuantity(int quantity){
    this.quantity = quantity;
  }
  public void setPrice(double price){
    this.price = price;
  }


  public String toString(){
    return this.beerName.getName() + " - " + this.getQuantity() + " a " + this.getPrice();
  }

}
