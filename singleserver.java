package sml;

import java.text.DecimalFormat;
import java.util.LinkedList;

public class singleserver{
	
	static LinkedList<Double> future_event = new LinkedList<Double>();
	static LinkedList<Double> arrivalList = new LinkedList<Double>();
	static LinkedList<Double> delayed_list = new LinkedList<Double>();
	static LinkedList<Double> object3 = new LinkedList<Double>();
	static long LAST = 1000;                   
	static double START = 0.0;                 
	static long   index     = 0;                         
    static double arrival  = START;                     
    static double delay,  service,wait,startser;                               
    static double departure = START;                     
    static double interarr = 0.0;
    static double idle = 0;
    static double sumidle = 0;
    static double timeinsystem = 0;
    static double idletime = 0;
	static double sumservice = 0;
	static double suminter = 0;
	static double sumtimesys = 0;
	static double totaldelay=0;
    static DecimalFormat f = new DecimalFormat("###0.00");  
		
	
	public static void main(String[] args) {
		
	    while(index < LAST) {
	    	index++;
	    	future_event.add(interArr()); 	
	    } 
	    index = 0; 
	    while (index < LAST) { 		      
		      interarr      = future_event.get((int) index);
		      arrival = arrival + interarr;
		      
		      if (arrival < departure) {
		        delay      = departure - arrival;        /*delay in queue*/
		       totaldelay += delay;
		      }
		      else 
		      delay      = 0.0;                        /*no delay*/     
		      
		      service      = serviceTime();
		      arrivalList.add(service);
		      wait         = delay + service;
		      startser 	   = arrival + delay;
		      departure    = arrival + wait;
		      
		    System.out.println("\t "+f.format(interarr)+" \t" +f.format(service)+"   \t  "+f.format(delay)+"  \t  "+f.format(departure)+"\t"); 
		    
		 
		      idle = waitcustomer(departure, index, arrival);
		      object3.add(idle);
		  
		      
		      sumidle += idle;
		      timeinsystem = (departure-arrival);
		      delayed_list.add(timeinsystem);
		      sumtimesys += timeinsystem;
		      sumservice += service;
		      suminter += interarr;
		      index++;
		      
	    }
	   System.out.println("...........................................................");
	   System.out.println("...........................................................");
	   System.out.println("...........................................................");
		System.out.println("The avg amount of service time : "+f.format(sumservice/1000));
	
		System.out.println("The avg interarrival time : "+f.format(suminter/1000));

		System.out.println("The avg amount of time in the system : "+f.format(sumtimesys/1000));
		
		System.out.println("The avg delay in system: " +f.format(totaldelay/1000));
	    
	}
	
	 static double waitcustomer( double k, long ind, double arrive) {
		   for( int j = ((int) ind+1); j < future_event.size(); j++ )
		   {
			  arrive = arrive + future_event.get(j);
			   
		      if( arrive < k ) 			    	  
		         System.out.println(" "+(j+1));
		      else
		    	  break;
		   }
		   int i = (int) (ind+1);
		   if (i<future_event.size())
			   idletime = (arrive+future_event.get(i)) - k; 
		   return idletime;
		}
	
	
	    static double interArr() {
			
			return ((double)(-Math.log(Math.random())*6));
			
		}
		
		static double serviceTime() {
			
			return ((double)(-Math.log(Math.random())*5));
			
	}

}


