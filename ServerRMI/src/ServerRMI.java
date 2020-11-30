/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tete
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class ServerRMI extends UnicastRemoteObject implements RMI{
    public ServerRMI() throws RemoteException{
        super();
    }
    
    @Override 
    public int sumar(int n1,int n2) throws RemoteException{
            return n1+n2;
    }
    
    public static void main(String[] args){
        try{
               Registry registro = LocateRegistry.createRegistry(7778);
               registro.rebind("RemoteRMI", new ServerRMI());
               System.out.println("SERVIDOR ACTIVO");
        }
        catch(RemoteException ex){
                System.out.println(ex.getMessage());
        }
    }
}
