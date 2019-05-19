package composants;

import java.util.HashMap;

public class StatsOnOrders {
    private Double orderAvgValue;
    private HashMap<String, Integer> beersOrderCount;

    public StatsOnOrders(Double orderAvgValue, HashMap<String, Integer> beersOrderCount){
        this.orderAvgValue = orderAvgValue;
        this.beersOrderCount = beersOrderCount;
    }

    public double getOrderAvgValue(){
        return orderAvgValue;
    }

    public HashMap<String, Integer> getBeersOderCount() {
        return beersOrderCount;
    }
}