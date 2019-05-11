package gui;

import controller.*;
import composants.exceptions.*;

import javax.swing.*;
import java.awt.event.*;

public class OrderGui extends JFrame{

  private InterfaceController controller = new Controller();

  private JPanel pannel;
  private JLabel labelClient, labelBusiness;
  private JComboBox comboBoxClient, comboBoxBusiness;

  JLabel labelClientInfo = new JLabel("");
  JLabel labelBusinessInfo = new JLabel("");

  public OrderGui() {

    super("Outil de Création de Commandes");
    setSize(600,450);
    setResizable(false);
    pannel = new JPanel();
    labelClient = new JLabel("Client : ");
    try{
      comboBoxClient = new JComboBox(controller.getClients());
      comboBoxClient.setSelectedIndex(-1);
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

    add(pannel);
    setVisible(true);

    ClientComboBoxListener listenerClient = new ClientComboBoxListener();
    comboBoxClient.addItemListener(listenerClient);
  }

  public class ClientComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        comboBoxBusiness.removeAllItems();
        int index = comboBoxClient.getSelectedIndex();
        labelClientInfo.setText("Client : " + controller.getInfoClient(index)+"\n");
        BusinessComboRefresh(index);
      }
  }
  /** Actualise le combobox contenant les adresses de livraison
  *
  * @param index
  *             index du client dans le tableau contenant tous les clients
  *
  *
  */
  private void BusinessComboRefresh(int index){
    try{
      String [] business = controller.getBusinessOf(index);
      comboBoxBusiness.addItem("Pas A livrer");
      if (business != null){
        for(int i=0;i<business.length;i++){
          comboBoxBusiness.addItem(business[i]);
        }
      }
    }catch (Exception e){ // TODO : change to ...
    }
  }

}
