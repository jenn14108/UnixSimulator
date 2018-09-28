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

/**
 * 	This class extends the ModifiedSequentialFilter class and implements the Cd Command
 *  It changes to another directory relative to the current directory
 */
public class CdFilter extends ModifiedSequentialFilter {
	
	private String newDirc; //stores String of new directory
	private String currDirc = SequentialREPL.currentWorkingDirectory;
	
	/**
	 * This is the Constructor of the CdFilter
	 * @param subCommand takes in the command name and the parameter String
	 */
	public CdFilter(String subCommand) {
		this.components = subCommand.split(" ");	
		this.subCommand = subCommand;
		cont = false;
	}
	
	/**
	 * This method processes the information stores in the Cd Filter and 
	 * determines which directory the user would like to change to. The new
	 * directory would be added to the path of the current directory
	 */
	@Override
	public void process() {
		
		if(next != null) { //prints error when there's next filter
			System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(subCommand));
			return; 
		} else if (prev != null) { //prints error when there's a previous filter
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(subCommand));
			return; 
		} else if (components.length < 2) { //prints error when there's no parameter
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(subCommand));
			return; 
		}
		
		newDirc = components[1].trim(); 
		if (newDirc.equals(".")) { //continue when new directory is the current directory
			cont = true;
			return;
		} else if (newDirc.equals("..")) { //goes back to the last directory
			int firstSepr = currDirc.indexOf(Filter.FILE_SEPARATOR); //index of first separator
			int lastSepr = currDirc.lastIndexOf(Filter.FILE_SEPARATOR); //index of last file separator
			
			//checks if we can go up from current directory
			if(!currDirc.equals(Filter.FILE_SEPARATOR)){  
				//sets new current working directory
				SequentialREPL.currentWorkingDirectory = currDirc.substring(0, lastSepr); 
			}
			cont = true;
			return;
		}
		
		currDirc += Filter.FILE_SEPARATOR + newDirc; 
		File dirc = new File(currDirc); //creates new directory from parameter
		
		//checks if the new directory exists
		if (!dirc.isDirectory()) {
			System.out.print(Message.DIRECTORY_NOT_FOUND.with_parameter(subCommand));
			return;
		}
		
		//sets new current working directory
		SequentialREPL.currentWorkingDirectory = dirc.getAbsolutePath();
		cont = true;
	}
	
	@Override
	protected String processLine(String line) {
		return null;
	}

}