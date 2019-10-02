/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TanqueEntrada;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author alexa
 */
public class ClienteNA {
    static InterfaceTanques remoto = null;
    public static void main(String[] args) {
        try{
            InterfaceTanques remoto = null;
            Registry reg=LocateRegistry.getRegistry("169.254.40.49",2026);
            remoto = (InterfaceTanques) reg.lookup("ServidorTanqueNA");
            System.out.println("Cliente NA: ");            
            while(true){
                System.out.println(remoto.executa((float) 0.451));
                //esperando 1 segundo para chamar o executa
               new Thread().sleep(1000);
           }
            
          
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
