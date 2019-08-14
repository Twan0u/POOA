package gui;

import javax.swing.*;

import composants.StatsOnOrders;
import controller.*;
import exceptions.ProgramErrorException;

public class StatsPanel extends JPanel {
    private Controller controller;
    private JLabel statsOnBeersLabel;
    private JLabel orderAvgValue;
    private JTable tableStats;

    StatsOnOrders stats;

    public StatsPanel(){
        try {
            stats = controller.getStatsOnOrders();
        }
        catch(ProgramErrorException e){
            JOptionPane.showMessageDialog (null,"Oups, une erreur s'est produite lors de la récupération de stats sur les commnandes","ERREUR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        orderAvgValue = new JLabel("Valeur moyenne des commandes : " + stats.getOrderAvgValue());

    }
}
