import java.util.Hashtable;

public class Process {
public int ID;
public int arrivalTime;
public int pointer =0;
 String[] instructions;
 String temp;
 boolean userInputR=false;
 boolean userOutputR=false;
 boolean fileR=false;
 String path;
 boolean late;
 String oldIns;
 Hashtable<String , String > HT= new Hashtable<String, String>();

	public Process(String[] ins,int ariv, int ID) {
		this.ID=ID;
		instructions=ins;
		arrivalTime=ariv;
	}
	
	
	@Override
		public String toString() {
			// TODO Auto-generated method stub
			return "P"+ID;
		}
	

}
