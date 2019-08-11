package business;


import composants.Client;
import exceptions.ProgramErrorException;
import org.junit.*;

import static org.junit.Assert.*;
import exceptions.ClientException;

public class BusinessTest {
    private final Business business = new Business();

    @Test
    public void getOrder() {
        assertEquals(1, business.getOrder(1).getId());
    }

    @Test(expected = ClientException.class)
    public void createClientWithWrongDiscount() throws ClientException{
        new Client(15, "jean", "+32497040852", -5);
    }


    @Test
    public void getBusinessOf() throws ProgramErrorException {
        Assert.assertEquals(0, business.getArrayBusinessOf(999999999).size());
    }

    @Test
    public void getStatsOnOrders() {
        Assert.assertTrue(business.getStatsOnOrders().getBeersOrderCount().get("bush") == 0);
    }


    @Test(timeout = 60_000)
    public void getAllClientsArray() throws ProgramErrorException {
        business.getAllClientsArray();
    }
}