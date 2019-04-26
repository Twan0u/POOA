package controller;
import business.*;
import composants.*;
import composants.exceptions.*;

public interface InterfaceController {
  String[] getClients()throws ClientException;
  String getInfoClient(int index);
  String[] getBeers()throws BeerException;
  String[] getBusinessOf(int index)throws BusinessUnitException,LocalityException;
}
