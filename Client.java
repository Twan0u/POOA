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
  public Client(int number, String name, String phoneNumber, double discount, String VATNumber){
    setNumber(number);
    setName(name);
    setPhoneNumber(phoneNumber);
    setDiscount(discount);
    setVATNumber(VATNumber);
  }
  public Client(int number, String name, String phoneNumber, double discount){
    client(number,name,phoneNumber,discount,null);
  }
  public Client(int number, String name, String phoneNumber){
    client(number,name,phoneNumber,0,null);
  }

  /* GETTEURS */
  public getNumber(){
    return this.number;
  }
  public getName(){
    return this.name;
  }
  public getPhoneNumber(){
    return this.phoneNumber;
  }
  public getDiscount(){
    return this.discount;
  }
  public getVATNumber(){
    return this.VATNumber;
  }


  /* SETTEURS */
  private setNumber(int num){
    // Verification et incrémentation ????
  }
  public setName(String name){
    //verification ( taille maximum )
    this.name = name;
  }
  public setPhoneNumber(String phone){
    // Verification
    this.phoneNumber = phone;
  }
  public setDiscount(double discount){
    // VERIFIER
    this.discount = discount;
  }
  public setVATNumber(String vatNum){
    //VERIFIER
    this.VATNumber = vatNum;
  }

}
