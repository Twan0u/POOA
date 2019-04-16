/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

import javax.swing.*;
import composants.BusinessUnit;

public class Interface extends JFrame{
  JPanel pannel = new JPanel();

  JButton b;
  JLabel l = new JLabel("yo bru");
  JComboBox cb;

  /* TO TEST ONLY*/
  public static void main(String[] args){
    //new Interface("1","2");
  }

  /* Creation d'une interface basique */
  public Interface(String titre, String bouton, BusinessUnit[] tabs, int nbbu){
    super(titre);
    setSize(400,300);
    setResizable(true);

    String [] businesss = new String[nbbu]; //tabs. EACH TO STRING;
    for(int i = 0; i<nbbu; i++){
      businesss[i] = tabs[i].getStreetName();
    }

    b = new JButton(bouton);
    cb = new JComboBox(businesss);
    pannel.add(b);
    pannel.add(l);
    pannel.add(cb);


    add(pannel);
    setVisible(true);
    System.out.println("Fin Interface");
  }
}
