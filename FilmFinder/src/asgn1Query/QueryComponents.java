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

import static javax.swing.border.TitledBorder.BOTTOM;
import static javax.swing.border.TitledBorder.CENTER;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import asgn1Index.AbstractRecord;
import asgn1Index.IndexException;
import asgn1Index.Record;

/*
 * Class containing GUI components used to run the <code>FilmFinder</code> query system.  
 * 
 * @author hogan
 */
@SuppressWarnings("serial") // We don't care about binary i/o here
public class QueryComponents extends JPanel implements ActionListener {	
	// Places where we'll add components to a frame
	private enum Position {MIDDLELEFT, TOPCENTRE, MIDDLECENTRE, BOTTOMCENTRE}
	
	//We use the FilmFinder object to pass across the collection and index
	private FilmFinder filmFinder;      
	
	// Query Components (Bottom)
	private JTextField queryText;
	private JTextField numResultsText;
	private JButton queryButton;
	private JPanel queryPanel;   
	
	// Display for text results (Middle)
	private JTextArea display; 
	private JScrollPane textScrollPane;
	
	// Margin to allow for the main frame
	private final Integer mainMargin = 20; // pixels;
	
	/**
	 * Create a new Query Window and initialise all of the contained GUI components
	 * @param ff <code>FilmFinder</code> to give collection and index access
	 * @throws QueryException if <code>isNull(ff)</code>
	 */
	public QueryComponents(FilmFinder ff) throws QueryException {
		if (ff==null) {
			throw new QueryException("FilmFinder object is null");
		}
		//Access to collection objects 
		this.filmFinder=ff; 
		// Initialize the GUI Components
		initialiseComponents();
	}

	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent event) {
		
		// Get event's source 
		Object source = event.getSource(); 

		//Consider the alternatives 
		if (source == queryButton) {
			processQuery();
		} 
	}
	
	/**
	 * Convenience method to add a labelled, editable text field to the
	 * chosen panel, with a fixed label and a mutable default text value
	 * 
	 * @param label <code>String</code> text for <code>JLabel</code>
	 * @param defaultValue <code>String</code> default value for <code>JTextField</code>
	 * @param panel <code>JPanel</code> to contain <code>JLabel</code> and <code>JTextField</code>
	 * @return <code>Label</code>led <code>JTextField</code> object placed on the <code>panel</code>
	 */
	private JTextField addParameterPanel(String label, Number defaultValue, JPanel panel) {
		// A parameter panel has two components, a label and a text field
		JPanel parameterPanel = new JPanel();
		JLabel parameterLabel = new JLabel(label);
		JTextField parameterText = new JTextField("" + defaultValue, 3);
		// Add the label to the parameter panel
		parameterLabel.setHorizontalAlignment(JTextField.RIGHT); // flush right
		parameterPanel.add(parameterLabel);
		// Add the text field
		parameterText.setEditable(true);
		parameterText.setHorizontalAlignment(JTextField.RIGHT); // flush right
		parameterPanel.add(parameterText);
		// Add the parameter panel to panel
		//panel.add(parameterPanel, positionConstraints(Position.MIDDLELEFT, mainMargin));
		panel.add(parameterPanel);
		// Return the newly-created text field (but not the label, which never changes)
		return parameterText;
	}

	/**
	 * Convenience method to add a labelled, editable text field to the
	 * chosen panel, with a fixed label and a mutable default text value
	 * 
	 * @param label <code>String</code> text for <code>JLabel</code>
	 * @param defaultValue <code>String</code> default value for <code>JTextField</code>
	 * @param panel <code>JPanel</code> to contain <code>JLabel</code> and <code>JTextField</code>
	 * @return <code>Label</code>led <code>JTextField</code> object placed on the <code>panel</code>
	 */
	private JTextField addQueryField(String label, String defaultValue,JPanel panel) {
		// The panel has two components, a label and a text field
		JPanel queryPanel = new JPanel();
		JLabel queryLabel = new JLabel(label);
		JTextField queryText = new JTextField("" + defaultValue, 40);
		// Add the label to the panel
		queryLabel.setHorizontalAlignment(JTextField.RIGHT); // flush right
		queryPanel.add(queryLabel);
		// Add the text field
		queryText.setEditable(true);
		queryText.setHorizontalAlignment(JTextField.LEFT); // flush left
		queryPanel.add(queryText);
		// Add to the chosen panel 
		//panel.add(queryPanel, positionConstraints(Position.MIDDLECENTRE, mainMargin));
		panel.add(queryPanel);
		// Return the newly-created text field (but not the label or panel )
		return queryText;
	}

	/**
	 * Convenience method for adding text to the display area without
	 * overwriting what's already there
	 * @param newText <code>String</code> containing text to be added 
	 */
	private void appendDisplay(String newText) {
		display.setText(display.getText() + newText);
	}
	
	/**
	 * Convenience method to update a <code>JTextField</code> via <code>appendDisplay</code>
	 * @param text <code>String<code> query text 
	 * @param results <code>List<Record></code> containing query result <code>Record</code>s
	 */
	private void displayResults(String text,List<Record> results) {
		if (results!=null) {
			appendDisplay("<"+text+">:\n"+results.toString()+"\n\n");
		} else {
			appendDisplay("Entry <"+text.trim()+"> not found\n");
		}
	}
	
	/**
	 * Simple method to get primitive int from a text field 
	 * 
	 * @param jtf <code>JTextField</code> containing the value 
	 * @return <code>int</code> value parsed from the field 
	 */
	private int getIntFromTextField(JTextField jtf) {
		int value = Integer.parseInt(jtf.getText());
		return value;
	}

	/*
	 * Helper method to initialise all the GUI components [Overkill control here]
	 */
	private void initialiseComponents() {
		// Choose a grid layout for the main frame
		this.setLayout(new GridBagLayout());

		//MIDDLE LEVEL 
		// Create a scrollable text area for displaying instructions and messages
		display = new JTextArea(Constants.TEXT_ROWS,Constants.TEXT_COLS); // lines by columns
		display.setEditable(false);
		display.setLineWrap(true);
		Font font = new Font("Arial", Font.BOLD,Constants.FONT_SIZE);
        //set font for JTextField
        display.setFont(font);
		textScrollPane = new JScrollPane(display);
		this.add(textScrollPane, positionConstraints(Position.MIDDLECENTRE, mainMargin));
		resetDisplay("Enter the complete film title and press 'Find Films'\n\n");
	
		
		//BOTTOM LEVEL 
		// Panel to contain the query components 
		//Simple flow layout for the bottom panel 
		queryPanel = new JPanel(new FlowLayout());
		queryPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Query Selections",
				CENTER, BOTTOM));
		this.add(queryPanel, positionConstraints(Position.BOTTOMCENTRE, mainMargin));
		queryPanel.setVisible(true);
		
		// Add editable panels
		numResultsText = addParameterPanel("Max Results", Constants.NUM_RESULTS,queryPanel);
		queryText = addQueryField("Enter Title","Casablanca",queryPanel);
		
		// Button for processing the query
		queryButton = new JButton("Find Films");
		queryButton.addActionListener(this);
		queryPanel.add(queryButton);
		this.repaint();
	}

	/**
	 * Convenience method for creating a set of positioning constraints for the
	 * specific layout we want for components of our GUI
	 * 
	 * @param location <code>Position> w.r.t <code>GridBagLayout</code> 
	 * @param margin <code>Integer</code> margin size (pixels)
	 * @return <code>GridBagConstraints</code> object consistent with requirements 
	 */
	private GridBagConstraints positionConstraints(Position location, Integer margin) {
		
		GridBagConstraints constraints = new GridBagConstraints();
		switch (location) {
		case TOPCENTRE:
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.insets = new Insets(margin, margin, 0, margin); // top, left, bottom, right	
			constraints.gridwidth = GridBagConstraints.REMAINDER; // component occupies whole row
			break;
		case MIDDLECENTRE:
			constraints.anchor = GridBagConstraints.CENTER;
			constraints.insets = new Insets(margin, margin, margin, margin); // top, left, bottom, right	
			constraints.gridwidth = GridBagConstraints.REMAINDER; // component occupies whole row
			constraints.weighty = 100; // give extra vertical space to this object
			break;
		case BOTTOMCENTRE:
			constraints.anchor = GridBagConstraints.SOUTH;
			constraints.insets = new Insets(margin, margin, margin, margin); // top, left, bottom, right	
			constraints.gridwidth = GridBagConstraints.REMAINDER; // component occupies whole row
			constraints.weighty = 100; // give extra vertical space to this object
			break;
		case MIDDLELEFT:
			constraints.anchor = GridBagConstraints.WEST;
			constraints.insets = new Insets(0, margin, 0, margin); // top, left, bottom, right	
			constraints.gridwidth = GridBagConstraints.RELATIVE; // component occupies whole row
			constraints.weightx = 100; // give extra horizontal space to this object
			break;
		}
		return constraints;
	}

	/*
	 * Helper method to implement action in response to FindFilm command 
	 */
	private void processQuery() {
		String text = this.queryText.getText(); 
		int max = getIntFromTextField(numResultsText); 
		resetDisplay("Results from Query: <"+text+">\n");
		List<Record> results=null;
		try {
			results = queryIndex(text,max);
		} catch (IndexException e) {
			resetDisplay(e.toString());
			e.printStackTrace();
		}
		displayResults(text,results);
		processSubQuery(results, max);
	}

	/**
	 * Helper method to find the most similar films to titles in a result list  
	 * 
	 * @param results <code>List<Record></code> list of records to use as queries
	 * @param max <code>int</code> containing maximum number of results to be returned 
	 * 
	 */
	private void processSubQuery(List<Record> results, int max) {
		int count=0; 
		if (results!=null) {
			for (AbstractRecord r : results) {
				if (count>0){
					//ignore first record - already dealt with 
					List<Record> res=null;
					try {
						res = queryIndex(r.getTitle(),max);
					} catch (IndexException e) {
						resetDisplay(e.toString());
						e.printStackTrace();
					}
					displayResults(r.getTitle(),res);
				}
				count++; 
			}
		}
	}
	
	/**
	 * Helper method to pass query text to the index 
	 * 
	 * @param text <code>String</code> query to be used 
	 * @param max <code>int</code> containing max number of results to be returned. 
	 * @return <code>List<Record></code> containing of proximal Records in descending match order: <code><r1,r2,...,rn></code> where <code>r1.similarity > 
	 * r2.similarity > ... > rn.similarity</code> and <code>rn > s</code> 
	 * for all other <code>Record s</code>
	 * @throws IndexException propagated from <code>Index.queryListing()</code>
	 */
	private List<Record> queryIndex(String text,int max) throws IndexException {
			return this.filmFinder.getIndex().queryListing(text.trim().toUpperCase(), max);
	}

	/**
	 * Convenience method for resetting the text in the display area
	 * 
	 * @param initialText <code>String</code> text to be used 
	 */
	private void resetDisplay(String initialText) {
		display.setText(initialText);
	}
}