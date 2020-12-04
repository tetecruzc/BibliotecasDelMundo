
import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alley
 */
    
public interface Middleware extends Remote{
    public String buscarTitulo(String title) throws RemoteException; 
    public String buscarAutor(String title) throws RemoteException; 
    public String getTitle(String title) throws RemoteException; 
    public String getAuthor(String title) throws RemoteException; 
}