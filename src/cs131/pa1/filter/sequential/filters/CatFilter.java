package cs131.pa1.filter.sequential.filters;
import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.*;
import java.io.*;

/**
 * This class extends the ModifiedSequentialFilter class and implements the Cat Command
 * Cat outputs the entirety of one or more files to the output message queue
 */
public class CatFilter extends ModifiedSequentialFilter {
	
	private String[] files;
	
	/**
	 * Constructor for the CatFilter. Initializes input and output, 
	 * and split the input (which should contain a command and a parameter)
	 * @param commandAndParam
	 */
	public CatFilter(String commandAndParam) {
		input = new LinkedList<>();
		output = new LinkedList<>();
		subCommand = commandAndParam;
		cont = false;
		contForCat = true;
		//split command and parameter(s)
		String[] splitCommandAndParam = commandAndParam.split(" ");
		//determine number of files to be located and printed out
		files = new String[splitCommandAndParam.length -1];
		//add all files into an array
		for (int i = 1; i < splitCommandAndParam.length; i++) {
			this.files[i-1] = splitCommandAndParam[i];
		}
	}
	
	/**
	 * process handles errors that may be found, and calls the 
	 * writeFileToOutput method to add contents in the files into
	 * the output to be returned in the main method
	 */
	@Override
	public void process() {
		boolean error = false;
		//if there is input, which there shouldn't be, throw error
		if (!input.isEmpty()) {
			System.out.print(Message.CANNOT_HAVE_INPUT.with_parameter(subCommand));
			return;
		//if there are no parameters, throw error
		} else if (files.length == 0) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(subCommand));
			return;
		} else {
			//for each file, see if it exists and add their content into output
			for (String f : files) {
				File file = new File(f);
				if (!file.exists() && !error) {
					System.out.print(Message.FILE_NOT_FOUND.with_parameter(subCommand));
					error = true;
					//prevents more than one error message from being printed if command is piped
					contForCat = false;
				} else {
					writeFileToOutput(file);
				}
			}
		}
		//to handle the case in which file is found but wc
		//has to print out 0 0 0 if has no content
		if (output.size() == 0) {
			output.add("noContent");
		}
		cont = true;
	}
	
	/**
	 * This method uses a Scanner to scan lines in the file 
	 * into the output
	 * @param file
	 */
	public void writeFileToOutput(File file) {
		try {
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				output.add(line);
			}
			fileScanner.close();
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String processLine(String line) {
		return null;
	}

}
