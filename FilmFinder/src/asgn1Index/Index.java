/**
 * 
 * This file is part of the FilmFinder Project, written as 
 * part of the assessment for CAB302, Semester 1, 2015. 
 *
 * FilmFinder
 * asgn1Collection 
 * 14/03/2015
 * 
 */
package asgn1Index;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import asgn1Collection.Collection;
import asgn1Collection.Listing;
import asgn1Collection.ListingException;

/**
 * Class to handle the creation of an index based on similarity relationships 
 * amongst <code>Records</code> found in an <code>asgn1Collection.Collection</code>. 
 * Index allows searches based on querying on the title field.  
 * 
 * @author hogan
 *
 */
public class Index {
	private Map<String,RecordCollection> index; 
	private Collection collection; 
	
	/**
	 * Constructor for the Index class 
	 * 
	 * @param c <code>Collection</code> to be indexed. 
	 * @throws IndexException if <code>isNull(c)</code> 
	 */
	public Index(Collection c) throws IndexException {
		if (c==null) {
			throw new IndexException("Collection not available"); 
		}
		this.index=new TreeMap<String,RecordCollection>();
		this.collection=c; 
	}
	
	
	/**
	 * Working method to index the collection based on keyword similarity 
	 * The approach works only if the <code>keyVector</code>s have been generated. Method 
	 * counts number of <code>keyVector</code> elements in common. 
	 * 
	 * @throws ListingException if <code>!c.hasKeyVectors()</code>
	 * @throws IndexException (propagated from <code>asgn1Index.Record(String,int)</code>)
	 */
	public void createIndex() throws ListingException, IndexException {
		if (!this.collection.hasKeyVectors()) {
			throw new ListingException("KeyVectors not available");
		}
		Map<String,Listing> listing = this.collection.getListing();
		for (Map.Entry<String, Listing> first : listing.entrySet()) {
			String title1 = first.getKey();
			Listing item1 = first.getValue(); 
			
			RecordCollection rc = new RecordCollection(); 
			
			for (Map.Entry<String, Listing> second : listing.entrySet()) {
				String title2 = second.getKey();
				Listing item2 = second.getValue(); 
				rc.addRecord(new Record(title2,item1.findSimilarity(item2)));
			}	
			rc.sortCollection();
			this.index.put(title1, rc);
		}
	}
	
	/**
	 * Method to locate the listings most similar to the query 
	 * Searches for titles in the collection 
	 * 
	 * @param query <code>String</code> containing the search text 
	 * @param matches <code>int<code> containing maximum number of matches to return 
	 * @return <code>List<Record></code> containing of proximal Records in descending match order: <code><r1,r2,...,rn></code> where <code>r1.similarity > 
	 * r2.similarity > ... > rn.similarity</code> and <code>rn > s</code> 
	 * for all other <code>Record s</code>
	 * @throws IndexException if <code>isNull(index) OR isEmpty(index)</code>
	 */
	public List<Record> queryListing(String query,int matches) throws IndexException {
		if (nullOrEmpty(this.index)) {
			throw new IndexException("Index not yet created or empty");
		}
		String q = query.toUpperCase();
		if (this.index.keySet().contains(q)) {
			RecordCollection rc = this.index.get(q);
			return rc.findClosestRecords(matches);
		} else {
			return null;
		}
	}
	
	/**
	 * Helper method to simplify tests for null or empty objects 
	 * 
	 * @param m <code>Map<String,RecordCollection></code> to be tested 
	 * @return <code>true</code> if <code>isNull(m) OR isEmpty(m)</code>
	 */
	private boolean nullOrEmpty(Map<String,RecordCollection> m) {
		return (m==null)||(m.isEmpty());
	}
}
