package TanqueEntrada;

import static TanqueEntrada.ClienteNA.remoto;
import atividadermi.InterfaceRMI;
import static java.lang.Math.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteOleoResidual {
        
       
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException{
        try{
            int  randomNum;
             InterfaceTanques remoto = null;
            Registry reg=LocateRegistry.getRegistry("169.254.40.49",2025);
            remoto = (InterfaceTanques) reg.lookup("TanqueOleo");
            System.out.println("Cliente Oleo: ");
          while(true){
                randomNum = (int) (100+ random() * 100);
               System.out.println(remoto.executa(randomNum)); 
                //espera 1 segundo para chamar novamente
               new Thread().sleep(5000);
          }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
