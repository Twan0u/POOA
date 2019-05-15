package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PreparePanel extends Container{

  ControllerPrepare controller = new ControllerPrepare();

  private static String column[]={"ID-COMMANDE","Client"};

  private JPanel base;

  private JComboBox comboBoxClient;
  private JLabel labelClient;

  private JScrollPane sp;
  private JTable table;

  public PreparePanel(Color colBackground,Color colText) {
    this.setBackground(colBackground);
    this.setLayout(new FlowLayout());

    base = new JPanel();
    base.add(new JLabel("Onglet PREPARATION DE COMMANDE"));

    JButton triCommande = new JButton("Trier par Client");
    JButton triadresses = new JButton("Trier par Adresses");

    labelClient = new JLabel("Client : ");
    labelClient.setHorizontalAlignment(SwingConstants.RIGHT);
    labelClient.setForeground(colText);

    try{
      comboBoxClient = new JComboBox(controller.getClients());
    }catch(ProgramErrorException error){
      JOptionPane.showMessageDialog (null, "Erreur du chargement des Clients","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
    }
    comboBoxClient.setMaximumRowCount(5);
    comboBoxClient.setSelectedIndex(-1);

    this.add(labelClient);
    this.add(comboBoxClient);

  /*  JTable table = new JTable(controller.getAllOrders(),column);
    table.setEnabled(false);
    sp=new JScrollPane(table);
    this.add(sp);*/
  }
}
