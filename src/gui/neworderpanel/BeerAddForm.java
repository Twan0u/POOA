package gui.neworderpanel;

import controller.*;
import composants.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.*;

public class BeerAddForm extends Container{

private Controller controller;

private JLabel labelBeer, labelQuantity;
private JComboBox comboBoxBeer;
private JSpinner spinnerQuantity;
private JButton addBeerButton, removeBeerButton;

private ArrayList<Beer> beers = null;
private ArrayList<OrderLine> orderLines = new ArrayList<>();

private BeerTable table;

  public BeerAddForm(Controller controller, BeerTable table){
    this.table = table;
    this.controller = controller;

    this.setLayout(new GridLayout(3,3,5,5));

    labelBeer = new JLabel("Ajouter à la commande: ");
    labelBeer.setHorizontalAlignment(SwingConstants.RIGHT);
    comboBoxBeer = new JComboBox(loadBeers());
    comboBoxBeer.setMaximumRowCount(5);
    this.add(labelBeer);
    this.add(comboBoxBeer);

    labelQuantity = new JLabel("Quantité: ");
    labelQuantity.setHorizontalAlignment(SwingConstants.RIGHT);
    spinnerQuantity = new JSpinner(new SpinnerNumberModel(1,1,100000,1));
    JFormattedTextField tf1 = ((JSpinner.DefaultEditor) spinnerQuantity.getEditor()).getTextField();
    tf1.setEditable(false);
    addBeerButton = new JButton("Ajouter à la commande");
    this.add(labelQuantity);
    this.add(spinnerQuantity);
    this.add(addBeerButton);

    removeBeerButton = new JButton("Supprimmer la dernière ligne de la commande");
    //removeBeerButton.setBackground(colBis);
    this.add(removeBeerButton);

    ButtonAddListener listenerAddBeer = new ButtonAddListener();
    addBeerButton.addActionListener(listenerAddBeer);

    ButtonRemoveListener listenerRemoveBeer = new ButtonRemoveListener();
    removeBeerButton.addActionListener(listenerRemoveBeer);
  }
  public ArrayList<OrderLine> getOrderLines(){
    return this.orderLines;
  }

    private String[] loadBeers(){
        try{
          beers = controller.getBeers();
        }catch(ProgramErrorException error){
            JOptionPane.showMessageDialog (null, "Erreur du chargement des bières","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        String [] beerTab = new String[beers.size()];
        for(int i=0;i<beerTab.length;i++){
          beerTab[i] = beers.get(i).getName();
        }
        return beerTab;
    }

      private class ButtonAddListener implements ActionListener{
        public void actionPerformed( ActionEvent event) {
          int indexBeer = comboBoxBeer.getSelectedIndex();
          Beer addedBeer = beers.get(indexBeer);
          int quantity = (int)spinnerQuantity.getValue();

          try{
            for(int i=0; i<orderLines.size();i++){
              if(orderLines.get(i).getBeer().compareTo(addedBeer)){
                orderLines.get(i).addQuantity(quantity);
                refreshTable();
                return;
              }
            }
            orderLines.add(new OrderLine(addedBeer,quantity));
          }catch(OrderLineException error){
              JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
          }
          refreshTable();
        }
      }
      public void refreshTable(){
        String[][] data = new String[orderLines.size()+1][4];
        double total = 0;
        for(int i=0;i<data.length-1;i++){
          OrderLine current = orderLines.get(i);
          int qt= current.getQuantity();
          double price = current.getPrice();
          data[i][0] = current.getBeer().getName();//beer getName
          data[i][1] = Integer.toString(qt);//quantité
          data[i][2] = Double.toString(price);//prix unitaire
          data[i][3] = Double.toString(qt*price); // total
          total += qt*price;
        }
        data[orderLines.size()][0] = "---";
        data[orderLines.size()][1] = "---";
        data[orderLines.size()][2] = "---";
        data[orderLines.size()][3] = Double.toString(total) + "€";
        table.refreshTable(data);
      }

      private class ButtonRemoveListener implements ActionListener{
        public void actionPerformed( ActionEvent event) {
          if(JOptionPane.showConfirmDialog (null, "êtes-vous sur de vouloir supprimmer la dernière bière de la commande? ","Warning",JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            orderLines.remove(orderLines.size()-1);
            refreshTable();
          }
        }
      }
}
