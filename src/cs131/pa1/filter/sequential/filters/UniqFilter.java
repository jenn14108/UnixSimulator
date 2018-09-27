package cs131.pa1.filter.sequential.filters;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.SequentialFilter;
import java.util.*;

public class UniqFilter extends SequentialFilter{
	
	private String name;
	private Boolean isValid;
	TreeSet<String> checker = new TreeSet<>();

	
	/*
	 * Initialize a new instance of the UniqFilter
	 */
	public UniqFilter(String commandAndParam) {
		output = new LinkedList<>();
		input = new LinkedList<>();
		String[] splitCommandAndParam = commandAndParam.split(" ");
		this.name = splitCommandAndParam[0];
		if (splitCommandAndParam.length > 1) {
			this.isValid = false;
		} else {
			this.isValid = true;
		}
	}
	
	@Override
	public void process() {
		if (input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(this.name));
		} else if (!isValid) {
			System.out.print(Message.INVALID_PARAMETER.with_parameter(this.name));
		} else {
			//proceed with the process method
			super.process();
		}
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
