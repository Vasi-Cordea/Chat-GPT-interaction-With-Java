
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class ChatGPT_API2 {

	public static String chatGPT(String prompt) {
	       String url = "https://api.openai.com/v1/chat/completions";
	       String apiKey = "yourApiKeyFromOpenAI";
	       String model = "gpt-3.5-turbo";

	       try {
	           URL obj = new URL(url);
	           HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
	           connection.setRequestMethod("POST");
	           connection.setRequestProperty("Authorization", "Bearer " + apiKey);
	           connection.setRequestProperty("Content-Type", "application/json");

	           // The request body
	           String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
	           connection.setDoOutput(true);
	           OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
	           writer.write(body);
	           writer.flush();
	           writer.close();

	           // Response from ChatGPT
	           BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	           String line;

	           StringBuffer response = new StringBuffer();

	           while ((line = br.readLine()) != null) {
	               response.append(line);
	           }
	           br.close();

	           // calls the method to extract the message.
	           return extractMessageFromJSONResponse(response.toString());

	       } catch (IOException e) {
	           throw new RuntimeException(e);
	       }
	   }

	   public static String extractMessageFromJSONResponse(String response) {
	       int start = response.indexOf("content")+ 11;

	       int end = response.indexOf("\"", start);

	       return response.substring(start, end);

	   }

	   public static void main(String[] args) throws IOException {

	       //System.out.println(chatGPT("hello, how are you? is climate change real in 500 words?"));
	       
	       //Instantiating the File class
	       File file = new File("C:\\Users\\vasic\\Desktop\\GPTsample.txt");
	       //Instantiating the PrintStream class
	       PrintStream stream = new PrintStream(file);
	       System.out.println("From now on "+file.getAbsolutePath()+" will be your console");
	       System.setOut(stream);
	       //Printing values to file
	       System.out.println(chatGPT("hello, how are you? how about space travel in 2000 words with structure?"
	       		+ "what stock to invest in 2024?"));
	 
	       
	       
	       

	   }
	   
	}

