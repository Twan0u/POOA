package dataccess;

import java.util.*;

import composants.*;
import exceptions.*;

public class DBAccess implements InterfaceData {

    public ArrayList<Client> getAllClients()throws ClientException {
        return ClientDBAccess.getAllClients();
    }
    public Client getClient(int id){return(null);}

    public ArrayList<Beer> getAllBeers()throws BeerException {
        return BeerDBAccess.getAllBeers();
    }
    public BusinessUnit[] getBusinessOf(int id)throws BusinessUnitException,LocalityException{
        return BusinessDBAccess.getBusinessOf(id);
    }
}