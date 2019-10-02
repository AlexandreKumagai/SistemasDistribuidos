/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processadores;

import TanqueEntrada.InterfaceTanques;
import TanqueSaida.InterfaceSaida;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author alexa
 */
public class ClienteDecantador {
     
    public static void main(String[] args) {
        try{
             Registry regDecantador =LocateRegistry.getRegistry("169.254.40.49",2029);
             InterfaceProcessadores remotoDecantador = (InterfaceProcessadores) regDecantador.lookup("ServidorDecantador");
             
             Registry regGlicerina =LocateRegistry.getRegistry("169.254.40.49",2030);
             InterfaceSaida remotoGlicerina = (InterfaceSaida) regGlicerina.lookup("ServidorTanqueGlicerina");
             
             Registry regLavador =LocateRegistry.getRegistry("169.254.40.49",2033);
             InterfaceProcessadores remotoLavador = (InterfaceProcessadores) regLavador.lookup("ServidorLavador");
             
             Registry regSecador=LocateRegistry.getRegistry("169.254.40.49",2032);
             InterfaceProcessadores remotoSecador = (InterfaceProcessadores) regSecador.lookup("ServidorSecador");
             
             float f   = 0;
          while(true){
                System.out.println("Distribuindo do decantador...");
                 f = remotoDecantador.getQuantidade(0);
                remotoGlicerina.executa((float) (f * 0.02));
                remotoLavador.executa((float) (f * 0.9), 0);
                remotoSecador.executa((float) (f * 0.08), 0);
                remotoDecantador.executa(f, 1);
                f = 0;
                System.out.println("Processado com sucesso");
                //espera 1 segundo para chamar novamente
               new Thread().sleep(5000);
          }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
