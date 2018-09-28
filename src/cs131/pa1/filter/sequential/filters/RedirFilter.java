package cs131.pa1.filter.sequential.filters;

import java.util.Arrays;
import java.util.LinkedList;
import java.io.*;
import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialREPL;

public class RedirFilter extends ModifiedSequentialFilter {
	
	private String newFileName;

	public RedirFilter(String subCommand) {
		components = subCommand.split(" ");
		input = new LinkedList<>();
		cont = false;
	}
	
	@Override
	public void process() {
		
		boolean illegal = false;
		
		if (input == null || input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(components[0] + " " + components[1]));
			return;
		} else if (components.length < 2) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(components[0]));
			return;
		}  else {
			if(components[1].contains("|")) {
				newFileName = components[1].substring(0, components[1].indexOf('|')); 
				illegal = true;
			} else {
				newFileName = components[1];
			}
			File currDir = new File(SequentialREPL.currentWorkingDirectory);
			try {
				FileWriter out = new FileWriter(new File(currDir,newFileName));
				writeToOutPut(out);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(illegal) {
			System.out.print(Message.CANNOT_HAVE_OUTPUT.with_parameter(components[0]+" " +newFileName));
		}
	}
	
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
