import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.jsoup.*;
import org.jsoup.nodes.Document;
public class PoemAnalyzer {

	public static void main(String[] args) throws IOException {
		
		String poem = getPoem();
		System.out.println(poem.length());

		//Scanner to read through the poem
		Scanner scan = new Scanner(poem);
		
		//Storing words in a hashmap
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
        //using while loop to read through the string and store individual words
		while(scan.hasNextLine())
		{
   
				
			//Storing the words
		    String word = scan.next();
				//using an if statement to track whether a word has been stored already & incremented or need to be added to the hashmap
		    	if(map.containsKey(word)) {
		    		int count = map.get(word) + 1;
		    		map.put(word, count);
		  
		    	}
		    	else {
		    		map.put(word, 1);
		    	}
		    	
		    		
		    	
		}
		
   	//closing scanner
      scan.close();
		
		//Adding hashmap entries in descending order to a linkedhashmap
		LinkedHashMap<String, Integer> map2 = 
			    map.entrySet()
			       .stream()             
			       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .collect(Collectors.toMap(e -> e.getKey(), 
			                                 e -> e.getValue(), 
			                                 (e1, e2) -> null, 
			                                 () -> new LinkedHashMap<String, Integer>()));
		

	} 
	
	public static String getPoem() throws IOException {
		
		//Jsoup reads in document
		//Selecting p-tags from document --This leaves us with just the poem
		//Removes Hyphens but keeps other punctuation as it doesnt affect the word count
		Document doc = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();
		String poem =  doc.select("p").text();
		String strippedPoem = poem.replaceAll("\\p{Pd}", " ");
		return strippedPoem;
		
	}
