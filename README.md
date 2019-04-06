# InteractiveConversationalAgent
**Purpose:**
The Interactive Conversational Agent allows an individual to hold a conversation of at least 30 turns. The agent is a celebrity and the user can be anyone. The celebrity chosen is Kanye West. The conversation will be primarily question/answer based, but there may be certain specific responses for some non-question statements. The responses from the chat agent is a collection of tweets and song lyrics by Kanye West himself.

## Class Organization

**Breakdown of the classes:**
* BDialog: This class is responsible to create a Graphical User Interface for the conversation between the user and the Chatbot. 
* Conversation: This class is responsible to communicate and transfer user inputs and chatbot outputs. 
* YeBot: YeBot is the main class for the interaction between a user and the chatbot.
* test: This test class implements YeBot and can be used to run the program
* ChatClient: This class operates as a socket to be used to chat with other bots

## How to Compile and Run the Code
**Enter the following code into command line to run Yebot:**
* javac BDialog.java Conversation.java YeBot.java test.javajava test

**Or run the test.java file**

## Assignment Three Additions

* GUI Improvement: The GUI is free of bugs and no longer has a pointless window that gains focus on each turn.
* YeBot class reconstruction: The YeBot class is now used as an object, to be used in the test and ChatClient classes.
  * Methods
    * initialize(): Run before YeBot's other functions are used. Loads the AIML, wordNet database, and sets 'unknown' responses
    * chat(): Opens a chat window for the user to directly interface with YeBot.
    * getResponse(String input): Gets YeBot's response to the input string.
* Library Implementations:
  * WordNet with JAWS: YeBot's getResponse method now makes use of the WordNet synonyms to find responses easier.
  * OpenNLP POS-Tagging: If no other suitable responses are found for an input, YeBot uses POS tagging to create a response that makes more sense
* New Topic:
  * Kanye responds better to greetings and talking about his favourite rappers.
* Added more reasonable responses when no responses can be found (total 5 now)
* Implemented ChatClient, which uses sockets to connect to other conversational agents and send responses to their inputs.

## Built With

* [Java](https://www.java.com/) - Programming language 
* [AIML](https://www.tutorialspoint.com/aiml/) - AIML dialogue





