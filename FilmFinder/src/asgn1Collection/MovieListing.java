package asgn1Collection;

import java.util.BitSet;
import java.util.Set;
import java.util.TreeSet;

public class MovieListing implements Listing {


	private String title;
	private int year;
	private BitSet keyVector;
	private TreeSet<String> keywords;
	
	
	public MovieListing(java.lang.String title, int year) throws ListingException {
		
		if(title == null || title == "")
			throw new ListingException("Title is null or empty.");
		if(year <= 0)
			throw new ListingException("Year is zero or less");
		
		this.title = title;
		this.year = year;
		this.keywords = new TreeSet<String>();
	}
             
	@Override
	public void addKeyword(String kw) throws ListingException {
		if(kw == null || kw == "")
			throw new ListingException("Keyword is null or empty.");
		this.keywords.add(kw);
	}

	@Override
	public int findSimilarity(Listing l) throws ListingException {
		
		if (getKeyVector() == null)
			throw new ListingException("This KeyVector is null");
		if (l.getKeyVector() == null)
			throw new ListingException("Passed KeyVector is null");
		BitSet temp = (BitSet)getKeyVector().clone();
		temp.and(l.getKeyVector());
		int c = temp.cardinality();
		return c;
	}

	@Override
	public BitSet getKeyVector() throws ListingException {
		if (keyVector == null)
			throw new ListingException("keyVector is null");		
		return keyVector;
	}

	@Override
	public Set<String> getKeywords() {
		return this.keywords;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public int numKeywords() {
		return this.keywords.size();
	}

	@Override
	public void setKeyVector(BitSet bs) {
		this.keyVector = bs;
	}

	@Override 
	public String writeKeyVector() throws ListingException {
		if (keyVector == null)
			throw new ListingException("keyVector is null");
		return this.keyVector.toString();
	}

	/* (non-Javadoc)
	 * @see asgn1Collection.Listing#writeKeywords()
	 */
	public String writeKeywords() {
		String str=""; int index=0;
		for (String kw : this.keywords) {
			str += kw +":"; 
			index++;
			if ((index % 10)==0) {
				str += "\n";
			}
		}
		return str;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.title + ":" + this.year + ":" + "Active keywords:" + this.numKeywords();
	}
	
	

}
