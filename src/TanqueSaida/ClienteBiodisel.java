/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanqueSaida;

import Processadores.InterfaceProcessadores;
import TanqueEntrada.InterfaceTanques;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author alexa
 */
public class ClienteBiodisel {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException{
        try{
            int  randomNum;
             InterfaceSaida remoto = null;
            Registry reg=LocateRegistry.getRegistry("169.254.40.49",2031);
            remoto = (InterfaceSaida) reg.lookup("ServidorTanqueBiodisel");
            
            Registry regSec=LocateRegistry.getRegistry("169.254.40.49",2032);
           InterfaceProcessadores remotoSec = (InterfaceProcessadores) regSec.lookup("ServidorSecador");
            
          while(true){
              remoto.executa( remotoSec.getQuantidade(0));
              new Thread().sleep(500);
          }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
