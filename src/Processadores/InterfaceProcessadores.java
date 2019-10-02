/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processadores;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author alexa
 */
public interface InterfaceProcessadores extends Remote  {
    public boolean executa(float f, int cod) throws RemoteException;
    
    public float getQuantidade(int cod) throws RemoteException;


}

