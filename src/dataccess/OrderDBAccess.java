package dataccess;

import composants.BusinessUnit;
import composants.Order;

import java.util.*;
import java.sql.*;

public class OrderDBAccess {
    public static void saveOrder(Order order) {
        Connection connection = SingletonConnection.getInstance();

        String sql = "INSERT INTO ClientOrder (idNumber, businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)"
                + " VALUES (?,?,?,?,?,?,?);";
        int hasPriority = order.getHasPriority() ? 1 : 0;
        BusinessUnit business = order.getBusinessUnitId();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, order.getId());
            statement.setInt(3, order.getClient().getId());
            statement.setInt(4, hasPriority);
            statement.setString(5, order.getOrderDate());
            statement.setString(6, order.getState());
            if(business != null) {
                statement.setInt(2, order.getBusinessUnitId().getIdBusinessUnit());
            }
            else {
                statement.setNull(2, Types.INTEGER);
            }
            if(order.getTimeLimit() > 0) {
                statement.setInt(7,order.getTimeLimit());
            }
            else {
                statement.setNull(7, Types.INTEGER);
            }

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("ici");
            System.out.println(e.getMessage());
            System.exit(0);
        }
        System.out.println("WidePeepoHappy");
    }
}
