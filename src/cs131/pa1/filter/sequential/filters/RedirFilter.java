package cs131.pa1.filter.sequential.filters;

import java.util.LinkedList;

import cs131.pa1.filter.sequential.SequentialFilter;

public class RedirFilter extends SequentialFilter {
	private String[] components;

	public RedirFilter(String subCommand) {
		components = subCommand.split(" ");
		input = new LinkedList<>();
	}
	
	
	@Override
	public void process() {
		
	}
	
	
	@Override
	protected String processLine(String line) {
		return null;
	}
	
	
	

}
