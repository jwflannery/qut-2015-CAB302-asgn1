package asgn1Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import asgn1Index.IndexException;
import asgn1Index.Record;
import asgn1Index.RecordCollection;

public class RecordCollectionTests {

	RecordCollection collection;
	Record record;

	@Before public void initialize(){
		collection = new RecordCollection();
		record = new Record("Casablanca", 1);
	}
	
	@Test
	public void isSortedUnsorted(){
		assertEquals(false, collection.isSorted());
	}
	
	@Test
	public void isSortedTest(){
	collection.sortCollection();
	assertEquals(true, collection.isSorted());
	}
	
	@Test
	public void sortCollectionIsSortedTest(){
		assertEquals(false, collection.isSorted());
		collection.sortCollection();
		assertEquals(true, collection.isSorted());
		
	}
	
	@Test
	public void sortCollectionValid() throws IndexException {
		Record secondRecord = new Record ("The Sound of Music",2);
		collection.addRecord(record);
		collection.addRecord(secondRecord);
		
		ArrayList<Record> records = new ArrayList<Record>();
		records.add(secondRecord);
		records.add(record);
		
		collection.sortCollection();
		assertEquals(secondRecord, collection.findClosestRecord());
		
	}
	@Test
	public void addRecordfindClosestValid() throws IndexException {
		collection.addRecord(record);
		collection.sortCollection();
		assertEquals(record, collection.findClosestRecord());
	}
	

	
	
	@Test(expected = IndexException.class)
	public void addRecordNull() throws IndexException {
		collection.addRecord(null);
	}


	@Test(expected = IndexException.class)
	public void findClosestRecordUnsorted() throws IndexException {
		collection.addRecord(record);
		collection.findClosestRecord();
	}
	
	@Test(expected = IndexException.class)
	public void findClosestRecordEmpty() throws IndexException {
		collection.findClosestRecord();
	}


	@Test
	public void findClosestRecords() throws IndexException {
		Record secondRecord = new Record ("The Sound of Music",2);
		collection.addRecord(record);
		collection.addRecord(secondRecord);

		
		ArrayList<Record> records = new ArrayList<Record>();
		records.add(secondRecord);
		records.add(record);
		
		collection.sortCollection();
		assertEquals(records, collection.findClosestRecords(2));
		
	}
	
	@Test(expected = IndexException.class)
	public void findClosestRecordsUnsorted() throws IndexException {
		collection.addRecord(record);
		collection.findClosestRecords(1);
		
	}
	
	@Test(expected = IndexException.class)
	public void findClosestRecordsSortedInvalidQuery() throws IndexException {
		collection.addRecord(record);
		collection.sortCollection();
		collection.findClosestRecords(2);
	}
	
	@Test(expected = IndexException.class)
	public void findClosestRecordsUnsortedInvalidQuery() throws IndexException {
		collection.addRecord(record);
		collection.findClosestRecords(2);
		
	}

}

