package dataccess;

import composants.Beer;
import exceptions.CorruptedDataException;
import exceptions.DataAccessException;

import java.util.ArrayList;

public interface BeerAccess {
    ArrayList<Beer> getAllBeers() throws DataAccessException, CorruptedDataException;
}
