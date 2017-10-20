package asgn1Index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordCollection extends AbstractRecordCollection {
	
	public RecordCollection() {
		this.records = new ArrayList<Record>();
	}

	@Override
	public void addRecord(Record r) throws IndexException {
		if(r == null)
			throw new IndexException("Record is null");
		this.records.add(r);
	}

	@Override
	public AbstractRecord findClosestRecord() throws IndexException {
		if (!this.isSorted() || this.records.size() == 0)
			throw new IndexException("Collection not sorted");
		return this.records.get(0);
	}

	@Override
	public List<Record> findClosestRecords(int n) throws IndexException {
			if (!this.isSorted())
				throw new IndexException("Collection not sorted");
			if (n > this.records.size())
				throw new IndexException("Query too large");
			sortCollection();
			return this.records.subList(0, n);
	}

	@Override
	public boolean isSorted() {
		return sorted;
	}

	@Override
	public void sortCollection() {
		Collections.sort(records);
		sorted = true;
	}

}
