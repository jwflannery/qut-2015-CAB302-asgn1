package asgn1Collection;

//THis is some free code for you to include (adapting as needed) for inclusion in the MovieListing.java 
//class that you have to create. It is copied from that class before it was deleted. 
public class Snippet {
	/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return this.title + ":" + this.year + ":" + "Active keywords:" + this.numKeywords();
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
		
}

