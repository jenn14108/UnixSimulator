package cs131.pa1.filter.sequential.filters;

import java.util.LinkedList;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.SequentialFilter;
import cs131.pa1.filter.sequential.SequentialREPL;

public class PwdFilter extends SequentialFilter{
	private String[] components;
	private String name;
	
	public PwdFilter(String subCommand) {
		output = new LinkedList<>();
		this.components = subCommand.split(" ");	
		this.name = components[0];
	}
	
	@Override
	public void process() {
		if(prev != null) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(this.name));
			return; 
		}
		output.add(SequentialREPL.currentWorkingDirectory);
	}

	
	@Override
	protected String processLine(String line) {
		return null;
	}

}
