package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import asgn1Index.Record;


public class RecordTests {
	Record record;
	
	@Before public void initialize(){
		record = new Record("Casablanca", 1);
	}
	
	@Test
	public void initializeTest(){
		record = new Record("Bank Alarm", 4);
	}
	
	@Test
	public void getSimilarity() {
		assertEquals(1, record.getSimilarity());
	}
	
	@Test
	public void getSimilarityNegative(){
		record = new Record("Casablanca", -1 );
		assertEquals(-1, record.getSimilarity());
	}
	
	@Test
	public void getTitle(){
		assertEquals("Casablanca", record.getTitle());
	}
	
	@Test
	public void getTitleNull(){
		record = new Record(null, 1);
		assertEquals(null, record.getTitle());
	}
	
	@Test
	public void compareToFirstGreater(){
		Record secondRecord = new Record("Bank Alarm", 0);
		assertEquals(-1, record.compareTo(secondRecord));
	}
	
	@Test
	public void compareToSecondGreater(){
		Record secondRecord = new Record("Bank Alarm", 2);
		assertEquals(1, record.compareTo(secondRecord));
	}
	
	@Test
	public void compareToBothEqual(){
		Record secondRecord = new Record("Bank Alarm", 1);
		assertEquals(0, record.compareTo(secondRecord));
	}
		
}
