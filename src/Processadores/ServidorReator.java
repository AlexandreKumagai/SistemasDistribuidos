/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processadores;

import atividadermi.AtividadeRMI;
import atividadermi.InterfaceRMI;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexa
 */
public class ServidorReator   extends UnicastRemoteObject implements InterfaceProcessadores {

    public ServidorReator() throws RemoteException {
    }
     public static Reator reator;
    public static void main(String[] args) {
        try {
           reator = new Reator();
           reator.quantidadeEt = 0;
           reator.quantidadeNA = 0;
           reator.quantidadeOleo = 0;
          Registry r = LocateRegistry.createRegistry(2028);          
          ServidorReator server = new ServidorReator();
           r.rebind("ServidorReator", server);
         //System.out.println(r.lookup("Ola"));
         //System.setProperty( "java.rmi.server.hostname", "192.168.100.2" );
          System.out.println("ServidorReator pronto");     
      } catch (Exception ex) {
         ex.printStackTrace();
      } 
    }
    @Override
    public boolean executa(float i, int codE) throws RemoteException {
                System.out.println("Reator ativado em: " + LocalTime.now());     
                  return true;
         }

    @Override
    public float getQuantidade(int cod) throws RemoteException {
        switch(cod){
            case 0 :
                return reator.quantidadeEt;
            case 1 :
                return reator.quantidadeNA;
            case 2 :
                return reator.quantidadeOleo;
        }
     return 0;   
    }
}
