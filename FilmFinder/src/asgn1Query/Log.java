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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import asgn1Collection.Collection;
import asgn1Collection.ListingException;


/**
 * Class to support logging of Collections and Queries
 * @author hogan
 *
 */
public class Log {
	BufferedWriter writer = null;
	BufferedWriter keyWriter = null; 
	
	/**
	 * Constructor establishes a log file based on the current time in the canonical directory 
	 * @throws IOException if log file  or BufferedWriter cannot be created
	 */
	public Log () throws IOException {
		//File management based on http://stackoverflow.com/questions/15754523/how-to-write-text-file-java 
        File logFile = new File(Constants.LOG_PREFIX+getLogTime());
        
        if (Constants.KEY_LOG) {
        	File keyFile = new File(Constants.KEY_PREFIX+getLogTime());
        	// This will output the full path where the file will be written to...
            System.out.println(keyFile.getCanonicalPath());
            this.keyWriter = new BufferedWriter(new FileWriter(keyFile));
        }

        // This will output the full path where the file will be written to...
        System.out.println(logFile.getCanonicalPath());
        this.writer = new BufferedWriter(new FileWriter(logFile));
	}
		
	/**
	 * Helper returning Log Time format for filename
	 * @return filename String yyyyMMdd_HHmmss
	 */
	private String getLogTime() {
		String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return timeLog;
	}

	/**
	 * Logs the basic snapshot of the collection  
	 * 
	 * @param c <code>Collection</code> to be logged 
	 * @param showListings <code>boolean</code> choose to record the listings; if <code>false</code>, the 
	 * next two parameters are ignored.  
	 * @param showKeywords <code>boolean</code> choose to record listing keywords 
	 * @param showKeyVectors <code>boolean</code> choose to record listing keyVectors
	 * @throws QueryException if <code>isNull(c)</code> 
	 * @throws IOException propagated from <code>BufferedWriter.write()</code>
	 */
	public void writeCollection(Collection c,boolean showListings,boolean showKeywords,boolean showKeyVectors) throws QueryException, IOException {
		if (c==null) {
			throw new QueryException("Null collection supplied");
		}
		this.writer.write(c.toString());
		if (showListings) {
			this.writer.write("\n\nDetails of the Collection:\n");
			try {
				this.writer.write(c.getListings(showKeywords,showKeyVectors));
			} catch (ListingException e) {
				throw new QueryException(e.getMessage());
			}
		}
	}
	
	/**
	 * Logs the basic snapshot of the collection method 
	 * 
	 * @param c <code>Collection</code> to be logged 
	 * @throws QueryException if <code>isNull(c)</code> 
	 * @throws IOException propagated from <code>BufferedWriter.write()</code>
	 */
	public void writeKeywords(Collection c) throws QueryException, IOException {
		if (c==null) {
			throw new QueryException("Null collection supplied");
		}
		this.keyWriter.write(c.writeKeywords());
	}
	
	/**
	 * Finalise basic log 
	 * 
	 * @throws IOException propagated from <code>BufferedWriter.close()</code> and 
	 * <code>BufferedWriter.write()</code>
	 */
	public void finaliseLog() throws IOException {
		writer.flush();
		writer.close();
	}
	
	/**
	 * Finalise key log 
	 * 
     * @throws IOException propagated from <code>BufferedWriter.close()</code> and 
	 * <code>BufferedWriter.write()</code>
	 */
	public void finaliseKeyLog() throws IOException {
		keyWriter.flush();
		keyWriter.close();
	}

}
