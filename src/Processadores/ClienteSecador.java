/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processadores;

import TanqueEntrada.InterfaceTanques;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author alexa
 */
public class ClienteSecador {
     public static void main(String[] args) {
        try{
             Registry regLav =LocateRegistry.getRegistry("169.254.40.49",2033);
             InterfaceProcessadores remotoLav = (InterfaceProcessadores) regLav.lookup("ServidorLavador");
             
             Registry regSec =LocateRegistry.getRegistry("169.254.40.49",2032);
             InterfaceProcessadores remotoSec = (InterfaceProcessadores) regSec.lookup("ServidorSecador");
             
          while(true){
              
              System.out.println(remotoSec.executa(remotoLav.getQuantidade(0),0));
          }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
