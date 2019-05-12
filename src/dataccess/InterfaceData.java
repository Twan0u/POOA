package dataccess;

import composants.*;
import exceptions.*;

import java.io.*;
import java.util.*;

public interface InterfaceData {
  Client[] getAllClients()throws ClientException;
  Client getClient(int index);
  Beer[] getAllBeers()throws BeerException;
  BusinessUnit[] getBusinessOf(int index)throws BusinessUnitException,LocalityException;
}
