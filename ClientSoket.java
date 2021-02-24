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
	        }
	        
	        user.close();
        
	}//main
}//ClientSoket



class Client implements Runnable{

	@Override
	public synchronized void run() {
	int flag = 0;
	while(flag==0){	
		try(Socket socket = new Socket("139.62.210.153" , 4269)){

	    	System.out.println("Sending request to Socket Server");

	    	// gets the input stream from the server
	    	InputStream input = socket.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	       	PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
		writer.println('1');
	        String recive = reader.readLine();
		System.out.println(recive);

		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			System.out.println("SHIT");
		};
		writer.println('2');
		writer.println('3');
		writer.println('4');
		writer.println('5');
		writer.println('6');
		writer.println('0');

	         recive = reader.readLine();
	         System.out.println(recive);

	         input.close();
		 flag++;

	}catch (UnknownHostException ex){
	System.out.println("Server not found->" + ex.getMessage());
}catch (IOException ex){
	System.out.println("I/O error: " + ex.getMessage());
}
}//while
		
		
		
		
	}
	
}







