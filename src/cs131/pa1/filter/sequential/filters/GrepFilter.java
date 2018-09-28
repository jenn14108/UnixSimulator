package cs131.pa1.filter.sequential.filters;
import java.util.*;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialFilter;

/**
 * This class imitates the Grep command used in a shell 
 * @author Jenn
 */
public class GrepFilter extends ModifiedSequentialFilter{
	
	private String searchTerm;
	
	/*
	 * Initializes a new instance of the grep filter with the 
	 * appropriate search term
	 */
	public GrepFilter(String commandAndParam) {
		this.subCommand = commandAndParam;
		output = new LinkedList<>();
		input = new LinkedList<>();
		cont = false;
		components = commandAndParam.split(" ");
		if (components.length == 1) {
			this.searchTerm = null;
		} else {
			this.searchTerm = components[1];
		}
	}
	
	@Override 
	/**
	 * This method overrides process() to print out the appropriate 
	 * error message if there is no input. 
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
	
	
	@Override
	protected String processLine(String line) {
		//if input line contains the searchterm - return the line
		if (line.contains(this.searchTerm)) {
			return line;
		}
		return null;
	}

}
