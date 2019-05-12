/*package gui;

import controller.*;
import composants.exceptions.*;

import javax.swing.*;
import java.awt.event.*;

public class OrderJava extends JFrame{

  private InterfaceController controller = new Controller();
  private JPanel pannel;
  private JLabel labelClient, labelBusiness;
  private JComboBox comboBoxClient, comboBoxBusiness;


  JLabel labelClientInfo = new JLabel("");
  JLabel labelBusinessInfo = new JLabel("");
  BrassiGestion.java
  int clientIndex = -1;

  public Interface(){
    super("Outil de Création de Commandes");
    setSize(600,450);
    setResizable(false);
    pannel = new JPanel();

    labelClient = new JLabel("Client : ");
    try{
      comboBoxClient = new JComboBox(controller.getClients());
    }catch(ClientException e){
      JOptionPane.showMessageDialog (null, "Il y a eu une erreur dans le chargement des Clients" + e.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
    }

    labelBusiness = new JLabel("Business : ");
    comboBoxBusiness = new JComboBox();



    comboBoxBusiness.addItem("Aucun Client Selectionné");

    pannel.add(labelClient);
    pannel.add(comboBoxClient);
    pannel.add(labelBusiness);
    pannel.add(comboBoxBusiness);
    pannel.add(labelClientInfo);
    pannel.add(labelBusinessInfo);

    Object rawData[][]={{"Bûche Blonde",2,1.5},{"Bûche Ambrée",10,3.0},{"Triple Karmeliet",15,1.0}};
    String data[][] = new String[rawData.length+1][4];
    double total = 0;
    for(int i=0;i<rawData.length;i++){
      String beer = (String) rawData[i][0];
      int quantity = (int) rawData[i][1];
      double price = (double) rawData[i][2];
      data[i][0]= beer;
      data[i][1]= Integer.toString(quantity);
      data[i][2]= Double.toString(price) + "€";
      data[i][3]= Double.toString(price*quantity) + "€";
      total += price * quantity;
    }
    data[rawData.length][0] = "---";
    data[rawData.length][1] = "---";
    data[rawData.length][2] = "---";
    data[rawData.length][3] = Double.toString(total) + "€";

    String column[]={"Bière","Quantité","Prix Unit","Total"};

    JTable table=new JTable(data,column);
    table.setBounds(5,10,100,200);
    JScrollPane sp=new JScrollPane(table);
    pannel.add(sp);

    add(pannel);
    setVisible(true);

    comboBoxClient.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          clientIndex = comboBoxClient.getSelectedIndex();
          labelClientInfo.setText("Client : " + controller.getInfoClient(comboBoxClient.getSelectedIndex())+"\n");
          comboBoxBusiness.removeAllItems();
          comboBoxBusiness.addItem("Pas A livrer");
          try{
          String [] business = controller.getBusinessOf(comboBoxClient.getSelectedIndex());
          if (business != null){
            for(int i=0;i<business.length;i++){
              comboBoxBusiness.addItem(business[i]);
            }
          }
          }catch (Exception zz){}

        }
    });

  }
}*/
