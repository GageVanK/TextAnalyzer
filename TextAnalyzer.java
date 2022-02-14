
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.jsoup.*;
import org.jsoup.nodes.Document;

public class TextAnalyzer {

	public static void main(String[] args) {
		
		try {
			
			//Jsoup takes in the url to parse
			Document doc = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();
			//Taking out the poem section of the article 
			String poem =  doc.select("p").text();
			
			/*
			I played around with this part a lot in trying to take out special chars & punctuation but it would always 
			end up with an off result that would cause miscounting words
			*/
			//This replaces all the hyphens"-" with a space
			String result = poem.replaceAll("\\p{Pd}", " ");
			
			//Scanner to read through the poem
			Scanner scan = new Scanner(result);
			
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
          
            System.out.println(map2);
        		
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		

	}

}
