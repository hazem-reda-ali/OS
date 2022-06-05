import java.io.IOException;
import java.util.*;

public class OS {
public static Queue<Process> GeneralBlocked= new LinkedList<>();
public static Queue<Process> Ready= new LinkedList<>();

public static int currentT=0;
public static int timeSlice=2;//adds by 2
static Process P1;
static Process P2;
static Process P3;
static Mutex userInput;
static Mutex userOutput;
static Mutex file;

	public OS() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		//read
		//scheduler
		String[] A= new String [1000];
		String[] B= new String [1000];
		String[] C= new String [1000];
		try {
			A = SystemCaller.readfile("Program_1.txt");
			B = SystemCaller.readfile("Program_2.txt");
			C = SystemCaller.readfile("Program_3.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//create process
		 P1 = new Process(A,0,1);
		 P2= new Process(B,1,2);
		 P3 = new Process(C,4,3);
		//initializing three resources
		 userInput=new Mutex();
		 userOutput=new Mutex();
		 file=new Mutex();
		
		timeLine();
				
	}
	public static void timeLine() {
		System.out.println("------------------------------");
		System.out.println("Time= "+ currentT);
		if(currentT==P1.arrivalTime) {
			Ready.add(P1);
			System.out.println("p1 added to ready");
			displayQueues(Ready,GeneralBlocked);
		}
		if(currentT==P2.arrivalTime) {
			Ready.add(P2);
			System.out.println("p2 added to ready");
			displayQueues(Ready,GeneralBlocked);
		}
		if(currentT==P3.arrivalTime) {
			Ready.add(P3);
			System.out.println("p3 added to ready");
			displayQueues(Ready,GeneralBlocked);
		}
		currentT++;
		if(!Ready.isEmpty()) {
		
		
		scheduler();
		}else {
			System.out.println("DONE!");
		}
	}

	
	public static void scheduler() {
		
//		System.out.println("entered scehduler!");
//		displayQueues(Ready,GeneralBlocked);
		Process g=Ready.remove();
		
		int GBSize=GeneralBlocked.size();
		int loop=timeSlice;
		System.out.println("P"+g.ID+" is getting executed");
		while(loop>0 && g.instructions[g.pointer]!=null) {
			if(g.late) {
				System.out.println(g.oldIns+"(continued)");
				Interpeter.assign(g.path,g.temp,g);
				g.late=false;
				loop--;
				continue;
				//-------------------------------------------
			}
			String[] x=g.instructions[g.pointer].split(" ");
			if(x.length==2) {
				System.out.println(x[0]+" " +x[1]+" is getting executed!");
			}else if(x.length==3) {
				System.out.println(x[0]+" " +x[1]+" "+x[2]+" is getting executed!");
			}
			else if(x.length==4) {
				System.out.println(x[0]+" " +x[1]+" "+x[2]+" "+x[3]+" is getting executed!");
			}
			
//			System.out.println(x[0]+" " +x[1]+" is getting executed!");
			
			switch (x[0]) {
//			 semWait
			case "semWait":
				if(x[1].contentEquals("userInput")) {
					if(!userInput.semWait(g)){
						loop=0;	
						displayQueues(Ready,GeneralBlocked);
					}
				}else
					if(x[1].contentEquals("userOutput")) {
						if(!userOutput.semWait(g)){
							loop=0;
							displayQueues(Ready,GeneralBlocked);
						}
					}else
						if(x[1].contentEquals("file")) {
							if(!file.semWait(g)) {
								loop=0;	
								displayQueues(Ready,GeneralBlocked);
							}	
						}
				break;
//				semSignal
			case"semSignal":
				if(x[1].contentEquals("userInput")) {
					userInput.semSignal(g);
	
				}else
					if(x[1].contentEquals("userOutput")) {
						userOutput.semSignal(g);
					
					}else
						if(x[1].contentEquals("file")) {
							file.semSignal(g);
						}
						
				break;
//			assign
			case"assign":
				if(x[2].contentEquals("input")) {
					g.temp=Interpeter.input();
					
					if(loop>1) {
						Interpeter.assign(x[1],g.temp,g);
						loop--;
	
					}else {
					g.oldIns=x[0]+" " +x[1]+" "+x[2]+" is getting executed!";
					g.late=true;
					g.path=x[1];
						
					}
				}else
					if(x[2].contentEquals("readFile")) {
						try {
							g.temp=Interpeter.readfile(x[3], g);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(loop>1) {
							Interpeter.assign(x[1],g.temp,g);
							loop--;
		
						}else {
						g.oldIns=x[0]+" " +x[1]+" "+x[2]+" "+x[3]+" is getting executed!";
						g.late=true;
						g.path=x[1];
							
						}
					}
		
				break;
//				printFromTo 
			case"printFromTo":
				Interpeter.printfromto(x[1], x[2], g);		
				break;
				
//				print 
			case"print":
				Interpeter.print(x[1],g);		
				break;
//				writefile
			case"writeFile":
				try {
					Interpeter.writeFile (x[1],x[2],g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				break;
	
			}
			
		
//			System.out.println("ana ro7t a3mel pointer ++");
			g.pointer++;
			loop--;
			if(g.instructions[g.pointer]==null) {
				System.out.println("process "+g.ID+" is fininshed ");
				displayQueues(Ready,GeneralBlocked);
			}
		}
		
		
		if(GeneralBlocked.size()<=GBSize) {
			if(g.instructions[g.pointer]!=null) {
			Ready.add(g);
//			displayQueues(Ready,GeneralBlocked);
//			System.out.println("da5alt ashoofa");
			}
				
		}
		timeLine();
		
		
	}
	public static void displayQueues(Queue<Process> rq,Queue<Process> bq) {
		System.out.print("ready queue: ");
		System.out.println(rq);
		System.out.print("blocked queue: ");
		System.out.println(bq);


	}

}
