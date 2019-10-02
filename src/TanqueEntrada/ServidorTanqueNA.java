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
public class ServidorTanqueNA    extends UnicastRemoteObject implements InterfaceTanques {
    public static TanqueNA tanqueNA;

    public ServidorTanqueNA() throws RemoteException{
    }
        public static void main(String[] args) {
        try {
          tanqueNA = new TanqueNA();
          tanqueNA.quantidadeNa = 0;
          Registry r = LocateRegistry.createRegistry(2026);          
          ServidorTanqueNA server = new ServidorTanqueNA();
           r.rebind("ServidorTanqueNA", server);
          System.out.println("ServidorTanqueNA pronto");     
      } catch (Exception ex) {
         ex.printStackTrace();
      } 
    }
                  @Override
    public String executa(float i) throws RemoteException {
        tanqueNA.quantidadeNa = tanqueNA.quantidadeNa + i;
        System.out.println("ServidorTanqueNA recebeu " +i +" em  "+ LocalTime.now() + " -> Total "+ tanqueNA.quantidadeNa);  
        return "ok";
    }
    @Override
    public float getQuantidade() throws RemoteException {
        return tanqueNA.quantidadeNa;
    }
    
     @Override
    public boolean retirar(float f) throws RemoteException {
        if(f  > tanqueNA.quantidadeNa)
            return false;
        else
            tanqueNA.quantidadeNa  = tanqueNA.quantidadeNa - f;
         return true;
    }    
}
