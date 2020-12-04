
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
public abstract class Client {
    String name;
    Middleware interfaz;

    public abstract String getBookByTitle(String title);
    public abstract List<String> getBookByAuthor(String author);
}
