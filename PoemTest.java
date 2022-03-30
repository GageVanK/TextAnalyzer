import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;


public class PoemTest {
	
	@Test
	public void poemEqualsTest() throws IOException {	  
		
		String poem = PoemAnalyzer.getPoem();
		assertEquals(poem, PoemAnalyzer.getPoem());
	      
	      
	   }
	
	@Test
	public void poemEndTest() throws IOException {	  
		
		String poem = PoemAnalyzer.getPoem();
		String poemEnd = "nevermore!";
		assertEquals(poem.endsWith(poemEnd), true);
	      
	      
	   }
	
	@Test
	public void poemLengthTest() throws IOException {	  
		
		String poem = PoemAnalyzer.getPoem();
		assertEquals(poem.length(), 6215);
	      
	      
	   }
	
	@Test
	public void poemLengthTestWrong() throws IOException {	  
		
		String poem = PoemAnalyzer.getPoem();
		assertEquals(poem.length(), 6000);
	      
	      
	      
	   }
	
}
