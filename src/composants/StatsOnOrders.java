package composants;

import java.util.HashMap;
import java.util.Map;

public class StatsOnOrders {
    private Double orderAvgValue;
    private Map<String, Integer> beersOrderCount;

    public StatsOnOrders(Double orderAvgValue, HashMap<String, Integer> beersOrderCount){
        this.orderAvgValue = orderAvgValue;
        this.beersOrderCount = beersOrderCount;
    }

    public double getOrderAvgValue(){
        return orderAvgValue;
    }

    public Map<String, Integer> getBeersOrderCount() {
        return beersOrderCount;
    }
}