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
public class ClienteET {
    public static void main(String[] args) {
        try{
            
            Registry reg=LocateRegistry.getRegistry("169.254.40.49",2027);
            
            InterfaceTanques remoto  = (InterfaceTanques) reg.lookup("ServidorTanqueET");
            
            System.out.println("Cliente ET: ");
            
            while(true){
                System.out.println(remoto.executa((float) 1.1));
                //esperando 1 segundo para chamar o executa
               new Thread().sleep(1000);
           }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
