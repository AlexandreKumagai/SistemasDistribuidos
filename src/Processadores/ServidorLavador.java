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

/**
 *
 * @author alexa
 */
public class ServidorLavador extends UnicastRemoteObject implements InterfaceProcessadores   {
    public static Lavador lavador1;
    public static Lavador lavador2;
    public static Lavador lavador3;

    public ServidorLavador() throws RemoteException {
    }
    
    public static void main(String[] args) {
        try {
            lavador1 = new Lavador();
            lavador1.quantidade = 0;
            lavador2 = new Lavador();
            lavador2.quantidade = 0;
            lavador3 = new Lavador();
            lavador3.quantidade = 0;
          Registry r = LocateRegistry.createRegistry(2033);          
          ServidorLavador server = new ServidorLavador();
           r.rebind("ServidorLavador", server);
         //System.out.println(r.lookup("Ola"));
         //System.setProperty( "java.rmi.server.hostname", "192.168.100.2" );
          System.out.println("ServidorLavador pronto");     
      } catch (Exception ex) {
         ex.printStackTrace();
      } 
    }

    @Override
    public boolean executa(float f, int cod) throws RemoteException {
        System.out.println("A lavar no 1 : 1 - > "+ lavador1.quantidade + " 2 -> "+ lavador2.quantidade + " 3 -> "+ lavador3.quantidade);
        lavador1.quantidade = (float) (lavador1.quantidade +( f * 0.97));
        System.out.println("A lavar no 2 : 1 - > "+ lavador1.quantidade + " 2 -> "+ lavador2.quantidade + " 3 -> "+ lavador3.quantidade);
        lavador2.quantidade = (float) (lavador1.quantidade * 0.97 + lavador2.quantidade);
        lavador1.quantidade = 0;
        System.out.println("A lavar no 3 : 1 - > "+ lavador1.quantidade + " 2 -> "+ lavador2.quantidade + " 3 -> "+ lavador3.quantidade);
        lavador3.quantidade = (float) (lavador2.quantidade * 0.97 + lavador3.quantidade);
        lavador2.quantidade = 0;
        return false;
    }

    @Override
    public float getQuantidade(int cod) throws RemoteException {
        float f =  lavador3.quantidade;
        lavador3.quantidade = 0;
        return f;               
    }

}
