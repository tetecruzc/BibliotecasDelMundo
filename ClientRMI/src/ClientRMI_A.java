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
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientRMI_A extends Client{
    public ClientRMI_A(){
        super();
        this.name = "Local";
        
        try{
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 7778
            );
            this.interfaz =(Middleware)registro.lookup("RemoteRMI_A");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
  
//    public static void main(String[] args){
//        ClientRMI_A client = new ClientRMI_A();
//        client.connectServer();
//    }

//    private void connectServer() {
//        try{
//            Registry registro = LocateRegistry.getRegistry("127.0.0.1",7778);
//            this.interfaz =(Middleware)registro.lookup("RemoteRMI"); // busca el obj remoto en el registro RMI de la maquina remota
//            System.out.println(interfaz.pedirLibro("Harry Potter"));
//            System.out.println(interfaz.pedirAutor("J.K Rowling"));
//        }
//        catch(Exception ex){
//            System.out.println(ex);
//        }
//    }

    @Override
    public String getBookByTitle(String title) {
        String book = null;
        try {
            book = this.interfaz.pedirLibro(title);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientRMI_A.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return book;   
    }

    
    @Override
    public List<String> getBookByAuthor(String author) {
        List<String> books = new ArrayList<String>(); 
        
        try {
            books = this.interfaz.pedirAutor(author);
        } 
        catch (RemoteException ex) {
            Logger.getLogger(ClientRMI_A.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }
}
