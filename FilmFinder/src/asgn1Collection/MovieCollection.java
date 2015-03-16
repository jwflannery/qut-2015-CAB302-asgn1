/**
 * 
 * This file is part of the FilmFinder Project, written as 
 * part of the assessment for CAB302, Semester 1, 2015. 
 *
 * FilmFinder
 * asgn1Collection 
 * 13/03/2015
 * 
 */
package asgn1Collection;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import asgn1Query.Constants;


/**
 * 
 * <p>Class to obtain and manage a collection of Listings. This implementation 
 * uses a sorted map via a <code>TreeMap</code> data structure to enable rapid access to 
 * individual elements as needed. A full <code>Set</code> of keywords is accumulated as the 
 * source file is parsed and these form the basis for the <code>BitSet</code> representations 
 * of each movies.</p> 
 * 
 * @author hogan
 *
 */
public class MovieCollection implements Collection {
	private Map<String,Listing> movies;
	private Set<String> keywords; 
	private int entries; 
	private boolean keyVectors; 
	
	/**
	 * Constructor for the <code>MovieCollection</code> class
	 * 
     * No parameters for this constructor
	 */
	public MovieCollection() { 
		this.movies = new TreeMap<String,Listing>(); 
		this.keywords = new TreeSet<String>();
		this.entries=0;
		this.keyVectors=false; 
	}
	
	/* (non-Javadoc)
	 * @see asgn1Collection.Collection#generateKeyVectors()
	 */
	@Override
	public void generateKeyVectors() throws ListingException {
		if (nullOrEmpty(this.movies)) {
			throw new ListingException("Movies not available; source file not parsed?"); 
		}
		if (nullOrEmpty(this.keywords)) {
			throw new ListingException("Keywords not available; movies not parsed"); 
		}
		ArrayList<String> kwList = new ArrayList<String>(this.keywords);
		for (Map.Entry<String, Listing> entry : this.movies.entrySet()) {
			Listing mov = entry.getValue(); 

			BitSet bs = new BitSet(this.numKeywords()); 
			for (String kw : mov.getKeywords()) {
				int index = Collections.binarySearch(kwList,kw);
				bs.set(index);
			}	
			mov.setKeyVector(bs);
		}
		this.keyVectors=true;
	}
	
	/* (non-Javadoc)
	 * @see asgn1Collection.Collection#getCollectionFromFile()
	 */
	@Override
	public void getCollectionFromFile() throws FileNotFoundException, IOException, ListingException {
		this.getCollectionFromFile(Constants.SOURCE_FILE);
	}
	
