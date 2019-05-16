package gui.neworderpanel;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OrderAddForm extends Container{

ControllerNewOrder controller;

private JLabel labelClient, labelBusiness, labelDays, labelDate;
private JComboBox comboBoxClient, comboBoxBusiness;
private JCheckBox checkPriority;
private JSpinner spinnerDate;
private JTextField timeLimit;

  public OrderAddForm(ControllerNewOrder controller,Color colorText){
    this.controller = controller;
    this.setLayout(new GridLayout(5,2,5,5));

    //ComboBox Client
    labelClient = new JLabel("Client : ");
    labelClient.setHorizontalAlignment(SwingConstants.RIGHT);
    labelClient.setForeground(colorText);
    comboBoxClient = new JComboBox(loadClients());
    comboBoxClient.setMaximumRowCount(5);
    comboBoxClient.setSelectedIndex(-1);

    this.add(labelClient);
    this.add(comboBoxClient);

    //ComboBox Business
    labelBusiness = new JLabel("Business : ");
    labelBusiness.setHorizontalAlignment(SwingConstants.RIGHT);
    labelBusiness.setForeground(colorText);
    comboBoxBusiness = new JComboBox();
    comboBoxBusiness.setMaximumRowCount(5);
    BusinessComboRefresh();

    this.add(labelBusiness);
    this.add(comboBoxBusiness);

    labelDate = new JLabel("Date de Livraison Prévue : ");
    labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
    labelDate.setForeground(colorText);
    spinnerDate = new JSpinner();
    this.add(labelDate);
    this.add(spinnerDate);

    labelDays = new JLabel("Livraison endéans les X jours après la date Prévue: ");
    labelDays.setHorizontalAlignment(SwingConstants.RIGHT);
    labelDays.setForeground(colorText);
    timeLimit = new JTextField("");
    this.add(labelDays);
    this.add(timeLimit);

    this.add(new JLabel("")); // grid spacer

    checkPriority = new JCheckBox("Livraison Prioritaire ?");
    //checkPriority.setBackground(colBackground);
    checkPriority.setHorizontalAlignment(SwingConstants.RIGHT);
    this.add(checkPriority);

    ClientComboBoxListener listenerClient = new ClientComboBoxListener();
    comboBoxClient.addItemListener(listenerClient);

    BusinessComboBoxListener listenerBusiness = new BusinessComboBoxListener();
    comboBoxBusiness.addItemListener(listenerBusiness);
  }

  /** Actualise le combobox contenant les adresses de livraison
  *
  * @param index
  *             index du client dans le tableau contenant tous les clients
  * @since 1.2
  */
  private void BusinessComboRefresh(){
    String [] business = null;
    try{
      business = controller.getBusiness();
    }catch(ProgramErrorException error){
        JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
    }
    comboBoxBusiness.removeAllItems();
    for(int i=0;i<business.length;i++){
      comboBoxBusiness.addItem(business[i]);
      }
  }
  private String[] loadClients(){ //TODO clients can't be null
    String [] clients = null;
      try{
        clients = controller.getClients();
      }catch(ProgramErrorException error){
          JOptionPane.showMessageDialog (null, "Erreur du chargement des Clients","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
          System.exit(1);
      }
      return clients;
  }

  public class ClientComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        try{
          controller.selectClient(comboBoxClient.getSelectedIndex());
        }catch(ProgramErrorException error){
            JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
        }
        BusinessComboRefresh();
      }
  }

  public class BusinessComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        controller.selectBusiness(comboBoxBusiness.getSelectedIndex());//l'index de la selection dans la combobox est recupéré et envoyé au
      }
  }
}
