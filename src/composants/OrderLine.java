package composants;
import exceptions.OrderLineException;
import exceptions.OrderException;
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
  private Beer beer;
  private Order order;
  private int quantity;
  private double price;


/*Methode constructeur pour les objets Client*/
  public OrderLine(Beer beer,Order order, int quantity, double price)throws OrderLineException {
    setPrice(price);
    setQuantity(quantity); // doit impérativement être set avant de setOrder
    setBeer(beer);
    setOrder(order);

  }
  public OrderLine(Beer beer,Order order, int quantity)throws OrderLineException{
    this(beer,order,quantity,beer.getStockPrice());
  }

  /* GETTEURS */
  public Beer getBeer(){
    return this.beer;
  }
  public Order getOrder(){
    return this.order;
  }
  public int getQuantity(){
    return this.quantity;
  }
  public double getPrice(){
    return this.price;
  }

  /* SETTEURS */
  public void setBeer(Beer beer)throws OrderLineException{
    if (beer == null){
      throw new OrderLineException("Il n'y a aucune bière dans cette ligne de commande");
    }
    this.beer = beer;
  }
  public void setOrder(Order order)throws OrderLineException{
    if (order == null){
      throw new OrderLineException("Pas d'Order rentrée en paramètre");
    }
    this.order = order;
    try{
      order.additem(this);
    }catch(OrderException ex){
      throw new OrderLineException("Impossible d'ajouter la ligne dans la commande");
    }
  }
  public void setQuantity(int quantity)throws OrderLineException{
    if (quantity <= 0){
      throw new OrderLineException("Quantité invalide ou nulle");
    }
    this.quantity = quantity;
  }
  public void setPrice(double price)throws OrderLineException{
    this.price = price;
  }

  public String toString(){
    return this.getBeer().getName() + " - " + this.getQuantity() + " a " + this.getPrice();
  }

}
