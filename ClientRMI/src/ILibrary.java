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
public interface ILibrary {
    public String getLocalBookByTitle(String title, int transactionId);
    public List<String> getLocalBookByAuthor(String author, int transactionId);
    public String getRemoteBookByTitle(String title, int transactionId);
    public String getRemoteBookByAuthor(String author, int transactionId);
}