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

/**
 * Simple abstract class to hold title and (<code>int</code>) similarity data 
 * 
 * @author hogan
 *
 */
public abstract class AbstractRecord {
	protected String title;
	protected int similarity;

	/**
	 * Simple getter for the similarity 
	 * 
	 * @return <code>int</code> containing the similarity
	 */
	public abstract int getSimilarity();

	/**
	 * Simple getter for the title <code>String</code>
	 * 
	 * @return <code>String</code> containing the title
	 */
	public abstract String getTitle();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "["+this.title+":"+this.similarity+ "]";
	}
}