package cs131.pa1.filter.sequential.filters;

import java.util.LinkedList;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialFilter;

public class WcFilter extends ModifiedSequentialFilter{
	private int lines;
	private int words;
	private int characters; 

	/**
	 * This is the constructor for the WcFilter
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
	 * process first checks for any errors. If no error, then continue
	 */
	@Override
	public void process() {
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
	 * processLine computes the number of lines, words,
	 * and characters in a file 
	 */
	@Override
	protected String processLine(String line) {
		this.lines++;
		this.words += line.split(" ").length;
		this.characters += line.length();
		return null;
	}

}

