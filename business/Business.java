package business;

import dataccess.*;
import composants.*;
import composants.exceptions.*;

public class Business implements BusinessInterface {
    private DataMock dataaccess = new DataMock();

    public Client[] getAllClients()throws ClientException{
      return dataaccess.getAllClients();
    }

    public Client getInfoClient(int index){
      return dataaccess.getClient(index);
    }

    public Beer[] getAllBeers()throws BeerException{
      return dataaccess.getAllBeers();
    }

    public BusinessUnit[] getBusinessOf(int num)throws BusinessUnitException,LocalityException{
      return dataaccess.getBusinessOf(num);
    }
}
