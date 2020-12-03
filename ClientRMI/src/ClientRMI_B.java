
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alley
 */
public class ClientRMI_B extends Client {

    public ClientRMI_B(){
        super();
        this.name = "Client B";
        
        try{
//            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 7777);
//            this.interfaz =(Middleware)registro.lookup("RemoteRMI_B");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String getBookByTitle(String title) {
        String book = null;
        try {
           book =  this.interfaz.getTitle(title);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientRMI_B.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return book;
    }

    @Override
    public List<String> getBookByAuthor(String author) {
        List<String> books = new ArrayList<String>(); 
        try {
           books =  this.interfaz.getAuthor(author);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientRMI_B.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    } 
}
