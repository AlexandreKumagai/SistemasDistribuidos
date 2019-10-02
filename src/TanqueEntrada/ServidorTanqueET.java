/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanqueEntrada;

import Processadores.InterfaceProcessadores;
import Processadores.ServidorDecantador;
import atividadermi.InterfaceRMI;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;

/**
 *
 * @author alexa
 */
public class ServidorTanqueET    extends UnicastRemoteObject implements InterfaceTanques  {
    public static TanqueET tanqueET;

    public ServidorTanqueET() throws RemoteException{
    }
      public static void main(String[] args) {
        try {
            tanqueET = new TanqueET();
            tanqueET.quantidadeEt = 0;
          Registry r = LocateRegistry.createRegistry(2027);          
          ServidorTanqueET server = new ServidorTanqueET();
           r.rebind("ServidorTanqueET", server);
          System.out.println("ServidorTanqueET pronto");     
      } catch (Exception ex) {
         ex.printStackTrace();
      } 
    }
                @Override
    public String executa(float f) throws RemoteException {
        tanqueET.quantidadeEt = tanqueET.quantidadeEt + f;
        System.out.println("ServidorTanqueET recebeu " + f + "  em " + LocalTime.now() + " -> Total "+ tanqueET.quantidadeEt);  
        return "ok";
    }

    @Override
    public float getQuantidade() throws RemoteException {
        float f =  tanqueET.quantidadeEt;
        return f;
    }

    @Override
    public boolean retirar(float f) throws RemoteException {
        if(f  > tanqueET.quantidadeEt)
            return false;
        else
            tanqueET.quantidadeEt = tanqueET.quantidadeEt - f;
         return true;
    }    
}
