/*
Programme de Gestion d'une brasserie
écrit par Antoine Lambert et Nathan Surquin
dans le cadre du cours de Programation avancée Orientée Objets
*/

import javax.swing.*;
import java.awt.event.*;


public class Interface extends JFrame{
  JPanel pannel = new JPanel();

  JLabel l;
  JLabel l2;
  JComboBox cb;
  JComboBox bubusiness;

  /* TO TEST ONLY*/
  public static void main(String[] args){
    //new Interface("1","2");
  }

  /* Creation d'une interface basique */
  public Interface(){
    super("Outil de Création de Commandes");
    setSize(600,450);
    setResizable(true);

    l= new JLabel("Client : ");
    cb = new JComboBox(BrassiGestion.getClients());

    l2= new JLabel("\n Business : ");

    bubusiness = new JComboBox();
    bubusiness.addItem("Aucun Client Selectionné");

    pannel.add(l);
    pannel.add(cb);

    pannel.add(l2);
    pannel.add(bubusiness);

    pannel.add(labelClient);
    pannel.add(labelLivraison);


    Object rawData[][]={{"Bûche Blonde",2,1.5},{"Bûche Ambrée",10,3.0},{"Triple Karmeliet",15,1.0}};
    String data[][] = new String[rawData.length+1][4];
    double total = 0;
    for(int i=0;i<rawData.length;i++){
      String beer = (String) rawData[i][0];
      int quantity = (int) rawData[i][1];
      double price = (double) rawData[i][2];

      data[i][0]= beer;
      data[i][1]= Integer.toString(quantity);
      data[i][2]= Double.toString(price);
      data[i][3]= Double.toString(price*quantity);
      total += price * quantity;
    }
    data[rawData.length][0] = "---";
    data[rawData.length][1] = "---";
    data[rawData.length][2] = "---";
    data[rawData.length][3] = Double.toString(total);


    String column[]={"Bière","Quantité","Prix Unit","Total"};


    JTable table=new JTable(data,column);
    table.setBounds(5,10,100,200);
    JScrollPane sp=new JScrollPane(table);
    pannel.add(sp);

    add(pannel);
    setVisible(true);

    cb.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          bubusiness.removeAllItems();
          bubusiness.addItem("Pas A livrer");
          String [] business = BrassiGestion.getBusinessOfClient(cb.getSelectedIndex());
          if (business != null){
            for(int i=0;i<business.length;i++){
              bubusiness.addItem(business[i]);
            }
          }
        }
    });

  }
}
