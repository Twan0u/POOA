package gui.neworderpanel;

import controller.*;
import exceptions.*;
import composants.*;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*; //pour le date format

public class OrderAddForm extends Container{

ControllerNewOrder controller;
ArrayList<Client> allClients;
ArrayList<BusinessUnit> business;

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

    comboBoxClient = new JComboBox();
    loadClientCombo();
    comboBoxClient.setMaximumRowCount(5);
    comboBoxClient.setSelectedIndex(-1);

    this.add(labelClient);
    this.add(comboBoxClient);

    //ComboBox Business
    labelBusiness = new JLabel("Business : ");
    labelBusiness.setHorizontalAlignment(SwingConstants.RIGHT);
    labelBusiness.setForeground(colorText);

    String [] initBoxBusiness = {"aucun Client Sélectionné"};
    comboBoxBusiness = new JComboBox(initBoxBusiness);
    comboBoxBusiness.setEnabled(false);
    comboBoxBusiness.setMaximumRowCount(5);

    this.add(labelBusiness);
    this.add(comboBoxBusiness);

    labelDate = new JLabel("Date de Livraison Souhaitée : ");
    labelDate.setHorizontalAlignment(SwingConstants.RIGHT);
    labelDate.setForeground(colorText);

    SpinnerDateModel model = new SpinnerDateModel();
    JSpinner spinnerDate = new JSpinner(model);

    JSpinner.DateEditor editor = new JSpinner.DateEditor(spinnerDate, "dd-MM-yyyy");
    spinnerDate.setEditor(editor);

    this.add(labelDate);
    this.add(spinnerDate);

    labelDays = new JLabel("Livraison maximum ... jours après la date Souhaitée (facultatif) : ");
    labelDays.setHorizontalAlignment(SwingConstants.RIGHT);
    labelDays.setForeground(colorText);
    timeLimit = new JTextField("0");
    this.add(labelDays);
    this.add(timeLimit);

    this.add(new JLabel("")); // grid spacer

    checkPriority = new JCheckBox("Livraison Prioritaire ?");
    //checkPriority.setBackground(colBackground);
    checkPriority.setHorizontalAlignment(SwingConstants.RIGHT);
    this.add(checkPriority);

    ClientComboBoxListener listenerClient = new ClientComboBoxListener();
    comboBoxClient.addItemListener(listenerClient);
  }

  public void loadClientCombo(){
    try{
      Controller controller = new Controller();
      allClients = controller.getAllClients();
      if (allClients !=null){
        for(int i=0;i<allClients.size();i++){
          Client current = allClients.get(i);
          comboBoxClient.addItem(current.getName() + "-" + current.getId());
        }
      }else {
        //TODO print message clients not found or no clients
      }
    }catch(ProgramErrorException error){
        JOptionPane.showMessageDialog (null, "Erreur du chargement des Clients","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }catch(Exception ignore){
      //TODO
    }
  }

  public class ClientComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        try{
        Controller controller = new Controller();
        int idClient = allClients.get(comboBoxClient.getSelectedIndex()).getId();
         business = controller.getBusinessOf(idClient);
          comboBoxBusiness.removeAllItems();
          comboBoxBusiness.addItem("Pas de Livraison");
          if (business.size() == 0){
            comboBoxBusiness.setEnabled(false);
          }else{
            for(int i=0;i<business.size();i++){
              comboBoxBusiness.addItem(business.get(i).getStreetName());
            }
          comboBoxBusiness.setEnabled(true);
        }
        }catch(ProgramErrorException error){
            JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
        }
      }
  }
  public Client getSelectedClient(){
    int index = comboBoxClient.getSelectedIndex();
    return allClients.get(index);
  }

  public BusinessUnit getSelectedBusiness(){
    int index = comboBoxBusiness.getSelectedIndex();
    if (index == 0){
      return null;
    }else{
      return business.get(index-1);
    }
  }
  public String getSelectedDate(){
    return "16-09-1997";//TODO
  }
  public String getSelectedTimeLimit(){
    return timeLimit.getText();
  }
  public boolean getSelectedPriority(){
    return checkPriority.isSelected();
  }
}
