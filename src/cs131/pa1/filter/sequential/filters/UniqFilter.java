package cs131.pa1.filter.sequential.filters;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialFilter;
import java.util.*;

public class UniqFilter extends ModifiedSequentialFilter{
	private String searchTerm;
	private TreeSet<String> checker = new TreeSet<>();
	
	/*
	 * Initialize a new instance of the UniqFilter
	 */
	public UniqFilter(String commandAndParam) {
		output = new LinkedList<>();
		input = new LinkedList<>();
		cont = false;
		components = commandAndParam.split(" ");

		if (components.length > 1) {
			this.searchTerm = null;
		} else {
			this.searchTerm = components[1];
		}
	}
	
	@Override
	public void process() {
		
		if (input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(components[0]));
		} else if (this.searchTerm == null) {
			System.out.print(Message.INVALID_PARAMETER.with_parameter(components[0]));
		} else {
			//proceed with the process method
			super.process();
		}
		cont = true;
	}
	
	@Override
	protected String processLine(String line) {
		if (checker.contains(line)) {
			return null;
		}
		checker.add(line);
		return line;
	}
}
