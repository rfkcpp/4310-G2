
import java.io.*;
import java.util.Scanner;

public class Driver {

	
	
	
	
	public static void main(String[] args) {
		
		
		//process arrival time interval
		final int K=1000;
		
		//variables for holding simulation parameters
		int average;
		int standard_deviation;
		int number_of_processes;
		
		//a Scanner for getting simulation parameters from user
		Scanner s= new Scanner(System.in);
		
		//getting the simulation parameters from the user
		System.out.println("Process Arrival Time Interval is:" + K);
		System.out.println("Enter the following simulation parameters");
		System.out.print("Total Number Of Processes: ");
		number_of_processes=s.nextInt();
		System.out.print("Average Total CPU time(d): ");
		average = s.nextInt();
		
		//setting standard deviation to 20% of average total CPU time
		standard_deviation = (average*30)/100;
		
		
		
		
		OsSystem mySystem = new OsSystem(K,average,standard_deviation,number_of_processes);
	
		double fifo_att = mySystem.fifo_scheduler();
		double sjf_att = mySystem.srj_scheduler();
		double srt_att = mySystem.srt_scheduler();
		
		System.out.println("FIFO ATT is: "+fifo_att+ "     d/ATT is: "+ (average/fifo_att));
		
		System.out.println("SJF ATT is: "+sjf_att+"     d/ATT is: "+ (average/sjf_att));
		
		System.out.println("SRT ATT is: "+srt_att+"     d/ATT is: "+ (average/srt_att));
		
	
	
	
	
	
	}//end of main



}//end of Driver class
