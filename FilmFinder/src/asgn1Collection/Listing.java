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

import java.util.BitSet;
import java.util.Set;

/**
 * Interface to support documents or works that make up the <code>asgn1Collection.Collection</code>
 * We assume that a title, year of creation and keyword list are available for each listing 
 *  
 * @author hogan
 *
 */
public interface Listing {

	/**
	 * Method to add a valid keyword to the list 
	 * 
	 * @param kw <code>String</code> keyword to be added 
	 * @throws ListingException if keyword invalid: <code>isNull(kw) OR isEmpty(kw)</code>
	 */
	public abstract void addKeyword(String kw) throws ListingException;

	/**
	 * Method to find the number of keywords common to <code>this</code> and 
	 * the <code>Listing l</code> based on the <code>keyVector</code> representations 
	 * for each listing 
	 * 
	 * @param l <code>Listing</code> for comparison
	 * @return <code>int</code> number of keywords in common
	 * @throws ListingException if <code>isNull(this.keyVectors) OR
	 * isNull(l)</code>
	 */
	public abstract int findSimilarity(Listing l) throws ListingException;
	
	/**
	 * Getter for access to <code>keyVectors</code> of the current <code>Listing</code> 
	 * 
	 * @return <code>BitSet</code> of <code>keyVectors</code> 
	 * @throws ListingException if <code>isNull(keyVectors)</code>
	 */
	public abstract BitSet getKeyVector() throws ListingException;

	/**
	 * Getter to provide access to the Listing keywords 
	 * 
	 * @return <code>Set<String></code> containing keywords  
	 */
	public abstract Set<String> getKeywords();

	/**
	 * Simple Getter to return the title field
	 * 
	 * @return String containing Listing title 
	 */
	public abstract String getTitle();

	/**
	  * Simple Getter to return the Listing year
	 * 
	 * @return int containing Listing year 
	 */
	public abstract int getYear();

	/**
	 * Simple Getter to return the number of keywords 
	 * 
	 * @return int containing number of keywords for the listing 
	 */
	public abstract int numKeywords();

	/**
	 * Setter to transfer keyVector BitSet to Listing 
	 * 
	 * @param bs BitSet to be stored 
	 */
	public abstract void setKeyVector(BitSet bs);
	
	/**
	 * Simple method to accumulate keyVector for printing 
	 * 
	 * @return String containing formatted keyVector
	 * @throws ListingException if <code>isNull(keyVector)</code>
	 */
	public abstract String writeKeyVector() throws ListingException;
	
	/**
	 * Simple method to accumulate keywords for printing 
	 * 
	 * @return String containing formatted keyword list
	 */
	public abstract String writeKeywords();
}