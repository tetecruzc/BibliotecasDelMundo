/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tete
 */
import java.io.IOException;
import java.util.List;


public abstract class Library
{
    String name;
    Middleware interfaz;
    String alias;
    Logger logger;
    
    public Library(){
        this.logger = new Logger();
    }
    
    /* Métodos abstractos */
    public abstract String getBookByTitle(String title,  int transactionId);
    public abstract List<String> getBookByAuthor(String author, int transactionId);
    
    /* Se llama al logger para almacenar la petición realizada a las bibliotecas */
    public void saveLog(int transactionId, String content) throws IOException{
        this.logger.saveRequestMsg(transactionId, this.alias, content);
    }
}