	/* (non-Javadoc)
	 * @see asgn1Collection.Collection#getCollectionFromFile(java.lang.String)
	 */
	@Override
	@SuppressWarnings("resource")
	public void getCollectionFromFile(String srcFile) throws FileNotFoundException, IOException, ListingException {
		FileInputStream fstream = new FileInputStream(srcFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			
		//Read File Line By Line
		String line, currentFilm="", keyword=""; MovieListing movie=null; 
		do { 
		  //Grab a line and parse it if we can 
		  line = br.readLine(); 
		  String title=""; String[] tokens = null;
		  if (line!=null) { 
			  tokens = line.split("\t");
			  title=tokens[0];
			  keyword=tokens[1];
			  if (nullOrEmpty(title)) {
				  throw new ListingException("Missing Title");
			  }
			  if (nullOrEmpty(keyword)) {
				  throw new ListingException("Missing Keyword");
			  }
		  }
		 
		  if (line==null) {
			  //final line clean up 
			  movie.addKeyword(keyword);
			  this.keywords.addAll(movie.getKeywords());
			  this.movies.put(movie.getTitle(),movie);
		  } else if (newFilm(currentFilm, title)) {
			  //New film, but write only if we have data
			  if (!currentFilm.isEmpty()) {
				 this.entries++;
			     this.keywords.addAll(movie.getKeywords());
				 this.movies.put(movie.getTitle(),movie);
			  }
			  currentFilm=title;
			  movie=new MovieListing(trimTitle(title),parseYear(title));
			  movie.addKeyword(keyword);
		  } else {
			  //standard operation 
			  movie.addKeyword(keyword);
		  }
		} while (line!=null); 
		
		//Close the streams
		fstream.close(); br.close();			
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Collection#getListing()
	 */
	@Override
	public Map<String, Listing> getListing() {
		return movies;
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Collection#getListings(boolean, boolean)
	 */
	@Override
	public String getListings(boolean showKeywords,boolean showKeyVector) throws ListingException {
		if (nullOrEmpty(this.movies)) {
			throw new ListingException("Movies not available; source file not parsed?"); 
		}
		String str = "";
		if (!this.hasKeyVectors()) {
			str += "KeyVectors not yet available\n"; 
		}
		for (Map.Entry<String, Listing> entry : this.movies.entrySet()) {
			Listing mov = entry.getValue(); 
			str+=mov.toString() + "\n";
			
			if (showKeywords) {
				str += mov.writeKeywords() + "\n";
			}
			if (this.hasKeyVectors() && showKeyVector) {
				str += mov.writeKeyVector() + "\n"; 
			}
		}
		return str;
	}
		
	
	/* (non-Javadoc)
	 * @see asgn1Collection.Collection#hasKeyVectors()
	 */
	@Override
	public boolean hasKeyVectors() {
		return this.keyVectors;
	}
	
	/* (non-Javadoc)
	 * @see asgn1Collection.Collection#numKeywords()
	 */
	@Override
	public int numKeywords() {
		return this.keywords.size();
	}
	
	/* (non-Javadoc)
	 * @see asgn1Collection.Collection#numListings()
	 */
	@Override
	public int numListings() {
		return this.movies.size();	
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String str = "Total Movies: <stored>:"+ this.movies.size() + ":<parsed>:" + 
                entries + ":" + "Total Keywords: " + 
			      this.keywords.size();
		if (this.hasKeyVectors()) { 
			str+="\nkeyVectors available";
		}
		return str + "\n";
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Collection#writeKeywords()
	 */
	@Override
	public String writeKeywords() {
		String str="|"; int index=0; 
		for(String kw : this.keywords) {
			str+= index+":"+kw+":";
			index++;
			if ((index % 10)==0) {
				str += "|\n|";
			}
		}
		return str + "|";
	}
	
	/**
	 * Helper method to tell if we are in a new movie in the IMDB file
	 * 
	 * @param currentFilm String containing full title string (current)
	 * @param name String full title string just read (possibly new)
	 * @return true if different; false otherwise 
	 */
	private boolean newFilm(String currentFilm, String name) {
		return !name.equalsIgnoreCase(currentFilm);
	}

	/**
	 * Helper method to parse title string to extract year 
	 * 
	 * @param t String containing (19XX) or (20XX) year entry 
	 * @return int year extracted from title string 
	 */
	private int parseYear(String t) {
		Pattern pattern = Pattern.compile("\\([1-2][0-9][0-9][0-9]\\)");
        Matcher matcher = pattern.matcher(t);
        if (matcher.find()) {  
           return Integer.parseInt(matcher.group().substring(1, matcher.group().length()-1));
        } else { 
           return 2020; 
        }
	}
	
	/**
	 * Helper to remove the bracketed year entry that forms part of the title string 
	 * 
	 * @param t String to parse 
	 * @return String containing bare title of the movies 
	 */
	private String trimTitle(String t) {
		//Split on parentheses, last token is year we are trying to remove
		String[] tokens=t.split("\\(");
		//Allow for multiple tokens - () in the actual title   
		//remove and then make uppercase to aid searching 
		int index=t.indexOf(tokens[tokens.length-1]);
		return t.substring(0,index-1).trim().toUpperCase();
	}
	

	/**
	 * Helper method to simplify tests for null or empty objects 
	 * 
	 * @param m <code>Map<String,Listing></code> to be tested 
	 * @return <code>true</code> if <code>isNull(m) OR isEmpty(m)</code>
	 */
	private boolean nullOrEmpty(Map<String,Listing> m) {
		return (m==null)||(m.isEmpty());
	}
	
	/**
	 * Helper method to simplify tests for null or empty objects 
	 * 
	 * @param m <code>Set<String></code> to be tested 
	 * @return <code>true</code> if <code>isNull(s) OR isEmpty(s)</code>
	 */
	private boolean nullOrEmpty(Set<String> s) {
		return (s==null)||(s.isEmpty());
	}
	
	/**
	 * Helper method to simplify tests for null or empty strings 
	 * 
	 * @param str <code>String</code> to be tested 
	 * @return <code>true</code> if <code>isNull(str) OR isEmpty(str)</code>
	 */
	private boolean nullOrEmpty(String str) {
		return (str==null)||(str.isEmpty());
	}
}
