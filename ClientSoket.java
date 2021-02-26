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

		Scanner user = new Scanner(System.in);
		
		System.out.println("Enter The number of threads");
  
	        int numOfthread = user.nextInt();
	        
	        for(int i = 0; i < numOfthread; i++) {
	        	
	        	Thread thrd = new Thread(new Client());
	        	thrd.start();
	        }//for
	        
	        user.close();
        
	}//main
}//ClientSoket



class Client implements Runnable{

	@Override
	public synchronized void run() {
		try(Socket socket = new Socket("139.62.210.153" , 4269)){

	    	System.out.println("Sending request to Socket Server");

	    	// gets the input stream from the server
	    	InputStream input = socket.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	       	PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
	       	
	       	
	       	long time = System.currentTimeMillis();
	       	
		writer.println('1');//Date
		
		
		
	        String recive = reader.readLine();
		System.out.println(recive);
		
		long time2 = System.currentTimeMillis();

		try{
			Thread.sleep(10);
		}catch(InterruptedException e){
			System.out.println("Could not complete");
		};

		writer.println('2');//Uptime

		try{
			Thread.sleep(10);
		}catch(InterruptedException e){
			System.out.println("Could not complete");
		};

		writer.println('3');//free

		try{
			Thread.sleep(40);
		}catch(InterruptedException e){
			System.out.println("Could not complete");
		};

		writer.println('4');//netstat

		try{
			Thread.sleep(40);
		}catch(InterruptedException e){
			System.out.println("Could not complete");
		};

		writer.println('5');//w

		try{
			Thread.sleep(40);
		}catch(InterruptedException e){
			System.out.println("Could not complete");
		};

		writer.println('6');//ps

		try{
			Thread.sleep(40);
		}catch(InterruptedException e){
			System.out.println("Could not complete");
		};

		writer.println('0');//d/c


		while((recive = reader.readLine()) != null){
	        	System.out.println(recive);
		}//while


	         
	         long Delay = time2 - time;
			 System.out.println("The delay is: " + Delay + "ms");
		 
	         input.close();//d/c actually

	}catch (UnknownHostException ex){
	System.out.println("Server not found->" + ex.getMessage());
}catch (IOException ex){
	System.out.println("I/O error: " + ex.getMessage());
}//catch
		
		



		
		
	}//main
	
}







