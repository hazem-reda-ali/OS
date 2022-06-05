import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;
public class Interpeter {
	



public static void printfromto(String A, String B, Process p) {
	int a;
	int b;
	String S1=p.HT.get(A);
	String S2=p.HT.get(B);
	
	if(S1!=null) {
		A=S1;
	
	}
	if(S2!=null) {
		B=S2;
	}
      a=Integer.parseInt(A);
      b=Integer.parseInt(B);
	for (int i = a +1; i < b; i++) {
		System.out.print(i+" ");
	}	
       System.out.println("");
	
}





public static  String readfile(String data,Process p) throws IOException{

	
	 String S1=p.HT.get(data);
     if(S1!=null) {
     	data=S1;
     }
     String[] arr = SystemCaller.readfile(data);
     String res = "";
     for(String s : arr) {
    	 if(s==null)
    		 break;
    	 res+=s+"\n";
     }
    	
     return res;	
}
public static void writeFile(String filename, String data,Process p) throws IOException {
	          
				String S1=p.HT.get(filename);
	            String S2=p.HT.get(data);
	            if(S1!=null) {
	            	filename=S1;
	            }
	            if(S2!=null) {
	            	data=S2;
	            }
	            
 try {
	 SystemCaller.writeFile(filename,data);
	} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
	}
}
	    
	


public static void assign(String variable, String value,Process p) {
	String S1=p.HT.get(value);
	if(S1!=null) {
		value=S1;
	}
	p.HT.put(variable, value);
		
}

public static String input(){
    return SystemCaller.input();
    
}


public static void print(String value,Process p) {
	String S1=p.HT.get(value);
	if(S1!=null) {
		value=S1;
	}
	
	SystemCaller.print(value);
	
}


}