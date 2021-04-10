
public class Process {
	
	private int id;
	private boolean active;
	private int arrival_time;
	private int total_cpu_time;
	private int remaining_cpu_time;
	private int turnaround_time;
	
	
	
	//constructor
	public Process(int my_id,int total_time) {
		
		id = my_id;
		total_cpu_time = total_time;
		active = false;
		
	}//end of constructor
	
	
	
	
	//setter methods ---------------------------------------------------------------------------
	
	public void set_active(boolean state) {
		active = state;
	}
	
	public void set_arrival_time(int time) {
		arrival_time = time;
	}
	
	public void set_remaining_cpu_time(int time) {
		remaining_cpu_time = time;
	}
	
	public void set_turnaround_time(int time) {
		turnaround_time = time;
	}
	
	//end of setter methods------------------------------------------------------------------------
	
	
	
	//getter methods ------------------------------------------------------------------------------
	
	public int get_id() {
		return id;
	}
	
	public boolean is_active() {
		return active;
	}
	
	public int get_arrival_time() {
		return arrival_time;
	}
	
	public int get_total_cpu_time() {
		return total_cpu_time;
	}
	
	public int get_remianing_cpu_time() {
		return remaining_cpu_time;
	}
	
	public int get_turnaround_time() {
		return turnaround_time;
	}
	
	//end of getter methods --------------------------------------------------------------------------
	
	
	//this function returns true if the process has been executed and finished
	public boolean is_done() {
		
		return remaining_cpu_time == 0;
	
	}//end of is_done

	public Process clone() {
		Process p = new Process(id,total_cpu_time);
		p.set_active(active);
		p.set_arrival_time(arrival_time);
		p.set_remaining_cpu_time(remaining_cpu_time);
		p.set_turnaround_time(turnaround_time);
		
		return p;
	}


}//end of Process class
