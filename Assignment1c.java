// Assignment 1 
// **
// **
package assignment1c;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shahril Dizal Don (2014776123)
 */
public class Assignment1c {

  private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static void main(String[] args) throws FileNotFoundException {
/*
 * Write a program to write data into a file Yourname.txt
 * Your Name (new line)
 * Address (new line)
 */
        
        String fileName = "Yourname.txt";

        try {
            
            FileWriter fileWriter =
                new FileWriter(fileName);
            
            try ( 
                   BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                
                bufferedWriter.write("Name: Shahril Dizal");
                bufferedWriter.newLine();
                bufferedWriter.write("Address: Kota Damansara, Petaling Jaya, Selangor");
                
            }
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
            
        }
  /*  
   * Write a program to read a file (Yourname.txt) and then write binary to file
   * name Yourname.bin
   */
        FileWriter out = null;        
       
        File file = new File("Yourname.txt");
        
        Scanner sc = new Scanner(file);
        String binToString = "";
        
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            byte[] bytes = line.getBytes(UTF_8);
            for(byte b : bytes){
                binToString += Integer.toBinaryString(b & 127 | 128).substring(1);
            }
            binToString += System.lineSeparator();
        }
        //write in binary to Yourname.bin
        try {
            out = new FileWriter("Yourname.bin");
            out.write(binToString);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Assignment1c.class.getName()).log(Level.SEVERE, null, ex);
        }
  /*     
   * Write a program to read(yourname.bin) and write it back in text mode(last.txt)
   */     
        File file2 = new File("Yourname.bin");
        
        Scanner sc2 = new Scanner(file2);
        
        String StoB = "";
        while(sc2.hasNextLine()){
            String line = sc2.nextLine();
            int interval = 7;
            int arrayLength = (int) Math.ceil(((line.length()/(double)interval)));
            String[] result = new String[arrayLength];
            
            int j = 0;
            int lastIndex = result.length - 1;
            for(int i = 0; i<=lastIndex; i++){
                result[i] = line.substring(j, j+interval);
                StoB +=  (char)Integer.parseInt( result[i], 2 );
                j += interval;
            }
            result[lastIndex] = line.substring(j);
            StoB += System.lineSeparator();
        }
        //write the binary to text to Last.txt
        try {
            out = new FileWriter("Last.txt");
            out.write(StoB);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Assignment1c.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
