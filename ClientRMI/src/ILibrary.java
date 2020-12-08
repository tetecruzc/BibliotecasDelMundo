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
    public String getLocalBookByTitle(String title);
    public List<String> getLocalBookByAuthor(String author);
    public String getRemoteBookByTitle(String title);
    public String getRemoteBookByAuthor(String author);    
}