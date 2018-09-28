package cs131.pa1.filter.sequential.filters;

import java.util.LinkedList;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialFilter;

/**
 * This class extends the ModifiedSequentialFilter class and implements the Wc Command
 * It reads lines from piped input and output the number of lines, words and characters, separated with space
 */
public class WcFilter extends ModifiedSequentialFilter{
	private int lines;
	private int words;
	private int characters; 

	/**
	 * This is the Constructor of the WcFilter
	 * @param subCommand takes in the command name String
	 */
	public WcFilter(String subCommand) {
		output = new LinkedList<>();
		input = new LinkedList<>();
		cont = false;
		this.components = subCommand.split(" ");	
		this.lines = 0;
		this.words = 0;
		this.characters = 0;
	}
	
	/**
	 * This method processes the information stored in the Wc Filter, throws errors or 
	 * adds the number of lines, words and characters from input into output
	 */
	@Override
	public void process() {
		
		//checks if the input Queue is empty
		if (input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(this.components[0]));
			return;
		//handles the case in which the file exists, but there is no content
		} else if (input.size()==1 && input.poll().equals("noContent")) {
			output.add("0 0 0");
		} else {
			super.process();
			output.add(this.lines + " " + this.words + " " + this.characters);
		}

		cont = true;
	}
	
	/**
	 * processLine computes the number of lines, words and characters in a passed in String 
	 */
	@Override
	protected String processLine(String line) {
		this.lines++;
		this.words += line.split(" ").length;
		this.characters += line.length();
		return null;
	}

}

