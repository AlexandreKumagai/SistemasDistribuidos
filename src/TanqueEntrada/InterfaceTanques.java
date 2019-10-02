/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanqueEntrada;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author alexa
 */
public interface InterfaceTanques extends Remote {
    public String executa(float i) throws RemoteException;
    
    public float getQuantidade() throws RemoteException;
    
    public boolean retirar(float f) throws RemoteException;
}
