import java.net.*;
import java.io.*;
	public class IterativeSocketServer{
	        public static void main(String []args){
	                if(args.length < 0x1){
	                        System.out.println("Please enter port number...");
	                        return;
	                }//if
					                
			String hostname = args[0];
	                int port = Integer.parseInt(args[0]);

			try (ServerSocket serv = new ServerSocket(port)){
				System.out.println("Server is listening on port#" + port + "...");
				while(true){
					Socket socket = serv.accept();
					System.out.println("...connected...");
					OutputStream out = socket.getOutputStream();
					PrintWriter write = new PrintWriter(out, true);
				}//while
			}catch(IOException ex){//try

				System.out.println("Server exception: " + ex.getMessage());
				ex.printStackTrace();
			}//catch
		}//main
	}//IterativeSocketServerimport java.net.*;
