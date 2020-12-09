import java.io.IOException;
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
     public LocalLibrary(String name, String ip, int port, String serverName, String alias){
        super();
        this.name = name;
        this.alias = alias;
        
        try{
            Registry registro = LocateRegistry.getRegistry(ip, port);
            this.interfaz =(Middleware)registro.lookup(serverName);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public String getBookByTitle(String title, int transactionId){
        String book = null;
        try {
            book = this.interfaz.pedirLibro(title, this.alias, transactionId);
            this.saveLog(transactionId, "Pedir libro "+ title);
        } catch (RemoteException ex) {
            Logger.getLogger(LocalLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             Logger.getLogger(LocalLibrary.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        return book;   
    }

    
    @Override
    public List<String> getBookByAuthor(String author, int transactionId){
        List<String> books = new ArrayList<String>(); 
        
        try {
            books = this.interfaz.pedirAutor(author, this.alias, transactionId);
            this.saveLog(transactionId, "Pedir autor "+ author);
        } 
        catch (RemoteException ex) {
            Logger.getLogger(LocalLibrary.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
             Logger.getLogger(LocalLibrary.class.getName()).log(Level.SEVERE, null, ex);
         }
        return books;
    }

}
