
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
public class LibraryBuilder {
    Client client;
    
    public LibraryBuilder(Client client){
        this.client = client;
    }
    
    public String getBookByTitle(String title){
        return this.client.getBookByTitle(title);
    }
    
    public List<String> getBookByAuthor(String author){
        return this.client.getBookByAuthor(author);
    }   
}
