package gui;

import javax.swing.*;

import composants.StatsOnOrders;
import controller.*;
import exceptions.ProgramErrorException;

import java.awt.*;
import java.util.Map;

public class StatsPanel extends JPanel {
    private Controller controller = new Controller();
    private JLabel orderAvgValue;
    private JTable tableStats;
    private String[] columns = {"Bière", "Nombre de pièces vendues"};
    private JScrollPane sp;

    StatsOnOrders stats;

    public StatsPanel(){
        try {
            stats = controller.getStatsOnOrders();
        }
        catch(ProgramErrorException e){
            JOptionPane.showMessageDialog (null,"Oups, une erreur s'est produite lors de la récupération de stats sur les commnandes","ERREUR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(600, 600));

        orderAvgValue = new JLabel("Valeur moyenne des commandes : " + String.format("%.2f", stats.getOrderAvgValue()));
        this.add(orderAvgValue);

        String[][] data = new String[stats.getBeersOrderCount().size()][5];

        int i = 0;
        for(Map.Entry<String, Integer> entry : stats.getBeersOrderCount().entrySet()){
            data[i][0] = entry.getKey();
            data[i][1] = Integer.toString(entry.getValue());
            i++;
        }
        tableStats = new JTable(data, columns);
        tableStats.setEnabled(false);
        sp = new JScrollPane(tableStats);
        this.add(sp);
    }
}
