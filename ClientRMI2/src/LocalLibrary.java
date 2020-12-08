
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
public class LocalLibrary extends Library{
     public LocalLibrary(String name, String ip, int port, String serverName){
        super();
        this.name = name;
        
        try{
            Registry registro = LocateRegistry.getRegistry(ip, port);
            this.interfaz =(Middleware)registro.lookup(serverName);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String getBookByTitle(String title) {
        String book = null;
        try {
            book = this.interfaz.pedirLibro(title);
        } catch (RemoteException ex) {
            Logger.getLogger(LocalLibrary.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LocalLibrary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return books;
    }

}
