import java.net.*;
import java.io.*;
import java.lang.Object;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Locale;
import java.time.Instant;
import java.time.Duration;
//Listen on ports 1025-4998
	public class IterativeSocketServer{
	        public static void main(String []args){
	                if(args.length < 0x1){
	                        System.out.println("Formula: port#...aborting...");
	                        return;
	                }//if

			//THE PLAYERS
			//args[0] == port number

			Thread server = new Thread(new Server(Integer.parseInt(args[0])));
			server.run();
		
		}//main

	}//IterativeSocketServer

	class Server implements Runnable{

		int port;

		Server(int newPort){
			port = newPort;
		}//Server

		@Override
		public synchronized void run(){

			try (ServerSocket serv = new ServerSocket(port)){
				System.out.println("Server is listening on port#" + port + "...");

				//Keeps time
				Instant s = Instant.now();


				
				while(true){
					//MORE PLAYERS
					Socket socket = serv.accept();
					System.out.println("...connected...");

					Thread listen = new Thread(new ear(socket, s));
					listen.start();

				}//while

			}catch(IOException ex){//try
				System.out.println("Server exception: " + ex.getMessage());
				ex.printStackTrace();
			}//catch
		}//run

	class ear implements Runnable{
		Socket socket;
		Instant s;

		ear(Socket newSocketRef, Instant newS){
			socket = newSocketRef;
			s = newS;
		}//ear

		@Override
		public synchronized void run(){

			try{
					OutputStream out = socket.getOutputStream();
					PrintWriter write = new PrintWriter(out, true);
					InputStream in = socket.getInputStream();
					BufferedReader read = new BufferedReader(new InputStreamReader(in));

					char c = read.readLine().charAt(0);

					while(c != '0'){//0 == disconnect
						switch(c){
							case '1'://date & time
								TimeZone tz = TimeZone.getTimeZone("EST");
								Locale lo = new Locale("ENGLISH");
								Calendar cal = Calendar.getInstance(tz,lo);
								write.println(cal.getTime().toString());
								break;
							case '2'://uptime
								Instant brk = Instant.now();
								Duration time = Duration.between(s,brk);
								write.println("DAYS: " + time.toDays() + " HOURS: " + time.toHours() + " MIN: " + time.toMinutes() + " SECS: " + time.toMillis() / 1000);
								break;
							case '3'://Memory Usage
								try{
									//MORE PLAYERS
									Process p = Runtime.getRuntime().exec("free");
									BufferedReader rd0 = new BufferedReader(new InputStreamReader(p.getInputStream()));
									BufferedReader rd1 = new BufferedReader(new InputStreamReader(p.getErrorStream()));
									String str = null;

									//EXECUTE
									//SUCCESS...
									while((str = rd0.readLine()) != null)
										write.println(str);
								}catch(IOException e){}
								break;
							case '4'://Netstat
								try{
									//MORE PLAYERS
									Process p = Runtime.getRuntime().exec("netstat");
									BufferedReader rd0 = new BufferedReader(new InputStreamReader(p.getInputStream()));
									String str = null;

									//EXECUTE
									while((str = rd0.readLine()) != null)
										write.println(str);
								}catch(IOException e){}
								break;
							case '5'://Current Users
								try{
								//MORE PLAYERS
									Process p = Runtime.getRuntime().exec("w");
									BufferedReader rd0 = new BufferedReader(new InputStreamReader(p.getInputStream()));
									String str = null;

									//EXECUTE
									while((str = rd0.readLine()) != null)
										write.println(str);
								}catch(IOException e){}
								break;
							case '6'://Running Processes
								try{
									//MORE PLAYERS
									Process p = Runtime.getRuntime().exec("ps");
									BufferedReader rd0 = new BufferedReader(new InputStreamReader(p.getInputStream()));
									String str = null;

									//EXECUTE
									while((str = rd0.readLine()) != null)
										write.println(str);
								}catch(IOException e){}
								break;
							default://nop
								write.println("UNKNOWN COMMAND");
								break;
						}//switch

						c = read.readLine().charAt(0);//read next in
					}//while
						write.println("Goodbye - AOL");
						System.out.println("...disconnected");
						socket.close();
			}catch(IOException ex){//try
				System.out.println("Server exception: " + ex.getMessage());
				ex.printStackTrace();
			}//catch

		}//run
	}//ear

}//Server
