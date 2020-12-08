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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ServerRMI extends UnicastRemoteObject implements Middleware{
       enum Tags{
           book,
           name,
           author,
       }
    
    public ServerRMI() throws RemoteException{
        super();
    }

    public NodeList getTag(String tag) {
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
    
    public Book getBook(String tag, String name, NodeList nodeList){
          Book book = new Book();
             for(int i=0;i<nodeList.getLength();i++){
                 Node node = nodeList.item(i);
                 Element element = (Element) node;
                  String elementLowerCase = element.getElementsByTagName(tag).item(0).getTextContent().toLowerCase();
                 if (elementLowerCase.contains(name.toLowerCase())){
                     book.title = element.getElementsByTagName(tag).item(0).getTextContent();
                 }
             }
            // System.out.println("EL LIBRO "+book.title); 
             return book;
    }
    
    public  List<String> getBooksByAuthor(String author, String tag, NodeList nodeList){
        List<String> books = new ArrayList<String>(); 
        
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
    
    @Override 
    public String pedirLibro(String name) throws RemoteException{
        Book book = null;
         try{
            NodeList nodeList = getTag(Tags.book.toString());         
            book = getBook(Tags.name.toString(),name,nodeList);          
         }
         catch(Exception ex){
            System.out.println(ex.getMessage());
         }   
         return "Libro "+book.title;
    }
    
    @Override
    public List<String> pedirAutor(String author) throws RemoteException {
        List<String> books = new ArrayList<String>();
        try{
            NodeList nodeList = getTag(Tags.book.toString());         
            books = this.getBooksByAuthor(author, Tags.author.toString(), nodeList);
        }
        catch(Exception ex){
           System.out.println(ex.getMessage());
        }
        return books;
    }

    @Override
    public String getTitle(String name) throws RemoteException{
       return this.pedirLibro(name);
    }

    @Override
    public List<String> getAuthor(String title) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public static void main(String[] args) throws IOException{
        try{
               Registry registro = LocateRegistry.createRegistry(7778);
               registro.rebind("RemoteRMI_A", new ServerRMI());
               System.out.println("SERVIDOR ACTIVO");           
        }
        catch(RemoteException ex){
                System.out.println(ex.getMessage());
        }
    }
}

