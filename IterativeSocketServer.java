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
	                        System.out.println("Please enter port number...");
	                        return;
	                }//if

			//THE PLAYERS			
			String hostname = args[0];
	                int port = Integer.parseInt(args[0]);



			try (ServerSocket serv = new ServerSocket(port)){
				System.out.println("Server is listening on port#" + port + "...");

				//MORE PLAYERS
				Socket socket = serv.accept();
				System.out.println("...connected...");

				OutputStream out = socket.getOutputStream();
				PrintWriter write = new PrintWriter(out, true);
				InputStream in = socket.getInputStream();
				BufferedReader read = new BufferedReader(new InputStreamReader(in));
				char c = read.readLine().charAt(0);


				//Keeps time
				Instant s = Instant.now();

				while(true){


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
									while((str = rd0.readLine()) != null){
										System.out.print("successful execution");
										System.out.println(str);
										write.println(str);
									}//while
									//..OR ERROR
									while((str = rd1.readLine()) != null){
										write.println(str);
									}//while
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
					socket.close();
				}//while
			}catch(IOException ex){//try

				System.out.println("Server exception: " + ex.getMessage());
				ex.printStackTrace();
			}//catch
		}//main
	}//IterativeSocketServer
