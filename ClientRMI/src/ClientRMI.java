/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tete
 */
import java.io.File;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRMI {
    public static void main(String[] args){
        ClientRMI client = new ClientRMI();
        client.connectServer();
    }

    private void connectServer() {
        try{
            Registry registro = LocateRegistry.getRegistry("127.0.0.1",7778);
            RMI interfaz =(RMI)registro.lookup("RemoteRMI"); // busca el obj remoto en el registro RMI de la maquina remota
            System.out.println(interfaz.pedirLibro("Harry Potter"));
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
    
}
