package asgn1Collection;

import java.util.BitSet;
import java.util.Set;

public class MovieListing implements Listing {

	private BitSet KeyVector = new BitSet();
	private Set<String> keywords;
	private String title;
	private int year;
	
	
	public MovieListing(java.lang.String title, int year) throws ListingException {
		this.title = title;
		this.year = year;
	}
             
	@Override
	public void addKeyword(String kw) throws ListingException {
		// TODO Auto-generated method stub

	}

	@Override
	public int findSimilarity(Listing l) throws ListingException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BitSet getKeyVector() throws ListingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getKeywords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getYear() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int numKeywords() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setKeyVector(BitSet bs) {
		// TODO Auto-generated method stub

	}

	@Override
	public String writeKeyVector() throws ListingException {
		// TODO Auto-generated method stub
		return null;
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
