package cs131.pa1.filter.sequential.filters;

import java.util.LinkedList;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialFilter;
import cs131.pa1.filter.sequential.SequentialREPL;

public class PwdFilter extends ModifiedSequentialFilter {

	
	public PwdFilter(String subCommand) {
		output = new LinkedList<>();
		this.components = subCommand.split(" ");	
		cont = false;
	}
	
	@Override
	public void process() {
		if(prev != null) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(components[0]));
			return; 
		}
		output.add(SequentialREPL.currentWorkingDirectory);
		cont = true;
	}

	
	@Override
	protected String processLine(String line) {
		return null;
	}

}
