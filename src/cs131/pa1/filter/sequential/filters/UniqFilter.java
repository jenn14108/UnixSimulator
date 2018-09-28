package cs131.pa1.filter.sequential.filters;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialFilter;
import java.util.*;

/**
 * This class extends the ModifiedSequentialFilter class and implements the Uniq Command
 * it filters out duplicate lines from the input
 */
public class UniqFilter extends ModifiedSequentialFilter{

	private TreeSet<String> checker = new TreeSet<>();
	
	/*
	 * Initialize a new instance of the UniqFilter
	 */
	public UniqFilter(String commandAndParam) {
		output = new LinkedList<>();
		input = new LinkedList<>();
		cont = false;
		components = commandAndParam.split(" ");
	}
	
	/**
	 * Process first checks whether there is input, which Uniq needs
	 * if not, throw error. Otherwise, continue.
	 */
	@Override
	public void process() {
		
		if (input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(components[0]));
		} else {
			//proceed with the process method
			super.process();
		}
		cont = true;
	}
	
	/**
	 * processLine uses a TreeSet to check whether a line 
	 * in a file is unique. If it is, then add to output.
	 */
	@Override
	protected String processLine(String line) {
		if (checker.contains(line)) {
			return null;
		}
		checker.add(line);
		return line;
	}
}
