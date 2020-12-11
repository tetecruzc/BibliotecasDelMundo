
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alley
 */
public class Logger {
     String name="src/logs/logs.txt";
     File file;
     
     public Logger(){
       this.file = new File(name);
     }
     
     private void createFile() throws IOException{
         this.file.createNewFile();
     }
     
     /* Calcula la fecha actual del servidor */
     private String calculateDate(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
     }
     
     /* Registra la petición realizada por el cliente. */
     public void saveRequestMsg(int transactionId, String library, String content) throws IOException{
        String date = this.calculateDate();        
        String msg =  "[" + date + "]" + " | "+ transactionId + " | "+ "Petición enviada a la "+ library + ": "+ content;
        this.saveRequest(msg);
     }


     /* Almacena la información construida en el archivo de texto */
     /* Crea el archivo de texto de no existir */
     private void saveRequest(String msg) throws IOException{
         if (!this.file.exists()){
             this.createFile();
             System.out.print("creando file");
         }  
              FileWriter fw = new FileWriter(this.file, true);
              PrintWriter pr = new PrintWriter(fw);
              pr.println(msg);
              pr.close();
              fw.close();   
     } 
}
