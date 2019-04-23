package business;

import dataccess.*;
import composants.*;
import composants.exceptions.*;

import java.io.*;
import java.util.*;

public interface BusinessInterface {
  Client[] getAllClients()throws ClientException;
  Client getInfoClient(int index);
  Beer[] getAllBeers()throws BeerException;
  BusinessUnit[] getBusinessOf(int num)throws BusinessUnitException,LocalityException;
}
