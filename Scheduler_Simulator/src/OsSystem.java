import java.util.ArrayList;
import java.util.*;

public class OsSystem {
	
	
	//an array List which contains Processes
	private ArrayList<Process> processes;
	
	//a variable that holds the number of processes
	private int total_processes;
	
	//simulation parameters
	private int max_arrival_time;
	private int average;
	private int standard_deviation;
	
	
	
	
	//constructor
	public OsSystem(int k, int avg, int sd, int total_p) {
		
		processes = new ArrayList<Process>(total_p);
		
		max_arrival_time = k;
		average = avg;
		standard_deviation = sd;
		total_processes = total_p;
		
		
		//fill the processes ArrayList
		fill_processes();
		
		}//end of constructor
	
	
	
	//function deletes all processes and refills the processes array list
	public void refill() {
		processes = new ArrayList<Process>();
		fill_processes();
	}//end of refill
	
	
	//FIFO Scheduler -------------------------------------------------------------------------------------------------------------
	public double fifo_scheduler() {
		
		int current_time =-1;
		
		//holds the index of the running process at each current time  tick
		int running  = -1;
		
		
		
		while (!all_done()) {
			
			current_time+=1;
			
			
			
			if (running >=0) {
				processes.get(running).set_remaining_cpu_time(processes.get(running).get_remianing_cpu_time()-1);
				if (processes.get(running).get_remianing_cpu_time() == 0) {
					processes.get(running).set_active(false);
					processes.get(running).set_turnaround_time(current_time-processes.get(running).get_arrival_time());
				}//end if running process has finished
			}//end of if something has been running in last cycle
			
			
			//setting the processes active
			for (int i=0; i<total_processes; i++) {
				if (processes.get(i).get_remianing_cpu_time()!=0   &&  processes.get(i).get_arrival_time()<=current_time) {
					processes.get(i).set_active(true);
				}
			}
			
			
			//setting the index of running process at this cycle
			
				
			int smallest_arrival = max_arrival_time +10;
				
			for (int j=0; j<total_processes; j++) {
					
				if (processes.get(j).is_active()  && processes.get(j).get_arrival_time()<smallest_arrival) {
						running =j;
						smallest_arrival = processes.get(j).get_arrival_time();
				}
			}
			

		
		}//end of while (!all_done())
		
		
		//computing the average turnaround time
		double average_turnaround;
		double total_turnaround=0.0;
		for (int k =0; k<total_processes; k++)
			total_turnaround = total_turnaround+processes.get(k).get_turnaround_time();
		average_turnaround = total_turnaround/total_processes;
		
	
		
		return average_turnaround;
		
		
		
	}//end of fifo_scheduler() -------------------------------------------------------------------------------------------------
	
	
	
	
	//Shortest job first scheduler---------------------------------------------------------------------------------------------
	public double srj_scheduler() {
		
		int current_time =-1;
		
		//holds the index of the running process at each current time  tick
		int running  = -1;
		
		
		
		while (!all_done()) {
			
			current_time+=1;
			
			
			
			if (running >=0) {
				processes.get(running).set_remaining_cpu_time(processes.get(running).get_remianing_cpu_time()-1);
				if (processes.get(running).get_remianing_cpu_time() == 0) {
					processes.get(running).set_active(false);
					processes.get(running).set_turnaround_time(current_time-(processes.get(running).get_arrival_time()));
				}//end if running process has finished
			}//end of if something has been running in last cycle
			
			
			//setting the processes active
			for (int i=0; i<total_processes; i++) {
				if (processes.get(i).get_remianing_cpu_time()!=0   &&  processes.get(i).get_arrival_time()<=current_time) {
					processes.get(i).set_active(true);
				}
			}
			
			
			//setting the index of running process at this cycle
			
				
			if (running>=0 && processes.get(running).is_done()) {
				int shortest_cpu_time = Integer.MAX_VALUE;
				
				for (int j=0; j<total_processes; j++) {
						
					if (processes.get(j).is_active()  && processes.get(j).get_total_cpu_time()<shortest_cpu_time) {
							running =j;
							shortest_cpu_time = processes.get(j).get_total_cpu_time();
					}
				}
			}
			
			if (running<0) {
				int shortest_cpu_time = Integer.MAX_VALUE;
				
				for (int j=0; j<total_processes; j++) {
						
					if (processes.get(j).is_active()  && processes.get(j).get_total_cpu_time()<shortest_cpu_time) {
							running =j;
							shortest_cpu_time = processes.get(j).get_total_cpu_time();
					}
				}
			}
			

		
		}//end of while (!all_done())
		
		
		//computing the average turnaround time
		double average_turnaround;
		double total_turnaround=0.0;
		for (int k =0; k<total_processes; k++)
			total_turnaround = total_turnaround+processes.get(k).get_turnaround_time();
		average_turnaround = total_turnaround/total_processes;
		
	
		
		return average_turnaround;
		
		
		
	}//end of Shortest Job First Scheduler ---------------------------------------------------------------------------------------
	
	
	
	
	
	
	
	
	
	//this function fills the processes with random 
	private void fill_processes() {
		
		for (int i=0; i<total_processes; i++) {
			
			Process p = new Process(i, gaussian_random());
			
			p.set_arrival_time(generate_arrival_time());
			if (p.get_arrival_time()==0)
					p.set_active(true);
			
			p.set_remaining_cpu_time(p.get_total_cpu_time());
			
			p.set_turnaround_time(-1);
			
			processes.add(p);
		
			}
	
	}//end of fill_processes
	
	
	
	
	//Rick############################################################################################################
	
	
	//returns a random integer from Gaussian distribution with mean=average and standard deviation=standard_deviation
	private int gaussian_random() {
		
		Random ran = new Random();
		return (int)(ran.nextGaussian() * standard_deviation + average);
	}
	
	//returns a random integer between 0 and max_arrival_time
	// Range of Math.random -> 0.0 - 1.0
	// 0.0 * (max - min) + min => min
	// 1.0 * (max - min) + min => max - min + min => max
	private int generate_arrival_time() {
		
		return (int)(Math.random()*(max_arrival_time));
	}
	
	//Rick###############################################################################################################
	
	
	//this function returns true if all the processes are finished the execution
	private boolean all_done() {
		
		boolean result=true;
		
		for (int i=0; i<total_processes; i++) {
			if (!processes.get(i).is_done())
				return false;
		}
		
		return result;
		
	}//end of all_done
	
	
	

}//end of OsSystem class
