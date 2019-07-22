package dataccess;

import composants.Order;
import exceptions.CorruptedDataException;
import exceptions.DataAccessException;
import exceptions.DataBackupException;

import java.util.ArrayList;

public interface OrderAccess {
    ArrayList<Order> getOrders(String state, Integer idLocality, Integer orderId, String dateMin, String dateMax, Integer idClient, boolean toDeliver, int methodCase) throws DataAccessException, CorruptedDataException;
    int saveOrder(Order order) throws DataAccessException, DataBackupException;
    public  void deleteOrder(int idOrder) throws DataAccessException;
    public void modifyOrder(Order order) throws DataAccessException;
}
