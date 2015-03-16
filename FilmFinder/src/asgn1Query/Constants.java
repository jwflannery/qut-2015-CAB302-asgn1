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
package asgn1Query;

/**
 * Simple class to hold constants for the <code>FilmFinder</code> project 
 * 
 * @author hogan
 *
 */
public class Constants {
	//File Processing  
	public static final boolean KEY_LOG = false; //Log keywords 
	public static final String LOG_PREFIX = "movies";
	public static final String SOURCE_FILE = "large19302015.txt";
	public static final String KEY_PREFIX = "keywords";
	
	//Query Runners 
	public static final boolean USE_GUI = true; 
	public static final int NUM_RESULTS = 5; 
	public static final int NUM_ARGS = 1; 
	
	//GUI Display Parameters 
	public static final int TEXT_ROWS = 30; 
	public static final int TEXT_COLS = 80; 
	public static final int FONT_SIZE = 12;
}
