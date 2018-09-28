package cs131.pa1.filter.sequential.filters;

import java.io.File;
import java.util.LinkedList;

import cs131.pa1.filter.*;
import cs131.pa1.filter.sequential.*;


public class LsFilter extends ModifiedSequentialFilter{
	
	public LsFilter(String subCommand) {
		components = subCommand.split(" "); 
		output = new LinkedList<>();
		cont = false;
	}
	
	
	@Override
	public void process(){
		if(prev != null) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(components[0]));
			return; 
		}
		
		File files = new File(SequentialREPL.currentWorkingDirectory);
		for(String file: files.list()) {
			output.add(file);
		}
		cont = true;
	}

	
	@Override
	protected String processLine(String line) {
		return null;
	}

}
