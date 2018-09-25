package cs131.pa1.filter.sequential.filters;


import java.io.File;
import java.util.LinkedList;

import cs131.pa1.filter.*;
import cs131.pa1.filter.sequential.*;
import java.util.LinkedList;

import cs131.pa1.filter.Filter;
import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.SequentialFilter;
import cs131.pa1.filter.sequential.SequentialREPL;


public class CdFilter extends SequentialFilter {

	private String[] components;
	private String name;
	private String newDirc;
	private String currDirc = SequentialREPL.currentWorkingDirectory;

	
	public CdFilter(String subCommand) {
		this.components = subCommand.split(" ");	
		this.name = components[0];
	}
	
	@Override
	public void process() {

		if(next != null) {
			System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(name));
			return; 
		} else if (prev != null) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(name));
			return; 
		} else if (components.length < 2) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(name));
			return; 
		}

		newDirc = components[1];
		if (newDirc.equals(".")) {
			return;
		} else if (newDirc.equals("..")) {
			int firstSepr = currDirc.indexOf(Filter.FILE_SEPARATOR);
			int lastSepr = currDirc.lastIndexOf(Filter.FILE_SEPARATOR);
			
			if(!currDirc.equals(Filter.FILE_SEPARATOR)){
				SequentialREPL.currentWorkingDirectory = currDirc.substring(0, lastSepr); 
			}
			return;
		}
		
		currDirc += Filter.FILE_SEPARATOR + newDirc; 
		File dirc = new File(currDirc);
		
		if (!dirc.isDirectory()) {
			System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(newDirc));
		} else {
			SequentialREPL.currentWorkingDirectory = dirc.getAbsolutePath();
		}
	}
	

	@Override
	protected String processLine(String line) {
		return null;
	}

}