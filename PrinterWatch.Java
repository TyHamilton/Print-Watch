
package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;



/**
 *
 * @author tyhamilton
 */
public class PrinterWatch {
	
	static int converted(String a){
		String number = a.trim();
		int result = Integer.parseInt(number);
		return result;
	};
	
    static ArrayList<String> infoP = new ArrayList<String>();
    static void textDisplay(String a){
        System.out.println();
    };
    
    static void pullData(LexPrint a) throws MalformedURLException, IOException{
    	//this method scrapes the HTML file on a Lexmark printer after being fed the printer object
    	infoP.clear();
        //this creates a string for the full address of where the data exists based off static data and the IP of the printer
    	String siteData = "";
        String d= "http://";
        String c= "/cgi-bin/dynamic/printer/PrinterStatus.html";
        String zed= d+a.getiP()+c;
        
        try{
            URL url = new URL(zed);
            Object content = url.getContent();
            
            BufferedReader in;
            in = new BufferedReader(new InputStreamReader((InputStream)content));
            
             char[] data = new char[10000]; 
             int index = 0;                
             while (true) {
                if (index == 10000)
                   break;
                int ch = in.read();
                if (ch == -1)
                   break;
                data[index] = (char)ch;
                index++;
             }
            for(char b: data){
            siteData += b;
            
            };
           //this was created to pull information from Lexmark printers HTML file location of data was known this is how it is found
            
            for(int i = 0; i<siteData.length();i++){
            if(siteData.charAt(i)=='%'){
            String cached = siteData.substring(i - 3, i);
            if(cached.charAt(0)=='t'){
            cached = cached.substring(1, cached.length());
            }else if(cached.charAt(0)=='>'){
            cached = cached.substring(1, cached.length());
            };
            
              infoP.add(cached);
            
            }
            
            };
            //turns the string from the HTML file in to a number for the data in the table as well assigns the number to the printer object

            int t = Integer.parseInt(infoP.get(0).substring(1));
            a.setToner(t);         
            int z= Integer.parseInt(infoP.get(34));
            a.setImage(z);
            int x= Integer.parseInt(infoP.get(33));
            a.setFuser(x);
            int y= Integer.parseInt(infoP.get(32));
            a.setK600(y);
            int u= Integer.parseInt(infoP.get(31));
            a.setK300(u);
     
        }catch(Exception e){
        System.out.println(e);};
        return ;
        
       
       
    }
    
      
    

    
}
