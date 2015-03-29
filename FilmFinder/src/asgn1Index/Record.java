package asgn1Index;

public class Record extends AbstractRecord implements Comparable<Record>{
	
	public Record(String title2, int i) {
		this.title = title2;
		this.similarity = i;
	}

	@Override
	public int getSimilarity() {
		return this.similarity;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	@Override
	public int compareTo(Record R) {
			if (this.similarity > R.similarity)
				return -1;
			else if (this.similarity == R.similarity)
				return 0;
			else
				return 1;
				
	}

}
