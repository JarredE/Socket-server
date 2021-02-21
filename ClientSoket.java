//package pkg1;
import java.io.*;
import java.net.*;

/**
 * This class implements java socket client
 * @author Jarred E.
 *
 */
public class ClientSoket{

	public static void main(String[] args){
    		if(args.length < 2)
			return;

        //get the localhost IP address, if server is running on some other IP, you need to use that
        //This implies that the server is listening locally
	//InetAddress host = InetAddress.getLocalHost();
	
		//PLAYERS
		String host = args[0];
		int port = Integer.parseInt(args[1]);
        
        	
        	    //establish socket connection to server
	            try(Socket socket = new Socket(host, port)){
            
        	    	System.out.println("Sending request to Socket Server");
            
        	    //read the server response message
	            InputStream ois = socket.getInputStream();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(ois));

		    String message = reader.readLine();

	            //String message = (String) ois.readObject();
            
	            System.out.println("Message: " + message);
            
        	    //close resources
	            ois.close();

	    	}catch (UnknownHostException ex){
			System.out.println("Server not found->" + ex.getMessage());
		}catch (IOException ex){
			System.out.println("I/O error: " + ex.getMessage());
		}//catch
            
	}//main
}//ClientSoket
