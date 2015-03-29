package asgn1Index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordCollection extends AbstractRecordCollection {

	public RecordCollection() {
		this.records = new ArrayList<Record>();
		this.sorted = false;
	}

	@Override
	public void addRecord(Record r) throws IndexException {
		this.records.add(r);
	}

	@Override
	public AbstractRecord findClosestRecord() throws IndexException {
		return this.records.get(0);
	}

	@Override
	public List<Record> findClosestRecords(int n) throws IndexException {
			return this.records;
	}

	@Override
	public boolean isSorted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sortCollection() {
	Collections.sort(records);
	}

}
