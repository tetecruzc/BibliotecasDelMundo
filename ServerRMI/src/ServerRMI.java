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
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ServerRMI extends UnicastRemoteObject implements Middleware{
    /* Tags para estandarizar búsqueda de libros. */
    enum Tags{
           book,
           name,
           author,
       }  
    Logger logger;
    
    public ServerRMI() throws RemoteException{
        super();
        this.logger = new Logger();
    }

    /* Retorna la lista de libros correspondientes en el archivo Books.xml */
    public NodeList getBookTagsList(String tag) {
        NodeList nodeList = null ;
            try{
             String url = "src/Database/Books.xml";
             File file = new File(url);
             DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
             DocumentBuilder db = dbf.newDocumentBuilder();
             Document document = db.parse(file);
             document.getDocumentElement().normalize(); 
             nodeList = document.getElementsByTagName(tag);
            }
            catch(Exception ex){
                 System.out.println(ex.getMessage());
            }
            return nodeList;
    }
    
    public String convertListToString(List<String> list){
         String allBooks = "";
        for(int i = 0; i< list.size(); i++){
            allBooks= allBooks + " - " + list.get(i);
        }  
        return allBooks;
    }

    /* Retorna el libro encontrado */
    /* Monitor de zona excluida */
    public synchronized Book getBook(String tag, String name, NodeList nodeList) throws InterruptedException{
          System.out.println("Buscando libro por título");
        //  System.out.println("Ha llegado una nueva petición!");
        //  Thread.sleep(10000);
        //  System.out.println("Despues de dormirse");
          Book book = new Book();
             for(int i=0;i<nodeList.getLength();i++){
                 Node node = nodeList.item(i);
                 Element element = (Element) node;
                  String elementLowerCase = element.getElementsByTagName(tag).item(0).getTextContent().toLowerCase();
                 if (elementLowerCase.contains(name.toLowerCase())){
                     book.title = element.getElementsByTagName(tag).item(0).getTextContent();
                 }
             }
             return book;
    }
    
    /* Retorna todos los libros encontrados, del autor dado */
    /* Monitor de zona excluida */
    public synchronized List<String> getBooksByAuthor(String author, String tag, NodeList nodeList) throws InterruptedException{
        System.out.println("Buscando libro por autor");
        List<String> books = new ArrayList<String>(); 
        //  System.out.println("Ha llegado una nueva petición!");
        //  Thread.sleep(10000);
        //  System.out.println("Despues de dormirse");
         for(int i=0;i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
                 Element element = (Element) node;
                 String elementLowerCase = element.getElementsByTagName(tag).item(0).getTextContent().toLowerCase();
                 if (elementLowerCase.contains(author.toLowerCase())){
                    books.add("Libro "+element.getElementsByTagName(Tags.name.toString()).item(0).getTextContent());
                 }
         }
           
         return books;
    }
   
    public String startSearchBook(int transactionId, String library, String message, String title){
        Book book = null;

         try{
            this.logger.saveRequestMsg(transactionId, library, message + title);
            NodeList nodeList = getBookTagsList(Tags.book.toString());         
            book = getBook(Tags.name.toString(),title ,nodeList); 
            this.logger.saveResponseMsg(transactionId,library, book.title);
         }
         catch(IOException ex){
             System.out.println(ex.getMessage());
         }
         catch(Exception ex){
            System.out.println(ex.getMessage());
         }
         return "Libro "+book.title;
    }
    

    /* Lenguaje natural de la biblioteca A : pedirLibro() */
    /* Retorna el libro dado un título */
    @Override 
    public String pedirLibro(String title, String library, int transactionId) throws RemoteException{
        String book = "";
        
          book = this.startSearchBook(transactionId,library, "Pedir libro : ",title);
       return book;
    }
    
    /* Lenguaje natural de la biblioteca A : pedirAutor() */
    /* Retorna los libros encontrados dado un autor */
    @Override
    public List<String> pedirAutor(String author, String library, int transactionId) throws RemoteException {
        List<String> books = new ArrayList<String>();
        try{
            this.logger.saveRequestMsg(transactionId, library, "Pedir autor "+ author);
            NodeList nodeList = getBookTagsList(Tags.book.toString());         
            books = this.getBooksByAuthor(author, Tags.author.toString(), nodeList);                     
           this.logger.saveResponseMsg(transactionId,library,convertListToString(books)); 
        }
        catch(IOException ex){
             System.out.println(ex.getMessage());
        }
        catch(Exception ex){
           System.out.println(ex.getMessage());
        }
        return books;
    }

    /* Lenguaje común Z39 */
    /* Llama al lenguaje natural de la biblioteca local. pedirLibro() */
    @Override
    public String getTitle(String title, String alias, int transactionId) throws RemoteException{
        String book = "";
        book = this.startSearchBook(transactionId, alias, "Get title : ", title);
       return book;
    }

    /* Lenguaje común Z39 */
    /* Llama al lenguaje natural de la biblioteca local. getBooksByAuthor() */
    @Override
    public List<String> getAuthor(String author, String library, int transactionId) throws RemoteException {
        List<String> books = new ArrayList<String>();
        try{
            this.logger.saveRequestMsg(transactionId, library, "Get author "+ author);
            NodeList nodeList = getBookTagsList(Tags.book.toString());         
            books = this.getBooksByAuthor(author, Tags.author.toString(), nodeList);
            this.logger.saveResponseMsg(transactionId,library,convertListToString(books)); 
        }
        catch(Exception ex){
           System.out.println(ex.getMessage());
        }
        return books;
    }
    
    /* Método principal del servidorRMI */
    /* Se instancia el registro RMI con el nombre RemoteRMI_A */
    public static void main(String[] args) throws IOException{
        try{
               java.lang.System.setProperty("java.rmi.server.hostname", "10.2.126.75");
               Registry registro = LocateRegistry.createRegistry(7778);
               registro.rebind("RemoteRMI_A", new ServerRMI());
               System.out.println("SERVIDOR ACTIVO");           
        }
        catch(RemoteException ex){
                System.out.println(ex.getMessage());
        }
    }
}

