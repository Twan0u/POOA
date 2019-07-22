package dataccess;

import composants.Client;
import exceptions.CorruptedDataException;
import exceptions.DataAccessException;

import java.util.ArrayList;

public interface ClientAccess {
    ArrayList<Client> getClients(Integer clientID) throws DataAccessException, CorruptedDataException;
}
