import java.util.*;
public class Mutex{

	Queue<Process> Blocked= new LinkedList<>();
	int available=1;
	Process holder = null;
	public Mutex() {
		// TODO Auto-generated constructor stub
	}

	
	public void semSignal(Process p) {
		if(holder.equals(p)) {
			if(this.Blocked.isEmpty()) {
				available = 1;
				holder=null;
			}else {
				Process np = this.Blocked.remove();
				holder=np;
				OS.GeneralBlocked.remove(np);
				OS.Ready.add(np);
			}
			
		}	
	}

	public boolean semWait(Process p) {
		boolean taken;
		if(available==1) {
			holder=p;
			available=0;
			taken = true;
		}
		else {
			this.Blocked.add(p);
			OS.GeneralBlocked.add(p);
			taken = false;
			}
		return taken;
	
	}
}
