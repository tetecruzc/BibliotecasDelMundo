
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

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
    public String pedirLibro(String title) throws RemoteException; 
    public List<String> pedirAutor(String title) throws RemoteException; 
    public String getTitle(String title) throws RemoteException; 
    public List<String> getAuthor(String title) throws RemoteException; 
}
