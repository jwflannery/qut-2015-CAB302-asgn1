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
		this.records.add(r);
	}

	@Override
	public AbstractRecord findClosestRecord() throws IndexException {
		//sortCollection();
		return this.records.get(0);
	}

	@Override
	public List<Record> findClosestRecords(int n) throws IndexException {
			sortCollection();
			return this.records.subList(0, n);
	}

	@Override
	public boolean isSorted() {
		// TODO Auto-generated method stub
		return sorted;
	}

	@Override
	public void sortCollection() {
		Collections.sort(records);
		sorted = true;
	}

}
