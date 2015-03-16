/**
 * 
 * This file is part of the FilmFinder Project, written as 
 * part of the assessment for CAB302, Semester 1, 2015. 
 *
 * FilmFinder
 * asgn1Query 
 * 15/03/2015
 * 
 */
package asgn1Query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import asgn1Collection.Collection;
import asgn1Collection.ListingException;
import asgn1Collection.MovieCollection;
import asgn1Index.Index;
import asgn1Index.IndexException;
import asgn1Index.Record;

/*
 * This class contains the <code>FilmFinder</code> main program
 * Choose GUI or CLI via the command line arguments
 * 
 * @author hogan
 */
@SuppressWarnings("serial")
public class FilmFinder extends JFrame implements Runnable {
	Collection collection; 
	Index index;
	Log log; 
	
	/**
	 * 
	 * @param c <code>Collection</code> to be processed 
	 * @param i <code>Index</code> derived from <code>c</code> 
	 * @param l <code>Log</code> object to support logging  
	 * @throws QueryException if <code>isNull(c) OR isNull(i) OR isNull(l)</code> 
	 */
	public FilmFinder(Collection c,Index i,Log l) throws QueryException {
		if (c==null) {
			throw new QueryException("Null collection supplied");
		}
		if (i==null) {
			throw new QueryException("Null index supplied");
		}
		if (l==null) {
			throw new QueryException("Null log supplied");
		}
		this.collection=c;
		this.index=i;
		this.log=l;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
	 	// Terminate the program if the user closes the main frame
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Film Finder");
		// Add components to the frame
		try {
			this.getContentPane().add(new QueryComponents(this));
		} catch (QueryException e) {
			//catch can never be reached here; 
			//Exception only if FilmFinder (this) is null 
			e.printStackTrace();
		}
		// Resize the main frame to fit its components
		this.pack();
        // Make the simulation visible
        this.setVisible(true);
	}

	
	/**
	 * 
	 * @return the collection
	 */
	public Collection getCollection() {
		return collection;
	}

	/**
	 * @return the index
	 */
	public Index getIndex() {
		return index;
	}

	/**
	 * @return the log
	 */
	public Log getLog() {
		return log;
	}

	/*
	 * Program entry point. Selects GUI or CLI from command line arg 
	 * options. 
	 * 
	 */
	public static void main(String[] args) {  	
		boolean queryGUI=processArguments(args); 
		Collection mc = new MovieCollection(); 
		Log log=null; 
		
		//Parse and index the collection 
		try {
			Index index = new Index(mc);
			log=new Log(); 
			mc.getCollectionFromFile();
			mc.generateKeyVectors();
			index.createIndex();
			
			//Basic Logging of collection
			//Ignore Movie list here - just summary 
			log.writeCollection(mc,false,false,false);
			if (Constants.KEY_LOG) {
				log.writeKeywords(mc);
				log.finaliseKeyLog();
			}
			log.finaliseLog();
			
			//GUI or not GUI 
			if (queryGUI) {
				SwingUtilities.invokeLater(new FilmFinder(mc,index,log));
			} else {
				//Simple, restricted command line engine - limited to 5 results. 
				System.out.println("Query Engine Available\n");
				BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
				while(true){
					System.out.println("Enter asgn1Query text: ");
				    String text = console.readLine();
				    List<Record> res = index.queryListing(text.trim().toUpperCase(), 5);
					if (res!=null) {
						System.out.println(res.toString());
					} else {
						System.out.println("Entry <"+text.trim()+"> not found\n");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (ListingException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IndexException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (QueryException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Helper method to process command line args
	 * Zero or one argument(s) are permissible; if one, we must select <code>gui</code>
	 * or <code>cli</code>; if zero, we default to <code>Constants.USE_GUI</code>. 
	 * Otherwise, if invalid combination, we exit 
	 * 
	 * @param args <code>String[]</code> command line args from <code>main</code>
	 * @return not reached if <code>!isValid(args)</code>. Otherwise if <code>isValid(args)</code>, 
	 * return <code>true</code> if <code>useGui<code>; otherwise <code>false</code>  
	 */
	private static boolean processArguments(String[] args) {
		switch (args.length) {
			case 0: 
				return Constants.USE_GUI;
			case 1: {
				String option = getValidOption(args);
				if (option==null) {
					usage();
					System.exit(-1);
				} else {
					return option.equalsIgnoreCase("gui");
				}
			}
			default: {
				usage(); 
				System.exit(-1);
				//for the compiler
			}
		}
		//for the compiler
		return false; 
	}
		
	/**
	 * Helper method to detect valid argument for the main program
	 * [Currently a max of one required] 
	 * 
	 * @param args <code>String[]</code> command line args from <code>main</code>
	 * @return <code>String</code> containing <code>"gui" OR "cli"<code>
	 */
	private static String getValidOption(String[] args) {
		String option=args[0].trim();
		boolean valid=(option.equalsIgnoreCase("gui")||option.equalsIgnoreCase("cli"));
		if (valid) {
			return option;
		} else {
			return null;
		}
	}

	/**
	 * Helper method to provide usage message to be invoked on argument error
	 */
	private static void usage() {
		System.err.println("Usage: FilmFinder [gui|cli]\n");
		System.err.println("       Not case sensitive; defaults to setting in Constants.java\n\n");
	}
}

