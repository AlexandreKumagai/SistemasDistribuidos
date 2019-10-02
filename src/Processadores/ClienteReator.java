/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Processadores;

import TanqueEntrada.InterfaceTanques;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;

/**
 *
 * @author alexa
 */
public class ClienteReator {
     
    public static void main(String[] args) {
        try{
             Registry regEt =LocateRegistry.getRegistry("169.254.40.49",2027);
             InterfaceTanques remotoEt = (InterfaceTanques) regEt.lookup("ServidorTanqueET");
             
             Registry regNA =LocateRegistry.getRegistry("169.254.40.49",2026);
             InterfaceTanques remotoNa = (InterfaceTanques) regNA.lookup("ServidorTanqueNA");
             
             Registry regOleo =LocateRegistry.getRegistry("169.254.40.49",2025);
             InterfaceTanques remotoOleo = (InterfaceTanques) regOleo.lookup("TanqueOleo");
             
             Registry regReator =LocateRegistry.getRegistry("169.254.40.49",2028);
             InterfaceProcessadores remotoReator = (InterfaceProcessadores) regReator.lookup("ServidorReator");
             
             Registry regDecantador =LocateRegistry.getRegistry("169.254.40.49",2029);
             InterfaceProcessadores remotoDecantador = (InterfaceProcessadores) regDecantador.lookup("ServidorDecantador");
          while(true){
                if(remotoEt.getQuantidade() > 2 &&   remotoNa.getQuantidade() > 1 &&  remotoOleo.getQuantidade() > 50){
                    System.out.println("Executando : Et -> " + remotoEt.getQuantidade()+ " NA ->" + remotoNa.getQuantidade()+ " Oleo ->" + remotoOleo.getQuantidade());
                    System.out.println(remotoReator.executa(0,0));
                    remotoEt.retirar(2);
                    remotoNa.retirar(1);
                    remotoOleo.retirar(50);
                    remotoDecantador.executa(50,0);
                    System.out.println("executou com sucesso");
               }
                new Thread().sleep(1000);
                System.out.println("Reator aguardando execução em " + LocalTime.now());
          }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
