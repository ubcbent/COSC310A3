import java.net.*;
import java.io.*;
import org.alicebot.ab.*;

public class ChatClient {
	
	public static int TCPMessagePort = 1248;
	public static String address = "192.168.1.65"; // enter other bot's ip address
	
	public Socket messageSocket;
	
	public static void main(String[] args) {
		
		//ChatClient client = new ChatClient();
		//client.execute();
		
		
		try {
			//ServerSocket welcomeSocket = new ServerSocket(TCPMessagePort); //run these first two lines if we are second
			//Socket socket = welcomeSocket.accept();
			Socket socket = new Socket(address,TCPMessagePort); //run if we are first
			DataOutputStream request = new DataOutputStream(socket.getOutputStream());
			BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//initialize yebot
			YeBot client = new YeBot();
			client.initialize();
			client.session = new Chat(client.yebot);
			//to delay start
			try {
				Thread.sleep(3000);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			request.writeBytes("Ye is in the BUILDING!\n"); //run this only if we are the first
			System.out.println("Kanye: Ye is in the BUILDING!"); // same with this
			// first message from other bot
			String reply = response.readLine();
			while(true) {
				System.out.println("Buddy: "+reply);
				String send = client.getResponse(reply);
				System.out.println("Kanye: "+send);
				request.writeBytes(send+"\n");
				reply = response.readLine();
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}

