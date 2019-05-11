package composants;
import composants.exceptions.BeerException;

/**
* <b>classe de l'objet bière</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 1.1
*
* L'objet se compose de:
* <ul>
* <li>un nom</li>
* <li>un prix de stock</li>
* <li>une quantité en stock</li>
* <li>une quantité minimale d'alerte</li>
* </ul>
*
* <b>Important</b>
* La taille maximum du nom d'une bière est fixée avec la variable MAX_LONG_NOM
*
*/


public class Beer{
/*____VARIABLES____*/

  /**Longueur maximum du nom d'une bière avant erreur*/
  private static int MAX_LONG_NOM = 64;
  /**Nom de la bière*/
  private String name;
  /**Prix de la bière*/
  private double stockPrice;
  /**Quantitié en stock*/
  private int qtInStock;
  /**quantité minimale en dessous de laquelle on alerte du faible stock*/
  private int lowThreshold;

/*____Constructeurs____*/

  /** Methode constructeur pour les objets Client
  * @param name
  *             nom de la bière.
  * @param stockPrice
  *             Prix de la bière.
  * @param qtInStock
  *             quantité en stock.
  * @param lowThreshold
  *             quantité minimale de stock avant alerte.
  * @throws BeerException erreur envoyée en cas de données erronnées
  * @since 1.0
  */
  public Beer(String name, double stockPrice, int qtInStock, int lowThreshold)throws BeerException{
    setName(name);
    setStockPrice(stockPrice);
    setQtInStock(qtInStock);
    setLowThreshold(lowThreshold);
  }

  /** Methode constructeur pour les objets Client
  * @param name
  *             nom de la bière.
  * @param stockPrice
  *             Prix de la bière.
  * @param qtInStock
  *             quantité en stock.
  * @throws BeerException erreur envoyée en cas de données erronnées
  * @since 1.0
  */
  public Beer(String name, double stockPrice, int qtInStock)throws BeerException{
    this(name,stockPrice,qtInStock,0);
  }

  /** Methode constructeur pour les objets Client
  * @param name
  *             nom de la bière.
  * @param stockPrice
  *             Prix de la bière.
  * @throws BeerException erreur envoyée en cas de données erronnées
  * @since 1.0
  */
  public Beer(String name, double stockPrice)throws BeerException{
    this(name,stockPrice,0,0);
  }

  /*____GETTEURS____*/

  /** Getteur pour la variable Name
  * @return le nom de la bière
  * @since 1.0
  */
  public String getName(){
    return this.name;
  }

  /** Getteur pour la variable stockPrice
  * @return le prix de la bière
  * @since 1.0
  */
  public double getStockPrice(){
    return this.stockPrice;
  }
  /** Getteur pour la variable qtInStock
  * @return la quantité restante en stock
  * @since 1.0
  */
  public int getQtInStock(){
    return this.qtInStock;
  }

  /** Getteur pour la variable lowThreshold
  * @return le minimum d'alerte de quantité pour la bière
  * @since 1.0
  */
  public int getLowThreshold(){
    return this.lowThreshold;
  }

  /*____SETTEURS____*/

  /** modifie le nom d'une bière
  * @param name
  *           nom de la bière (non null, pas de taille nulle et pas trop long (MAX_LONG_NOM))
  * @throws BeerException envoyée en cas de nom invalide
  * @since 1.1
  */
  public void setName(String name)throws BeerException{
    if (name.length() <= 0){
      throw new BeerException("Nom de La Bière trop court");
    }else if(name.length() > MAX_LONG_NOM){
      throw new BeerException("Nom de La Bière TropLong");
    }else if(name == null){
      throw new BeerException("Nom de Bière Invalide");
    }
    this.name = name;
  }

  /** modifie le prix d'une bière
  * @param price
  *           prix de la bière, ne doit pas être négatif
  * @throws BeerException envoyée en cas de prix invalide
  * @since 1.1
  */
  public void setStockPrice(double price)throws BeerException{ // plus grand que 0
    if (price < 0){
      throw new BeerException("Prix de la Bière Invalide");
    }
    this.stockPrice = price;
  }

  /** modifie la quantité en stock d'une bière
  * @param quantity
  *           quantité de la bière en stock, ne doit pas être négatif
  * @throws BeerException envoyée en cas de quantité invalide
  * @since 1.1
  */
  public void setQtInStock(int quantity)throws BeerException{ // minimum 0
    if (quantity < 0){
      throw new BeerException("Quantité de Bières en Stock Invalide");
    }
    this.qtInStock = quantity;
  }

  /** modifie le seuil d'alerte d'une bière
  * @param min
  *           minimim de stock en dessous de quoi il y aura une alerte
  * @throws BeerException envoyée en cas de quantité invalide
  * @since 1.1
  */
  public void setLowThreshold(int min)throws BeerException{
    if (min < 0){
      throw new BeerException("Seuil d'alerte pour la Bière est invalide");
    }
    this.lowThreshold = min;
  }

  /*____TO STRING____*/

  /** génère une chaine de caractère décrivant l'objet Beer
  *  sous la forme "NOM a PRIX Stock : STOCK"
  * @return chaine de caractère décrivant la bière
  * @see Beer#getName
  * @see Beer#getStockPrice
  * @see Beer#getQtInStock
  * @since 1.2
  */
  public String toString(){
    return this.getName() + " a " + this.getStockPrice() + " Stock : " + this.getQtInStock();
  }
}
