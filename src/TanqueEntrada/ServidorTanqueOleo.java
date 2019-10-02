/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanqueEntrada;

import static TanqueEntrada.ServidorTanqueET.tanqueET;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

/**
 *
 * @author alexa
 */
public class ServidorTanqueOleo   extends UnicastRemoteObject implements InterfaceTanques {
    public static TanqueOleo tanqueOleo;

    /**
     *
     * @throws RemoteException
     */
    public ServidorTanqueOleo() throws RemoteException {
    }
      public static void main(String[] args) {
        try {
         //System.setProperty( "java.rmi.server.hostname", "192.168.1.2" );
          ServidorTanqueOleo server = new ServidorTanqueOleo();
          Registry r = LocateRegistry.createRegistry(2025);          

           r.bind("TanqueOleo", server);
           tanqueOleo = new TanqueOleo();
           tanqueOleo.QuantidadeOleo = (float)0;
          System.out.println("ServidorTanqueOleo pronto: "+ r.lookup("TanqueOleo"));     
      } catch (Exception ex) {
         ex.printStackTrace();
      } 
    }
        @Override
        public String executa(float  i) throws RemoteException {
        tanqueOleo.QuantidadeOleo = tanqueOleo.QuantidadeOleo +  i;
        System.out.println("Tanque oleo recebendo " +i +"litros em "+LocalTime.now() +"\nTotal no tanque: "+tanqueOleo.QuantidadeOleo);
        return "ok";
    }
        @Override
    public float getQuantidade() throws RemoteException {
        return tanqueOleo.QuantidadeOleo;
    }
     @Override
    public boolean retirar(float f) throws RemoteException {
        if(f  > tanqueOleo.QuantidadeOleo)
            return false;
        else
            tanqueOleo.QuantidadeOleo = tanqueOleo.QuantidadeOleo - f;
         return true;
    }    
}
