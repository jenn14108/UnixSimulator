package cs131.pa1.filter.sequential.filters;
import java.util.*;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialFilter;


public class GrepFilter extends ModifiedSequentialFilter{
	
	private String searchTerm;
	
	/*
	 * Initializes a new instance of the grep filter with the 
	 * appropriate search term by splitting up the command and parameter
	 */
	public GrepFilter(String commandAndParam) {
		this.subCommand = commandAndParam;
		output = new LinkedList<>();
		input = new LinkedList<>();
		cont = false;
		components = commandAndParam.split(" ");
		//if components = 1 that means that no parameter was given
		if (components.length == 1) {
			this.searchTerm = null;
		} else {
			this.searchTerm = components[1];
		}
	}
	
	@Override 
	/**
	 * This method overrides process() to print out the appropriate 
	 * error message if there is no input. If no error, contineu with process()
	 */
	public void process() {
		if (input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(this.subCommand));
		} else if (this.searchTerm == null) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(this.subCommand));
		} else {
			super.process();
		}
		cont = true;	
	}
	
	/**
	 * processLine checks whether a given line in the file 
	 * contains the searchTerm, if it does, add to output
	 */
	@Override
	protected String processLine(String line) {
		//if input line contains the searchterm - return the line
		if (line.contains(this.searchTerm)) {
			return line;
		}
		return null;
	}

}
