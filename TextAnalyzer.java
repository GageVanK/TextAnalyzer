import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TextAnalyzer {

	public static void main(String[] args) throws FileNotFoundException {
		
		//Downloaded the HTML File that was given
		  File inputFile = new File("C:\\Users\\gagev\\Downloads\\poem.html");
		  Document document;
		  try {
		        //Using Jsoup to parse HTML File into its own Documents
			document = Jsoup.parse(inputFile, "UTF-8");
			
			//Taking out all the parts of the file that arent needed
			//This takes only the text in <p> tags from the document and assigns it to its own string
			String e =  document.select("p").text();
			
			//This takes all the punctuations/special characters out of the document and assigns it to another string
			String result = e.replaceAll("\\p{Punct}", "");
			String finalString = result.replaceAll("[^\\w\\s]", "");
			
			
			//Then I write this string into a text file to be able to start tracking the words
			BufferedWriter  writer = null;
			writer = new BufferedWriter( new FileWriter("C:\\Users\\gagev\\OneDrive\\Desktop\\StrippedHTMLDoc.txt"));
            writer.write(finalString);
            writer.close();
	        
	       
		  } catch (IOException e) {
			e.printStackTrace();
		  }

		  //This scans in the new text file with only the poem
		  Scanner scan = new Scanner(new File("C:\\Users\\gagev\\OneDrive\\Desktop\\StrippedHTMLDoc.txt"));
		  //Creating a hashmap to stores the words and the amount of times they occur
          HashMap<String, Integer> map = new HashMap<String, Integer>();
          	//using while loop to read through the text file
            while(scan.hasNextLine())
            {
    
            		
            	//Storing the words
                String word = scan.next();
            		//using an if statement to track whethe a word has been stored already & incremented or need to be added to the hashmap
                	if(map.containsKey(word)) {
                		int count = map.get(word) + 1;
                		map.put(word, count);
              
                	}
                	else {
                		map.put(word, 1);
                	}
                	
                	//Found out you cant sort hashmaps so I add the values into a new list
                	List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
                	
                	//Using Collections/Comparator to sort the list
                	Collections.sort(list , new Comparator<Map.Entry<String, Integer>>(){
                		@Override
                		public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                			//This should sort them in descending order, it doesnt seem to work though
                			return e2.getValue().compareTo(e1.getValue());
                		}
                	});
                	//For loop to iterate through the list and output the Word along with the amount of times they occurred
                	for (Map.Entry<String, Integer> e : list) {
            			System.out.println(e.getKey() + ", " + e.getValue());
                	}
                
            }
	
	}}
        
