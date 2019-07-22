package dataccess;

import composants.BusinessUnit;
import exceptions.CorruptedDataException;
import exceptions.DataAccessException;

import java.util.ArrayList;

public interface BusinessAccess {
    ArrayList<BusinessUnit> getBusinessWithClientID(int idClient) throws DataAccessException, CorruptedDataException;
}