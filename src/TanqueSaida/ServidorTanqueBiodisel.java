
package TanqueSaida;

import Processadores.ServidorDecantador;
import atividadermi.InterfaceRMI;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

public class ServidorTanqueBiodisel  extends UnicastRemoteObject implements InterfaceSaida {
    public static  TanqueBiodisel tanqueBiodisel;

    public ServidorTanqueBiodisel() throws RemoteException{
    }
    
    
    public static void main(String[] args) {
        try {
            tanqueBiodisel = new TanqueBiodisel();
            tanqueBiodisel.quantidadeBiodisel = 0;
          Registry r = LocateRegistry.createRegistry(2031);          
          ServidorTanqueBiodisel server = new ServidorTanqueBiodisel();
           r.rebind("ServidorTanqueBiodisel", server);
         //System.out.println(r.lookup("Ola"));
         //System.setProperty( "java.rmi.server.hostname", "192.168.100.2" );
          System.out.println("ServidorTanqueBiodisel pronto");     
      } catch (Exception ex) {
         ex.printStackTrace();
      } 
    }
    
              @Override
    public boolean executa(float fi) throws RemoteException {
        tanqueBiodisel.quantidadeBiodisel =  tanqueBiodisel.quantidadeBiodisel + fi;
         System.out.println("ServidorTanqueBiodisel recebendo " + fi + " em " + LocalTime.now() + " total : " + tanqueBiodisel.quantidadeBiodisel);     
        return true;
    }
}
