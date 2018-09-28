package cs131.pa1.filter.sequential.filters;

import java.io.File;
import java.util.LinkedList;

import cs131.pa1.filter.*;
import cs131.pa1.filter.sequential.*;

/**
 * @author Julia
 * This class extends the ModifiedSequentialFilter class and implements the Ls Command
 * Ls Filter pipes the contents of the current working directory to the output message queue
 */
public class LsFilter extends ModifiedSequentialFilter{
	
	public LsFilter(String subCommand) {
		components = subCommand.split(" "); 
		output = new LinkedList<>();
		cont = false;
	}
	
	/**
	 * This method processes the information stored in the Ls Filter and 
	 * prints out all files and directories in the current working directory
	 */
	@Override
	public void process() {
		
		//prints error if there's a previous Filter
		if(prev != null) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(components[0]));
			return; 
		}
		
		File files = new File(SequentialREPL.currentWorkingDirectory);
		
		//adds all current files/directories into output
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
