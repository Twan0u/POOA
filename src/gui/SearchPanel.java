package gui;

import controller.*;
import exceptions.*;
import composants.*;
import gui.neworderpanel.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;


public class SearchPanel extends JPanel {

    public ArrayList<Order> orders;

    private Controller controller = new Controller();

    private JLabel dateMinLabel;
    private JLabel dateMaxLabel;
    private JLabel orderStateLabel;

    private JSpinner dateMin;
    private JSpinner dateMax;

    private ButtonGroup buttonGroup;
    private JRadioButton newO;
    private JRadioButton preparedO;
    private JRadioButton deliveredO;
    private JRadioButton paidO;
    private JRadioButton anyState;

    private JButton search;

    private String column[]={"ID","Client","Adresse","Date","Etat"};

    public SearchPanel(){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(600, 600));

        SpinnerDateModel model1 = new SpinnerDateModel();
        dateMinLabel = new JLabel("Date de livraison minimum");
        this.add(dateMinLabel);
        dateMin = new JSpinner(model1);
        dateMin.setEditor(new JSpinner.DateEditor(dateMin, "yyyy-MM-dd"));
        JFormattedTextField tf1 = ((JSpinner.DefaultEditor) dateMin.getEditor()).getTextField();
        tf1.setEditable(false);
        this.add(dateMin);

        SpinnerDateModel model2 = new SpinnerDateModel();
        dateMaxLabel = new JLabel("Date de livraison max");
        this.add(dateMaxLabel);
        dateMax = new JSpinner(model2);
        dateMax.setEditor(new JSpinner.DateEditor(dateMax, "yyyy-MM-dd"));
        JFormattedTextField tf2 = ((JSpinner.DefaultEditor) dateMax.getEditor()).getTextField();
        tf2.setEditable(false);
        this.add(dateMax);

        orderStateLabel = new JLabel("Etat de la commande");
        buttonGroup = new ButtonGroup();
        newO = new JRadioButton("Nouvelle", true);
        preparedO = new JRadioButton("Préparée", false);
        deliveredO = new JRadioButton("Livrée", false);
        paidO = new JRadioButton("Payée", false);
        anyState = new JRadioButton("N'importe quel état", false);
        buttonGroup.add(newO);
        buttonGroup.add(preparedO);
        buttonGroup.add(deliveredO);
        buttonGroup.add(paidO);
        buttonGroup.add(anyState);
        this.add(orderStateLabel);
        this.add(newO);
        this.add(preparedO);
        this.add(deliveredO);
        this.add(paidO);
        this.add(anyState);

        search = new JButton("Rechercher les commandes correspondant aux critères");
        search.addActionListener(new SearchButtonListener());
        this.add(search);

        String [][] data = new String[1][5];
        //String [][] data = new String[orders.length][5];
        /*for each (order in orders){
        data[0][0] = Integer.toString(order.getId());// id
        data[0][1] = order.getClient.getName();//client
        data[0][2] = order.getBusinessUnitId.getStreetName();// adresse
        data[0][3] = order.getOrderDate;//DateEditor
        data[0][4] = order.getState();// etat
      }*/

        JTable table=new JTable(data,column);
        table.setEnabled(false);
        JScrollPane sp=new JScrollPane(table);
        this.add(sp);
    }

    private String getSelectedState(){
        if(newO.isSelected() == true)
            return "new";
        else if(preparedO.isSelected() == true)
            return "prepared";
        else if(deliveredO.isSelected() == true)
            return "delivered";
        else if(paidO.isSelected() == true)
            return "payed";
        else
            return "anyState";
    }

    private LocalDate getDateMin(){
        Date date = (Date)dateMin.getValue();
        LocalDate formattedDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return formattedDate;
    }

    private LocalDate getDateMax(){
        Date date = (Date)dateMax.getValue();
        LocalDate formattedDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return formattedDate;
    }

    private class SearchButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

            LocalDate dateMin = getDateMin();
            LocalDate dateMax = getDateMax();

            if(dateMin.isAfter(dateMax)){
                JOptionPane.showMessageDialog(null, "La date minimum ne peut pas être après la date max", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String dateMinToString = dateMin.getYear() + "-" + dateMin.getMonthValue() + "-" + dateMin.getDayOfMonth();
            String dateMaxToString = dateMax.getYear() + "-" + dateMax.getMonthValue() + "-" + dateMax.getDayOfMonth();

            if(getSelectedState() == "anyState"){
                try {
                    orders = controller.getOrdersWithDates(dateMinToString, dateMaxToString);
                    //TODO : Refresh Le tableau
                } catch (ProgramErrorException error) {
                    JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                try {
                    orders = controller.getOrdersWithStateAndDates(getSelectedState(), dateMinToString, dateMaxToString);
                    //TODO Refresh le tableau
                } catch (ProgramErrorException error) {
                    JOptionPane.showMessageDialog (null, error.getMessage(),"ERREUR", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
