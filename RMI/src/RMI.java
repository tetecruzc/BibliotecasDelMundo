/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tete
 */
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RMI extends Remote{
    public String pedirLibro(String title) throws RemoteException; 
   // public void pedirAutor(String title) throws RemoteException; 
    public String buscarTitulo(String title) throws RemoteException; 
   // public void buscarAutor(String title) throws RemoteException; 
   // public void encontrarVol(String title) throws RemoteException; 
   // public void encontrarAutor(String title) throws RemoteException; 
   // public void getTitle(String title) throws RemoteException; 
   // public void getAuthor(String title) throws RemoteException; 
}
