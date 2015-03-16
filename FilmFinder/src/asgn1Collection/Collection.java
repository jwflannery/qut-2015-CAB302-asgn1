/**
 * 
 * This file is part of the FilmFinder Project, written as 
 * part of the assessment for CAB302, Semester 1, 2015. 
 *
 * FilmFinder
 * asgn1Collection 
 * 15/03/2015
 * 
 */
package asgn1Collection;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * Interface to specify a collection of <code>asgn1Collection.Listing</code> objects 
 * 
 * @author hogan
 *
 */
public interface Collection {

	/**
	 * Method to generate a keyVector representation for each listing 
	 * Each keyVector is stored as part of the Listing object 
	 * @throws ListingException if listings or keywords unavailable: 
	 * <code>isNull(listings) OR isEmpty(listings) OR isNull(keywords) 
	 * OR isEmpty(keywords)</code>
	 */
	public abstract void generateKeyVectors() throws ListingException;

	/**
	 * Method to parse a collection file and store listing and keyword info. 
	 * Uses source file designated in <code>asgn1Collection.Constants</code>. 
	 * 
	 * @throws FileNotFoundException if srcFile doesn't exist (java.io)
	 * @throws IOException if reads from srcFile fail (java.io) 
	 * @throws ListingException if data line has missing title or keyword entry: 
	 * <code>isNull(title) OR isEmpty(title) OR isNull(keyword) OR 
	 * isEmpty(keyword)</code>
	 */
	public abstract void getCollectionFromFile() throws FileNotFoundException,
			IOException, ListingException;

	/**
	 * Method to parse a collection file and store listing and keyword info. 
	 * 
	 * @param srcFile <code>String</code> containing file name (current dir)
	 * @throws FileNotFoundException if srcFile doesn't exist (java.io)
	 * @throws IOException if reads from srcFile fail (java.io) 
	 * @throws ListingException if missing title or keyword entry <br>
	 * <b>Formally:</b> <code>isNull(title) OR isEmpty(title) <br>
	 * OR isNull(keyword) OR isEmpty(keyword)</code>
	 */
	public abstract void getCollectionFromFile(String srcFile)
			throws FileNotFoundException, IOException, ListingException;

	/**
	 * Getter method for access to the collection of <code>Listing</code>s 
	 * 
	 * @return <code>Map</code> containing the listings  
	 */
	public abstract Map<String, Listing> getListing();

	/**
	 * Method to generate a <code>String</code> snapshot of the collection.  
	 * Iterate over the listings giving the detail selected
	 * 
	 * @param showKeywords <code>boolean</code> choose to record listing keywords 
	 * @param showKeyVector <code>boolean</code> choose to record listing keyVectors
	 * @return <code>String</code> containing collection summary 
	 * @throws ListingException if listings not available: <code>isNull(listings) OR 
	 * isEmpty(listings) 
	 */
	public abstract String getListings(boolean showKeywords,
			boolean showKeyVector) throws ListingException;

	/**
	 * <code>boolean</code> status to show whether <code>keyVectors</code> exist 
	 * 
	 * @return <code>true</code> if <code>keyVectors</code> have been created; 
	 * <code>false</code> otherwise 
	 */
	public abstract boolean hasKeyVectors();

	/**
	 * Simple getter to return the total number of keywords 
	 * 
	 * @return <code>int >=0</code> indicating number of keywords available
	 */
	public abstract int numKeywords();

	/**
	 * Simple getter to return the total number of listings 
	 * 
	 * @return <code>int >=0</code> indicating number of listings available
	 */
	public abstract int numListings();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();

	/**
	 * Logging method producing ordered listing of keywords
	 * 
	 * @return <code>String</code> of ':' separated index:keyword pairs.
	 */
	public abstract String writeKeywords();

}