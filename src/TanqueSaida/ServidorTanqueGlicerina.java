
package TanqueSaida;

import Processadores.ServidorDecantador;
import atividadermi.InterfaceRMI;
import java.rmi.registry.LocateRegistry;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

public class ServidorTanqueGlicerina extends UnicastRemoteObject implements InterfaceSaida {
    public static TanqueGlicerina tanqueGlicerina;

    public ServidorTanqueGlicerina() throws RemoteException {
    }
    
 public static void main(String[] args) {
        try {
            tanqueGlicerina = new TanqueGlicerina();
            tanqueGlicerina.QuantidadeGlicerina = 0;
          Registry r = LocateRegistry.createRegistry(2030);          
          ServidorTanqueGlicerina server = new ServidorTanqueGlicerina();
           r.rebind("ServidorTanqueGlicerina", server);
         //System.out.println(r.lookup("Ola"));
         //System.setProperty( "java.rmi.server.hostname", "192.168.100.2" );
          System.out.println("ServidorTanqueGlicerina pronto");     
      } catch (Exception ex) {
         ex.printStackTrace();
      } 
    }
 
           @Override
    public boolean executa(float f) throws RemoteException {
        tanqueGlicerina.QuantidadeGlicerina =  tanqueGlicerina.QuantidadeGlicerina + f;
        System.out.println("ServidorTanqueGlicerina recebendo " + f + " em "+ LocalTime.now() + "total -> " + tanqueGlicerina.QuantidadeGlicerina);     
        return true;
    }
}
