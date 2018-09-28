package cs131.pa1.filter.sequential.filters;

import java.util.LinkedList;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialFilter;
import cs131.pa1.filter.sequential.SequentialREPL;

/**
 * This class extends the ModifiedSequentialFilter class and implements the Pwd Command
 * Pwd Filter pipes the working directory to the output message queue
 */
public class PwdFilter extends ModifiedSequentialFilter {

	/**
	 * This is the Constructor of the PwdFilter Class
	 * @param subCommand takes in the name of the command 
	 */
	public PwdFilter(String subCommand) {
		output = new LinkedList<>();
		this.components = subCommand.split(" ");	
		cont = false;
	}
	
	/**
	 * This method processes the information stored in the Filter and adds the current working directory
	 * to the output Queue
	 */
	@Override
	public void process() {
		
		//prints error message if the Filter has a previous Filter
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
