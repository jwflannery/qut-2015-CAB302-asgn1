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

/**
 * This class represents any exceptions associated with the <code>Listing</code> and
 * associated entries. 
 * 
 * @author hogan
 *
 */
@SuppressWarnings("serial")
public class ListingException extends Exception {
	/**
	 * Constructor to deal with Exceptions associated with Listings 
	 * 
	 * @param msg String containing pertinent message for the user
	 */
	public ListingException(String msg) {
		super("Listing Exception "+ msg);
	}
}
