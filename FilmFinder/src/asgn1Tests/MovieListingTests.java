package asgn1Tests;
 
import static org.junit.Assert.*;

import java.util.BitSet;
 
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
 




import asgn1Collection.ListingException;
import asgn1Collection.MovieListing;
 
public class MovieListingTests {
    MovieListing movie;
     
 
    @Before public void initialize() throws ListingException{
    movie = new MovieListing("Casablanca", 1993);	
    
    }
    /* Test 1: Constructing a MovieList object
     */
    @Test
    public void SetUpBothValid(){
        assertEquals("Titles Match","Casablanca", movie.getTitle());
        assertEquals("Years Match", 1993, movie.getYear());
    }
     
   //Test 2
    @Test (expected = ListingException.class)
    public void SetUpBothInvalid() throws ListingException {
        movie = new MovieListing(null, 0);
    }
     
  //Test 3
    @Test (expected = ListingException.class)
    public void SetUpTitleNull() throws ListingException {
        movie = new MovieListing(null, 1993);
    }
     
  //Test 4
    @Test (expected = ListingException.class)
    public void SetUpYearZero() throws ListingException {
        movie = new MovieListing("Casablanca", 0);
    }
    
  //Test 5
    @Test (expected = ListingException.class)
    public void SetUpYearBelowZero() throws ListingException {
        movie = new MovieListing("Casablanca", -1993);
    }
     
  //Test 6
    @Test (expected = ListingException.class)
    public void SetUpTitleEmpty() throws ListingException {
        movie = new MovieListing("", 1993);
    }
     
  //Test 7
    @Test
    public void AddKeywordValidInput() throws ListingException{
        movie.addKeyword("Keyword");
        assertEquals("Keyword in set", true, movie.getKeywords().contains("Keyword"));
    }
     
  //Test 8
    @Test (expected = ListingException.class)
    public void AddKeywordEmpyInput() throws ListingException{
        movie.addKeyword("");
    }
     
  //Test 9
    @Test (expected = ListingException.class)
    public void AddKeywordNullInput() throws ListingException{
        movie.addKeyword(null);
    }
     
  //Test 10
    @Test
    public void findSimilarityValidInput() throws ListingException{
        MovieListing movieTwo = new MovieListing("The Sound of Music", 1883);
        BitSet bs = new BitSet();
        bs.set(0);
        movie.setKeyVector(bs);
        movieTwo.setKeyVector(bs);
 
        assertEquals("Keywords Match", 1, movie.findSimilarity(movieTwo));
    }
     
  //Test 11
    @Test (expected = ListingException.class)
    public void findSimilarityThisKeyVectorNull() throws ListingException {
        MovieListing movieTwo = new MovieListing("The Sound of Music", 1883);
        BitSet bs = new BitSet();
        bs.set(0);
        movieTwo.setKeyVector(bs);
 
        assertEquals("Keywords Match", 1, movie.findSimilarity(movieTwo));
    }
 
  //Test 12
    @Test (expected = ListingException.class)
    public void findSimilarityOtherKeyVectorNull() throws ListingException {
        MovieListing movieTwo = new MovieListing("The Sound of Music", 1883);
        BitSet bs = new BitSet();
        bs.set(0);
        movie.setKeyVector(bs);
 
        assertEquals("Keywords Match", 1, movie.findSimilarity(movieTwo));
    }
     
  //Test 13
    @Test
    public void getKeyVectorValidInput() throws ListingException{
        BitSet bs = new BitSet();
        bs.set(0);
        movie.setKeyVector(bs);
        assertEquals("KeyVector works", "{0}", movie.getKeyVector().toString());
    }
     
  //Test 14
    @Test (expected = ListingException.class)
    public void getKeyVectorNullInput() throws ListingException {
        movie.getKeyVector();
    }
  
        //Test 15
    @Test
    public void getOneKeyWord() throws ListingException{
      	movie.addKeyword("Keyword");
      	TreeSet<String> testSet = new TreeSet<String>();
      	testSet.add("Keyword");
      	
      	assertEquals("Get Keywords",testSet, movie.getKeywords());
    }
    
    //Test 16
    @Test
    public void getMultipleKeyWords() throws ListingException{
      	movie.addKeyword("Keyword");
      	movie.addKeyword("Other");
      	movie.addKeyword("Word");
      	TreeSet<String> testSet = new TreeSet<String>();
      	testSet.add("Keyword");
      	testSet.add("Other");
      	testSet.add("Word");
      	assertEquals("Get Keywords",testSet, movie.getKeywords());
    }
    
    @Test
    public void getTitle(){
    assertEquals("Get Title Works", "Casablanca", movie.getTitle());
    }
    
    @Test
    public void getYear(){
    assertEquals("Get Title Works", 1993, movie.getYear());
    }
    
    @Test
    public void numKeywordsOneKeyword() throws ListingException{
    	movie.addKeyword("Keyword");
    	assertEquals("numKeywords", 1, movie.numKeywords());
    }
    
    @Test
    public void numKeywordsManyKeyword() throws ListingException{
      	movie.addKeyword("Keyword");
      	movie.addKeyword("Other");
      	movie.addKeyword("Word");
    	assertEquals("numKeywords", 3, movie.numKeywords());
    }
    
    @Test
    public void setKeyVector() throws ListingException{
        BitSet bs = new BitSet();
        bs.set(0);
        movie.setKeyVector(bs);
        assertEquals(movie.getKeyVector(), bs);
    }
    
  //Test 
    @Test
    public void writeKeyVectorValidInput() throws ListingException{
        BitSet bs = new BitSet();
        bs.set(0);
        movie.setKeyVector(bs);
        assertEquals("KeyVector works", "{0}", movie.writeKeyVector());
    }
         
  //Test
    @Test (expected = ListingException.class)
    public void writeKeyVectorNullInput() throws ListingException {
        movie.writeKeyVector();
    }
    
    @Test
    public void writeKeyWords() throws ListingException {
    	movie.addKeyword("Keyword");
    	assertEquals("Keyword:", movie.writeKeywords());
    }
    
    @Test
    public void toStringTest(){
    	
    	assertEquals(("Casablanca:" + 1993 + ":Active keywords:" + 0), movie.toString());
    }
}

