/**
 * 
 * This file is part of the FilmFinder Project, written as 
 * part of the assessment for CAB302, Semester 1, 2015. 
 *
 * FilmFinder
 * asgn1Index 
 * 15/03/2015
 * 
 */
package asgn1Index;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple collection class for the similarity records 
 * 
 * @author hogan
 *
 */
public abstract class AbstractRecordCollection {
	protected ArrayList<Record> records;
	protected boolean sorted = false;
	
	/**
	 * Simple method to add a record to the collection 
	 * 
	 * @param r <code>Record</code> to be added 
	 * @throws IndexException if <code>isNull(r)</code>
	 */
	public abstract void addRecord(Record r) throws IndexException;

	/**
	 * Search method to find the closest record in the collection 
	 * (In practice, the top record once the collection is sorted) 
	 * 
	 * @return <code>AbstractRecord r</code> such that 
	 * <code>r.similarity>s.similarity</code> for all <code>Record s</code>
	 * @throws IndexException if <code>!isSorted(this)</code> 
	 */
	public abstract AbstractRecord findClosestRecord() throws IndexException;

	/**
	 * Search method to find the n closest records in the collection 
	 * (In practice, the top n records once the collection is sorted)
	 * 
	 * @param n <code>int</code> max number of records to be returned 
	 * @return <code><r1,r2,...,rn></code> where <code>r1.similarity > 
	 * r2.similarity > ... > rn.similarity</code> and <code>rn > s</code> 
	 * for all other <code>Record s</code>
	 * @throws IndexException if <code>!isSorted(this) OR n > #(this)</code>
	 */
	public abstract List<Record> findClosestRecords(int n) throws IndexException;

	/**
	 * Simple status method to show whether collection has been sorted 
	 * 
	 * @return <code>true</code> if <code>isSorted(this)</code>; <code>false</code>
	 * otherwise
	 */
	public abstract boolean isSorted();

	/**
	 * Method to sort the collection based on Record comparator
	 * 
	 */
	public abstract void sortCollection();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + records + "]";
	}

}