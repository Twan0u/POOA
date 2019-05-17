package dataccess;

import composants.*;
import exceptions.*;

import java.util.*;
import java.sql.*;

public class OrderDBAccess {
    public static ArrayList<Order> getAllOrders() throws DataAccessException, CorruptedDataException {
        Connection connection = SingletonConnection.getInstance();
        ArrayList<Order> orders = new ArrayList<>();

        String sql = "SELECT * FROM ClientOrder as cO"
                + " LEFT JOIN OrderLine oL on oL.OrderNumber = cO.idOrder"
                + " LEFT JOIN Beer b on oL.BeerName = b.idName"
                + " LEFT JOIN BusinessUnit bU on cO.businessUnit = bU.idBusinessUnit"
                +" LEFT JOIN Client c on cO.clientNumber = c.idClient"
                +" LEFT JOIN Locality l on bU.locality = l.idLocality"
                + " ORDER BY cO.idOrder";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet data = statement.executeQuery();

            Order order = null;
            int idOrder = -1;
            Integer businessId;
            int clientNumber;
            boolean hasPriority;
            String orderDate;
            String state;
            Integer timeLimit;

            Client client;
            String phoneNumber;
            String clientName;
            String vatNumber;
            double discount;

            OrderLine orderLine;
            int quantity;
            double price;
            String beerName;

            Beer beer;
            double stockPrice;
            int qtInStock;
            int lowThreshold;

            BusinessUnit business;
            int localityNumber;
            String streetNumber;
            String streetName;

            Locality locality;
            String localityName;
            String postalCode;

            while(data.next()) {
                if(idOrder != data.getInt("idOrder")){
                    idOrder = data.getInt("idOrder");
                    hasPriority = data.getInt("hasPriority") == 1;
                    orderDate = data.getString("orderDate");
                    state = data.getString("state");
                    clientNumber = data.getInt("clientNumber");
                    timeLimit = data.getInt("timeLimit");

                    phoneNumber = data.getString("phoneNumber");
                    clientName = data.getString("clientName");
                    discount = data.getDouble("discount");
                    client = new Client(clientNumber, clientName, phoneNumber, discount);
                    vatNumber = data.getString("vatNumber");
                    if(!data.wasNull())
                        client.setVATNumber(vatNumber);

                    order = new Order(idOrder, client, hasPriority, orderDate, state, timeLimit);

                    businessId = data.getInt("businessUnit");
                    if(!data.wasNull()) {
                        localityNumber = data.getInt("idLocality");
                        localityName = data.getString("localityName");
                        postalCode = data.getString("postalCode");
                        locality = new Locality(localityNumber, localityName, postalCode);
                        streetName = data.getString("streetName");
                        streetNumber = data.getString("streetNumber");
                        business = new BusinessUnit(businessId, client, locality, streetName, streetNumber);
                        order.setBusinessUnitId(business);
                    }
                    orders.add(order);
                }
                    beerName = data.getString("idName");
                    qtInStock = data.getInt("qtInStock");
                    lowThreshold = data.getInt("lowTreshold");
                    stockPrice = data.getDouble("stockPrice");
                    beer = new Beer(beerName, stockPrice, qtInStock, lowThreshold);

                    price = data.getDouble("price");
                    quantity = data.getInt("quantity");
                    orderLine = new OrderLine(beer, order, quantity, price);
                    order.additem(orderLine);
            }
        }
        catch(SQLException e) {
            throw new DataAccessException("Erreur lors de la récupération de données concernant les commandes");
        }
        catch(ClientException e) {
            throw new CorruptedDataException("Des données incohérentes concernant les clients sont présentes dans la BD");
        }
        catch(OrderException e){
            throw new CorruptedDataException("Des données incohérentes concernant les commandes sont présentes dans la BD");
        }
        catch(LocalityException e){
            throw new CorruptedDataException("Des données incohérentes concernant les localités sont présentes dans la BD");
        }
        catch(BusinessUnitException e){
            throw new CorruptedDataException("Des données incohérentes concernant les business sont présentes dans la BD");
        }
        catch(BeerException e){
            throw new CorruptedDataException("Des données incohérentes concernant les bières sont présentes dans la BD");
        }
        catch (OrderLineException e) {
            throw new CorruptedDataException("Des données incohérentes concernant les lignes commandes sont présentes dans la BD");
        }
        return orders;
    }

    public static int saveOrder(Order order) throws DataAccessException, DataBackupException {
        Connection connection = SingletonConnection.getInstance();
        Integer id = null;

        String sql = "INSERT INTO ClientOrder (businessUnit, clientNumber, hasPriority, orderDate, state, timeLimit)"
                + " VALUES (?,?,?,?,?,?);";

        try {
            int hasPriority = order.getHasPriority() ? 1 : 0;
            BusinessUnit business = order.getBusinessUnitId();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(2, order.getClient().getId());
            statement.setInt(3, hasPriority);
            statement.setString(4, order.getOrderDate());
            statement.setString(5, order.getState());
            if(business != null) {
                statement.setInt(1, business.getIdBusinessUnit());
            }
            else {
                statement.setNull(1, Types.INTEGER);
            }
            if(order.getTimeLimit() > 0) {
                statement.setInt(6,order.getTimeLimit());
            }
            else {
                statement.setNull(6, Types.INTEGER);
            }
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();

            if(rs.next())
                id = rs.getInt(1);

            sql = "INSERT INTO OrderLine (orderNumber, quantity, price, beerName)"
                    + " VALUES (?,?,?,?);";
            statement = connection.prepareStatement(sql);
            int nbOrderLines = order.getOrderLinesSize();
            OrderLine orderLine;

            for(int i = 0; i < nbOrderLines; i++) {
                orderLine = order.getOrderLine(i);
                statement.setInt(1, order.getId());
                statement.setInt(2, orderLine.getQuantity());
                statement.setDouble(3, orderLine.getPrice());
                statement.setString(4, orderLine.getBeer().getName());
                statement.executeUpdate();
            }
        }
        catch(SQLException e){
            throw new DataBackupException("Erreur lors de la sauvegarde d'une commande dans la BD");
        }
        return id;
    }
}