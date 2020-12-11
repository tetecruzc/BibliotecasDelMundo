
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tete
 */

/* Realiza una abstracción de las librerías remotas y local. */ 
public class LibraryBuilder {
    Library client;
    Logger logger;

    public LibraryBuilder(Library client){
        this.client = client;   
    }

    public String getBookByTitle(String title, int transactionId){
        return this.client.getBookByTitle(title, transactionId);
    }
    
    public List<String> getBookByAuthor(String author, int transactionId){
        return this.client.getBookByAuthor(author,transactionId);
    }     
}
