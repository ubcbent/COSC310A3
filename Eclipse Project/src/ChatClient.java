import java.net.*;
import java.io.*;
import org.alicebot.ab.*;

public class ChatClient {
	
	public static int TCPMessagePort = 1248;
	public static String address = "localhost";
	
	public Socket messageSocket;
	
	public static void main(String[] args) {
		
		//ChatClient client = new ChatClient();
		//client.execute();
		
		
		try {
			Socket socket = new Socket(address,TCPMessagePort);
			DataOutputStream request = new DataOutputStream(socket.getOutputStream());
			BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//initialize yebot
			YeBot client = new YeBot();
			client.initialize();
			client.session = new Chat(client.yebot);
			
			request.writeBytes("Ye is in the BUILDING!"); //run this only if we are the first
			System.out.println("Kanye: Ye is in the BUILDING!"); // same with this
			// first message from other bot
			String reply = response.readLine();
			while(true) {
				System.out.println("Buddy: "+reply);
				String send = client.getResponse(reply);
				System.out.println("Kanye: "+send);
				request.writeBytes(send);
				reply = response.readLine();
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}

