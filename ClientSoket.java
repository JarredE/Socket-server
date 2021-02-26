import java.io.*;
import java.net.*;
import java.util.Scanner;
/**
 * This class implements java socket client
 * @author Jarred E.
 *
 */
public class ClientSoket{

	public static void main(String[] args){
		if(args.length < 0x1){
			System.out.println("Formula: ip# port#\nTry Again...");
			return;
		}//if


		Scanner user = new Scanner(System.in);
		
		System.out.println("Enter The number of threads");
  
	        int numOfthread = user.nextInt();
	        
	        for(int i = 0; i < numOfthread; i++) {	        	
	        	Thread thrd = new Thread(new Client(args[0], args[1]));
	        	thrd.start();
	        }//for
	        
	        user.close();
        
	}//main
}//ClientSoket



class Client implements Runnable{
	String str[] = new String[2];
	//str[0] = ip
	//str[1] = port

	Client(String ip, String port){
		str[0] = ip;
		str[1] = port;
	}//Client

	@Override
	public synchronized void run() {
		try(Socket socket = new Socket(str[0], Integer.parseInt(str[1]))){

		    	System.out.println("Sending request to Socket Server");

		    	// gets the input stream from the server
	    		InputStream input = socket.getInputStream();
		        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		       	PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
	       	
	       		long time[] = new long[10];
		       /*long*/ time[0] = System.currentTimeMillis();
	       	
			writer.println('1');//Date
		
		
		
		        String recive = reader.readLine();

		
			/*long*/ time[1] = System.currentTimeMillis();

			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				System.out.println("Could not complete");
			};

			//PRINT
			System.out.println(recive);

			time[2] = System.currentTimeMillis();
			writer.println('2');//Uptime

			time[3] = System.currentTimeMillis();
			writer.println('3');//free

			time[4] = System.currentTimeMillis();
			writer.println('4');//netstat

			time[5] = System.currentTimeMillis();
			writer.println('5');//w

			time[6] = System.currentTimeMillis();
			writer.println('6');//ps

			writer.println('0');//d/c

			while((recive = reader.readLine()) != null){
	        		System.out.println(recive);
			}//while

		        input.close();//d/c actually

			System.out.println("The delay is: " + (time[1] - time[0])/*delay*/ + "ms");

			//PROCESS
			time[8] = System.currentTimeMillis();//Finish time
			//turaround times
			time[1] = time[8] - time[1];
			time[2] = time[8] - time[2];
			time[3] = time[8] - time[3];
			time[4] = time[8] - time[4];
			time[5] = time[8] - time[5];
			time[6] = time[8] - time[6];

			System.out.println("\n---Turnaround Times---");
			for(int x = 1; x < 7; x++){
				System.out.println("P" + x + " = " + time[x] + "ms");
				time[9] += time[x];
			}//for
			System.out.println("\nTotal Turnaround Time   = " + time[9] + "ms\nAverage Turnaround Time = " + time[9]/6 + "ms");
		}catch (UnknownHostException ex){
			System.out.println("Server not found->" + ex.getMessage());
		}catch (IOException ex){
			System.out.println("I/O error: " + ex.getMessage());
		}//catch		
	}//run()
	
}//Runnable()







