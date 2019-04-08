/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

public class Client {
  /*Variables d'instances*/
  static int counterNumber; // A VERIFIER
  private int number;
  private String name;
  private String phoneNumber;
  private double discount;
  private String VATNumber; // Facultatif



/*Methode constructeur pour les objets Client*/
  public Client(int number, String name, String phoneNumber){
    setNumber(number);
    setName(name);
    setPhoneNumber(phoneNumber);
    setDiscount(0);
    setVATNumber(null);
  }
  public Client(int number, String name, String phoneNumber, double discount){
    setNumber(number);
    setName(name);
    setPhoneNumber(phoneNumber);
    setDiscount(discount);
    setVATNumber(null);
  }
  public Client(int number, String name, String phoneNumber, double discount, String VATNumber){
    setNumber(number);
    setName(name);
    setPhoneNumber(phoneNumber);
    setDiscount(discount);
    setVATNumber(VATNumber);
  }



  /* GETTEURS */
  public int getNumber(){
    return this.number;
  }
  public String getName(){
    return this.name;
  }
  public String getPhoneNumber(){
    return this.phoneNumber;
  }
  public double getDiscount(){
    return this.discount;
  }
  public String getVATNumber(){
    return this.VATNumber;
  }


  /* SETTEURS */
  private void setNumber(int num){
    // Verification et incrémentation ????
  }
  public void setName(String name){
    //verification ( taille maximum )
    this.name = name;
  }
  public void setPhoneNumber(String phone){
    // Verification
    this.phoneNumber = phone;
  }
  public void setDiscount(double discount){
    // VERIFIER
    this.discount = discount;
  }
  public void setVATNumber(String vatNum){
    //VERIFIER
    this.VATNumber = vatNum;
  }

  /* Utils */

  public String toString(){
    return getNumber() + " - " + getName() + " - " + getPhoneNumber() + " - " + getDiscount() + "% - " + getVATNumber();
  }

}
