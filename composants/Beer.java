package composants;
/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

import composants.exceptions.BeerException;

// verification que le nom de la bière n'existe pas déja


public class Beer{
  private static int MAX_LONG_NOM = 64;

  /*Variables d'instances*/
  private String name;
  private double stockPrice;
  private int qtInStock;
  private int lowThreshold;



  /*Methode constructeur pour les objets Client*/
  public Beer(String name, double stockPrice, int qtInStock, int lowThreshold)throws BeerException{
    setName(name);
    setStockPrice(stockPrice);
    setQtInStock(qtInStock);
    setLowThreshold(lowThreshold);
  }
  public Beer(String name, double stockPrice, int qtInStock)throws BeerException{
    this(name,stockPrice,qtInStock,0);
  }
  public Beer(String name, double stockPrice)throws BeerException{
    this(name,stockPrice,0,0);
  }

  /* GETTEURS */
  public String getName(){
    return this.name;
  }
  public double getStockPrice(){
    return this.stockPrice;
  }
  public int getQtInStock(){
    return this.qtInStock;
  }
  public int getLowThreshold(){
    return this.lowThreshold;
  }

  /* SETTEURS */
  public void setName(String name)throws BeerException{ //different de null, de "", ou pas encore existant dans la bdd
    if (name.length() <= 0){
      throw new BeerException("Nom de La Bière trop court");
    }else if(name.length() > MAX_LONG_NOM || name == null){
      throw new BeerException("Nom de La Bière TropLong");
    }else if(name == null){
      throw new BeerException("Nom de Bière Invalide");
    }
    this.name = name;
  }
  public void setStockPrice(double price)throws BeerException{ // plus grand que 0
    if (price < 0){
      throw new BeerException("Prix de la Bière Invalide");
    }
    this.stockPrice = price;
  }
  public void setQtInStock(int quantity)throws BeerException{ // minimum 0
    if (quantity < 0){
      throw new BeerException("Quantité de Bières en Stock Invalide");
    }
    this.qtInStock = quantity;
  }
  public void setLowThreshold(int min)throws BeerException{
    if (min < 0){
      throw new BeerException("Seuil d'alerte pour la Bière est invalide");
    }
    this.lowThreshold = min;
  }

  /* Utils */
  public String toString(){
    return this.getName() + " a " + this.getStockPrice() + " Stock : " + this.getQtInStock();
  }
}
