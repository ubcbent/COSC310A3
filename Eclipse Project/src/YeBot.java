import java.util.*;
import org.alicebot.ab.*;
import java.io.File;
import edu.smu.tspell.wordnet.*;
import java.util.ArrayList;
/**
 * YeBot is the main class where the conversation is held.
 */
public class YeBot {

	static Conversation conversation;			

	public static void main(String[] args) {
		//initialize
		String dir = new File(".").getAbsolutePath();
		System.out.println(dir.substring(0,dir.length()-2));
		MagicBooleans.trace_mode = false;
		Bot yebot = new Bot("YeBot",dir.substring(0,dir.length()-2));
		yebot.writeAIMLFiles();
		String ans;
		//initialize wordnet, requires that wordnet is installed in the correct dir
		System.setProperty("wordnet.database.dir", "C:\\Program Files (x86)\\WordNet\\2.1\\dict\\");
		WordNetDatabase database = WordNetDatabase.getFileInstance();
		//string array to contain the responses kanye has if he doesn't have a response
		ArrayList<String> unknown = new ArrayList<String>();
		unknown.add("wish i could help   i dont know what that means");
		unknown.add("you got good vibes   but i dont know what to say to that");
		unknown.add("yo man you gotta slow down   maybe try saying that a different way");
		
		Chat session = new Chat(yebot);
		conversation = new Conversation();

		String input = "test";
		String output;
		int i = 1;
			
		while(!conversation.isContained(input)){
			input = null;
			input = conversation.recieveInput();
			//System.out.println(input);
			
			if (input!=""&&input!=null&&input.length()>1||i==1) {
				if(input==""||input==null||input.length()<1) {
					//start conversation
					
					output = conversation.response("Ye is in the BUILDING!");
					i=0;	
				}
				else if(conversation.isContained(input)) {
					//user calls for exiting the conversation
					try {
						Thread.sleep(500);
						
						output = conversation.response(session.multisentenceRespond(input));
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				}
				else {
					//regular response
					// process synonyms if kanye has no response !REQUIRES WORDNET TO BE INSTALLED!
					String response = session.multisentenceRespond(input);
					if(unknown.contains(response)) {
						// kanye is confused, typical
						String[] words = input.split(" ");
						outerloop:
						for(int j = 0; j<words.length;j++) {
							Synset[] syns = database.getSynsets(words[j]);
							if(syns.length!=0) {
								for(Synset syn : syns) {
									String[] replace = syn.getWordForms();
									for(String r : replace) {
										String inputnew = input.replaceAll(words[j]+" ", r+" ");
										response = session.multisentenceRespond(inputnew);
										if(!unknown.contains(response)) {
											// if we find a response, break and output
											input = inputnew;
											break outerloop;
										}
									}
								}
							}
						}
					}
					output = conversation.response(response);
				}
			}	
		}
		System.exit(1); 	//This statement terminates the program	
		
			//Do you want to start the conversation over? make sure you get valid input (Done)
//			ans = conversation.response("Do you want to start our conversation over? (Y/N)"); 
//			while(ans != null && !ans.toUpperCase().equals("Y") && !ans.toUpperCase().equals("N"))
//				ans = conversation.response("Invalid input. Do you want to start our conversation over? (Y/N)");
		//while(ans.toUpperCase().equals("Y"));	//start over if answer is "Y" or "y"

	}
	
}
