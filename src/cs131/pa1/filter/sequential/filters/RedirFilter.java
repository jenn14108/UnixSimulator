package cs131.pa1.filter.sequential.filters;

import java.util.Arrays;
import java.util.LinkedList;
import java.io.*;
import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialREPL;

/**
 * This class extends the ModifiedSequentialFilter class and implements the > Command
 * It reads piped input, and writes it to a file specified after the > symbol
 */
public class RedirFilter extends ModifiedSequentialFilter {
	
	private String newFileName; 
	
	/**
	 * This is the Constructor of the RedirFilter class
	 * @param subCommand takes in the command name and the new file name
	 */
	public RedirFilter(String subCommand) {
		components = subCommand.split(" ");
		input = new LinkedList<>();
		cont = false;
	}
	
	/**
	 * This method processes the information stores in the Redir Filter
	 * It reads piped input and writes it to the file name stored in the subCommand
	 */
	@Override
	public void process() {
		
		boolean illegal = false;//determines whether the parameters contains the | character
		
		//prints error when the input of the Filter is empty
		if (input == null || input.isEmpty()) {
			//This handles the case in which the user types in the command and input without space
			//separation. i.e <hello.txt or <hello.txt|wc
			if (components.length == 1) {
				if (components[0].contains("|")) {
					components[0] = components[0].substring(0, components[0].lastIndexOf('|'));
				}
			System.out.println(Message.REQUIRES_INPUT.with_parameter(components[0]));
			return;
			} else {
				System.out.print(Message.REQUIRES_INPUT.with_parameter(components[0] + " " + components[1]));
				return;
			}
		//prints error if the Filter does not have parameters
		} else if (components.length < 2) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(components[0]));
			return;
		}  else {
			//if the new file name contains "|", gets rid of the string after the "|" character
			if(components[1].contains("|")) {
				newFileName = components[1].substring(0, components[1].indexOf('|')); 
				illegal = true;
			} else {
				newFileName = components[1];
			}
			
			File currDir = new File(SequentialREPL.currentWorkingDirectory);
			try {
				//writes piped input onto a new file
				FileWriter out = new FileWriter(new File(currDir,newFileName));
				writeToOutPut(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//prints error if the command asks the Filter to return an output
		if(illegal) {
			System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(components[0]+" " +newFileName));
		}
	}
	
	/**
	 * This methods writes the piped input onto the file given
	 * @param out FilterWriter object to write to the output file
	 */
	private void writeToOutPut(FileWriter out) throws IOException {
		for(String line: input) {
			out.write(line + "\n");
		}
	}
	
	@Override
	protected String processLine(String line) {
		return null;
	}

}
