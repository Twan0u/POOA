package gui;

import composants.Locality;
import composants.Order;
import controller.Controller;
import exceptions.ProgramErrorException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class DeliveryPanel extends JPanel{
    private JScrollPane sp;
    private JTable table;
    private JButton search;
    private JLabel cpLabel;
    private JLabel locNameLabel;

    private Controller controller = new Controller();
    private ArrayList<Order> ordersToDeliver;
    private ArrayList<Locality> localitiesToDeliver = new ArrayList<>();

    private String column[]={"ID-COMMANDE","Client","Prioritaire","Rue","Code Postal","Localite"};

    private JComboBox comboxLocality, comboxCodePostal;

    public DeliveryPanel(){
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(600, 600));

        try{
            ordersToDeliver = controller.getOrdersToDeliver();
        } catch (ProgramErrorException e) {
            JOptionPane.showMessageDialog(null, "Il y a eu une erreur lors de la récupération de données sur les commandes à livrer", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        comboxCodePostal = new JComboBox();
        comboxCodePostal.setMaximumRowCount(5);
        cpLabel = new JLabel("Code postal");
        comboxCodePostal.addItemListener(new PostCodeComboBoxListener());
        this.add(cpLabel);
        this.add(comboxCodePostal);

        comboxLocality = new JComboBox();
        comboxLocality.setMaximumRowCount(5);
        comboxLocality.setSelectedIndex(-1);
        comboxLocality.setEnabled(false);
        locNameLabel = new JLabel("Nom localité");
        this.add(locNameLabel);
        this.add(comboxLocality);

        search = new JButton("Rechercher les commandes prêtes à être livrées pour cette localité");
        search.addActionListener(new ButtonSearchListener());
        this.add(search);

        if(ordersToDeliver.size() > 0){
            fillLocalitiesToDeliver();
            for(Locality l : localitiesToDeliver){
                comboxCodePostal.addItem(l.getPostCode());
            }
        }
        String[][]data = new String[0][6];
        table = new JTable(data, column);
        table.setEnabled(false);
        sp = new JScrollPane(table);
        this.add(sp);
    }

    private class PostCodeComboBoxListener implements ItemListener {
        public void itemStateChanged(ItemEvent event){
            updateComboxLocName(localitiesToDeliver.get(comboxCodePostal.getSelectedIndex()).getPostCode());
        }
    }

    private void updateComboxLocName(String cp){
        comboxLocality.setEnabled(true);
        comboxLocality.removeAllItems();
        for(Locality l : localitiesToDeliver){
            if(l.getPostCode().equals(cp))
                comboxLocality.addItem(l.getName());
        }
    }

    private class ButtonSearchListener implements ActionListener{
        public void actionPerformed( ActionEvent event) {
            Locality selectedLocality = localitiesToDeliver.get(comboxCodePostal.getSelectedIndex());
            updateTable(selectedLocality);
        }
    }

    private void updateTable(Locality locality){
        ArrayList<Order> selectedOrders = new ArrayList<>();
        for(Order o : ordersToDeliver){
            if(o.getBusinessUnitId().getLocality().getPostCode().equals(locality.getPostCode())){
                selectedOrders.add(o);
            }
        }
        if(selectedOrders.size() == 0){
            JOptionPane.showMessageDialog (null, "Il n'y a aucune Commande à livrer dans cette localité","Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        else{
            String [][] out = new String[selectedOrders.size()][6];
            for(int i = 0; i<out.length;i++){
                Order current = selectedOrders.get(i);
                out[i][0] = Integer.toString(current.getId());
                out[i][1] = current.getClient().getName();
                out[i][2] = Boolean.toString(current.getHasPriority());
                out[i][3] = current.getBusinessUnitId().getStreetName();
                out[i][4] = current.getBusinessUnitId().getLocality().getPostCode();
                out[i][5] = current.getBusinessUnitId().getLocality().getName();
            }
            this.remove(sp);
            table = new JTable(out,column);
            table.setEnabled(false);
            sp=new JScrollPane(table);
            this.add(sp);
            this.updateUI();
        }
    }

    private void fillLocalitiesToDeliver(){
        for(Order o : ordersToDeliver){
            boolean isAlreadyIn = false;
            for(Locality l : localitiesToDeliver){
                if(o.getBusinessUnitId().getLocality().getIdLocality() == l.getIdLocality())
                    isAlreadyIn = true;
            }
            if(!isAlreadyIn)
                localitiesToDeliver.add(o.getBusinessUnitId().getLocality());
        }
    }
}
