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
public class ServidorDecantador   extends UnicastRemoteObject implements InterfaceProcessadores{
    public static Decantador decantador;

    public ServidorDecantador()  throws RemoteException{
    }
    
    public static void main(String[] args) {
        try {
            decantador = new Decantador();
            decantador.quantidade = (float)0;
          Registry r = LocateRegistry.createRegistry(2029);          
          ServidorDecantador server = new ServidorDecantador();
           r.rebind("ServidorDecantador", server);
          System.out.println("Servidor Decantador pronto "+r.lookup("ServidorDecantador"));     
      } catch (Exception ex) {
         ex.printStackTrace();
      } 
    }

    @Override
    public boolean executa(float f, int cod) throws RemoteException {
        if(cod != 0){
            decantador.quantidade = 0;
        }
        if(decantador.quantidade + f > 500){
            decantador.quantidade = 500;
            System.out.println("Decantador cheio!  " + decantador.quantidade);     
            return false;
        }
        decantador.quantidade = decantador.quantidade + f;
        System.out.println("Decantador recebeu "+ f + "litros do Reator - Total -> " + decantador.quantidade);     
        return true;
    }
    @Override
    public float getQuantidade(int cod) throws RemoteException {
        return decantador.quantidade;
    }

    
}
