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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexa
 */
public class ServidorSecador   extends UnicastRemoteObject implements InterfaceProcessadores  {
    public static Secador secador;

    public ServidorSecador()  throws RemoteException{
    }
    
    public static void main(String[] args) {
        try {
            secador = new Secador();
            secador.quantidade = 0;
          Registry r = LocateRegistry.createRegistry(2032);          
          ServidorSecador server = new ServidorSecador();
           r.rebind("ServidorSecador", server);
         //System.out.println(r.lookup("Ola"));
         //System.setProperty( "java.rmi.server.hostname", "192.168.100.2" );
          System.out.println("ServidorSecador pronto");     
      } catch (Exception ex) {
         ex.printStackTrace();
      } 
    }

    @Override
    public boolean executa(float f, int cod) throws RemoteException {
       secador.quantidade = (float) (secador.quantidade + (f * 0.99));
          System.out.println("Secando ..." + f);     
        try {
            new Thread().sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServidorSecador.class.getName()).log(Level.SEVERE, null, ex);
        }
       return true;
    }

    @Override
    public float getQuantidade(int cod) throws RemoteException {
        return secador.quantidade;                
    }
}
