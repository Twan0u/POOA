package gui;

import controller.*;
import exceptions.*;
import composants.*;

import java.io.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModifyPanel extends Container{

  private Controller controller;

  private int currentClientid;
  ArrayList<Client> allClients;
  ArrayList<BusinessUnit> allBusinessOfClient;

  private JTextField IdOrder;
  private JButton recherche, sauvegarder, supprimer;
  private Order order;
  private JPanel panel,topPanel;

  private JLabel labelClient, labelBusiness, labelDays, labelDate;
  private JComboBox comboBoxClient, comboBoxBusiness;
  private JCheckBox checkPriority;
  private JSpinner spinnerDate;
  private JTextField timeLimit;

  public ModifyPanel(Order order)throws ProgramErrorException{
    this.controller = new Controller();
    this.order = order;

    this.setLayout(new FlowLayout());

    panel = new JPanel();
    panel.setLayout(new GridLayout(8,2,5,5));
    // Client
    labelClient = new JLabel("Client : ");
    labelClient.setHorizontalAlignment(SwingConstants.RIGHT);

    comboBoxClient = new JComboBox();
    int indexComboClient = loadClientCombo();
    comboBoxClient.setMaximumRowCount(5);
    comboBoxClient.setSelectedIndex(indexComboClient);

    panel.add(labelClient);
    panel.add(comboBoxClient);

    //Business
    labelBusiness = new JLabel("Business : ");
    labelBusiness.setHorizontalAlignment(SwingConstants.RIGHT);

    comboBoxBusiness = new JComboBox();
    int indexComboBusiness = loadBusinessCombo();
    comboBoxBusiness.setMaximumRowCount(5);
    comboBoxBusiness.setSelectedIndex(indexComboBusiness);

    panel.add(labelBusiness);
    panel.add(comboBoxBusiness);

    labelDate = new JLabel("Date de Livraison Prévue : ");
    labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
    spinnerDate = new JSpinner();
    panel.add(labelDate);
    panel.add(spinnerDate);

    labelDays = new JLabel("Livraison endéans les X jours après la date Prévue: ");
    labelDays.setHorizontalAlignment(SwingConstants.RIGHT);
    timeLimit = new JTextField("");
    panel.add(labelDays);
    panel.add(timeLimit);

     JRadioButtonMenuItem neww, prepared,delivered,paid;
     ButtonGroup radioButtonGroup;
     neww = new JRadioButtonMenuItem("Nouvelle Commande");
     panel.add(neww);
     prepared = new JRadioButtonMenuItem("Commande préparée");
     panel.add(prepared);
     delivered = new JRadioButtonMenuItem("Commande Livrée");
     panel.add(delivered);
     paid = new JRadioButtonMenuItem("Commande Payée");
     panel.add(paid);

     radioButtonGroup = new ButtonGroup();
     radioButtonGroup.add(neww);
     radioButtonGroup.add(prepared);
     radioButtonGroup.add(delivered);
     radioButtonGroup.add(paid);

    panel.add(new JLabel("")); // grid spacer

    checkPriority = new JCheckBox("Livraison Prioritaire ?");
    checkPriority.setHorizontalAlignment(SwingConstants.RIGHT);
    panel.add(checkPriority);
    sauvegarder = new JButton("sauvegarder les modification de la commande");
    panel.add(sauvegarder);

    supprimer = new JButton("supprimer la commande");
    supprimer.setBackground(Color.RED);
    supprimer.setForeground(Color.WHITE);
    panel.add(supprimer);

    this.add(panel);

    ClientComboBoxListener listenerClient = new ClientComboBoxListener();
    comboBoxClient.addItemListener(listenerClient);
  }

  public int loadClientCombo(){ // return int est l'index ou se situe le client dans la combobox
    int idclient = order.getClient().getId();
    currentClientid = idclient;
    int indexInCombo = -1;
    try{
      allClients = controller.getAllClients();
      if (allClients !=null){
        for(int i=0;i<allClients.size();i++){
          Client current = allClients.get(i);
          if (idclient == current.getId()){
            indexInCombo = i;
          }
          comboBoxClient.addItem(current.getName() + "-" + current.getId());
        }
      }else {
        //TODO print message clients not found or no clients
      }
    }catch(Exception ignore){
      //TODO
    }
        return indexInCombo;
  }

  public int loadBusinessCombo(){ // return int est l'index ou se situe le business dans la combobox

    BusinessUnit selectedBusiness = order.getBusinessUnitId();
    int idBusiness = -1;
    int indexInCombo = 0;
    if (selectedBusiness != null){
      idBusiness = selectedBusiness.getIdBusinessUnit();
    }else{//il n'y a pas de livraison à effectuer pour cet order}
    }
    try{
      allBusinessOfClient = controller.getBusinessOf(currentClientid);
      comboBoxBusiness.addItem("Pas de Livraison");
      if (allBusinessOfClient !=null){
        for(int i=0;i<allBusinessOfClient.size();i++){
          BusinessUnit current = allBusinessOfClient.get(i);
          if (idBusiness == current.getIdBusinessUnit()){
            indexInCombo = i+1;
          }
          comboBoxBusiness.addItem(current.getStreetName());
        }
        }else {
        comboBoxBusiness.setEnabled(false);
        //TODO print message clients not found or no clients or no orders
      }
    }catch(Exception ignore){
      //TODO
    }
        return indexInCombo;
  }

  public class ClientComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        try{
          int idClient = allClients.get(comboBoxClient.getSelectedIndex()).getId();
          allBusinessOfClient = controller.getBusinessOf(idClient);
          comboBoxBusiness.removeAllItems();
          comboBoxBusiness.addItem("Pas de Livraison");
          comboBoxBusiness.setSelectedIndex(0);
          if (allBusinessOfClient.size() == 0){
            comboBoxBusiness.setEnabled(false);
          }else{
            for(int i=0;i<allBusinessOfClient.size();i++){
              comboBoxBusiness.addItem(allBusinessOfClient.get(i).getStreetName());
            }
          comboBoxBusiness.setEnabled(true);
        }
        }catch(ProgramErrorException error){
            JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
        }
      }
  }

}
