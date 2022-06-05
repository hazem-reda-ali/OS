import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SystemCaller {

	public SystemCaller() {
		// TODO Auto-generated constructor stub
	}

	public static  String[] readfile(String path) throws IOException{
	    String[] result = new String [1000];
	        BufferedReader  br=new BufferedReader(new FileReader(path));
	        String st;
	        int i=0;
	        while((st=br.readLine())!=null){
	            result[i]=st;
	            i++;
	        }
	    return result;
	}
	public static void writeFile(String filename, String data) throws IOException {
		try {
			 
			FileWriter myWriter = new FileWriter(filename);
			myWriter.write(data);
			myWriter.close();
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	
	
	}
	public static void print(String value) {
		System.out.println(value);
	}
	public static String input(){
		System.out.println("Enter value");
	    Scanner sc=new Scanner(System.in);
	    String str=sc.nextLine();
	    return str;
	}
	

}
