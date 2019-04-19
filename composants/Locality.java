package composants;
import composants.exceptions.LocalityException;
/**
* <b>classe de l'objet Locality</b>
*
* @author Antoine Lambert et Nathan Surquin
* @version 1.3
*
* L'objet se compose de:
* <ul>
*   <li>un numéro d'identification</li>
*   <li>un nom</li>
*   <li>un code postal</li>
* </ul>
*
* <b>Important</b>
* La taille maximum du nom est fixée avec la variable MAX_LONG_NOM
*
* <b>A implementer</b>
* <ul>
*   <li>Implementation des IDs</li>
*   <li>Verification des numéros d'identification</li>
* </ul>
*
*/
public class Locality {
  /*____VARIABLES____*/

  /**Longueur maximum du nom d'une localite*/
  private static int MAX_LONG_NOM = 64;
  /**numero d'identification*/
  private int idLocality;
  /**nom de la localite*/
  private String name;
  /**code postal de la localite*/
  private String postCode;

  /*____Constructeurs____*/

  /** Methode constructeur pour les objets Locality
  * @param id
  *             Numero d'identification.
  * @param name
  *             Nom de Localité
  * @param postCode
  *             Code Postal
  * @throws LocalityException erreur envoyée en cas de données erronnées
  * @since 1.0
  */
  public Locality(int id, String name, String postCode)throws LocalityException{
    setIdLocality(id);
    setName(name);
    setPostCode(postCode);
  }

  /** Methode constructeur pour les objets Locality avec identification automatique
  * @param name
  *             Nom de Localité
  * @param postCode
  *             Code Postal
  * @throws LocalityException erreur envoyée en cas de données erronnées
  * @since 1.0
  */
  public Locality(String name, String postCode)throws LocalityException{
    setName(name);
    setPostCode(postCode);
  }

  /*____GETTEURS____*/

  /** Getteur pour la variable idLocality
  * @return numéro d'identification de la localité
  * @since 1.0
  */
  public int getIdLocality(){
    return this.idLocality;
  }

  /** Getteur pour le nom de la localite
  * @return nom de Localité
  * @since 1.0
  */
  public String getName(){
    return this.name;
  }

  /** Getteur pour la variable postCode
  * @return code Postal de la localité
  * @since 1.0
  */
  public String getPostCode(){
    return this.postCode;
  }

  /*____SETTEURS____*/

  /** modifie le numéro d'identification d'une localite
  * @param num
  *           numéro d'identification de la localite
  * @since 1.0
  */
  private void setIdLocality(int num){
    //verification ??
    this.idLocality = num;
  }

  /** Modifie le nom de la localite
  * @param name
  *           nom de la localite
<<<<<<< HEAD
  * @throws LocalityException envoyée en cas de localite null, trop petite ou trop grande (MAX_LONG_NOM)
=======
  * @throws LocaliteException envoyée en cas de localite null, trop petite ou trop grande (MAX_LONG_NOM)
>>>>>>> 6f3abd066633e9b99d75e30dd1a52104a7c1a9e3
  * @since 1.0
  */
  public void setName(String name)throws LocalityException{
    //max caractères
    if (name.length() <= 0 || name.length() > MAX_LONG_NOM || name == null){
      throw new LocalityException("Nom de Localité Invalide");
    }
    this.name = name;
  }

  /** Modifie le code postal de la localite
  * @param postCode
  *           code postal de la localite
<<<<<<< HEAD
  * @throws LocalityException envoyé si le code postal n'est pas un nombre entier
=======
  * @throws LocaliteException envoyé si le code postal n'est pas un nombre entier
>>>>>>> 6f3abd066633e9b99d75e30dd1a52104a7c1a9e3
  * @since 1.0
  */
  public void setPostCode(String postCode)throws LocalityException{
    try {
      int temp = Integer.parseInt(postCode);
    }catch (NumberFormatException err){
      throw new LocalityException("Code Postal de Localité Invalide");
    }
    this.postCode = postCode;
  }

  /*____TO STRING____*/

  /** génère une chaine de caractère décrivant l'objet Locality
  *  sous la forme "ID - NAME - POSTCODE"
  * @see Locality#getIdLocality
  * @see Locality#getName
  * @see Locality#getPostCode
  * @return chaine de caractère décrivant la localite
  * @since 1.2
  */
  public String toString(){
    return getIdLocality() + " - " + getName() + " - " + getPostCode();
  }
}
