package cs131.pa1.filter.sequential.filters;

import java.util.LinkedList;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.SequentialFilter;
import cs131.pa1.filter.sequential.SequentialREPL;

public class CdFilter extends SequentialFilter {

	private String[] components;
	private String name;
	private String directory;
	
	public CdFilter(String subCommand) {
		this.components = subCommand.split(" ");	
		this.name = components[0];
	}
	
	public void process() {
		if(prev != null) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(name));
			return; 
		} else if (next != null) {
			System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(name));
			return; 
		} else if (components.length < 2) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(name));
			return; 
		}
		directory = components[1];
		if (directory.equals(".")) {
			return;
		} else if (directory.equals("..")) {
			int firstSepr = SequentialREPL.currentWorkingDirectory.indexOf(Filter.FILE_SEPARATOR);
			int lastSepr = SequentialREPL.currentWorkingDirectory.lastIndexOf(Filter.FILE_SEPARATOR);
			
		}
	}
	
	
	@Override
	protected String processLine(String line) {
		return null;
	}

}
