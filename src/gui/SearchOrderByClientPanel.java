package gui;

import controller.*;
import exceptions.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchOrderByClientPanel extends Container{

  private static String column[]={"ID-COMMANDE","Client"};

  private JComboBox comboBoxClient;

  private JScrollPane sp;
  private JTable table;

  public SearchOrderByClientPanel() {

    this.setLayout(new BorderLayout());
    JPanel topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout());

    //try{
      String [] TODO = {"Client 1","Client 2"};
      comboBoxClient = new JComboBox(TODO);//TODO
    //}catch(ProgramErrorException error){
    //  JOptionPane.showMessageDialog (null, "Erreur du chargement des Localités","FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
    //}
    comboBoxClient.setMaximumRowCount(5);
    comboBoxClient.setSelectedIndex(-1);

    topPanel.add(comboBoxClient);
    this.add(topPanel,BorderLayout.NORTH);

    //try{
      String [][] data ={{"1","az"},{"2","er"}} ;
        JTable table = new JTable(data,column);
        table.setEnabled(false);
        sp=new JScrollPane(table);
        this.add(sp,BorderLayout.CENTER);
    //}catch(ProgramErrorException error){
    //  JOptionPane.showMessageDialog (null, error.getMessage(),"FATAL_ERROR", JOptionPane.ERROR_MESSAGE);
    //}

    /*LocalityComboBoxListener listener = new LocalityComboBoxListener();
    comboBoxLocality.addItemListener(listener);*/
  }
  /*public class LocalityComboBoxListener implements ItemListener {
      public void itemStateChanged(ItemEvent event){
        try{
          System.out.println("table new");
          if (0==1){throw new ProgramErrorException("");};
        }catch(ProgramErrorException error){
            JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
        }
        //TODO refresh Table
      }
  }*/

}
