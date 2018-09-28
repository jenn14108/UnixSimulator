package cs131.pa1.filter.sequential.filters;

import java.util.LinkedList;
import java.io.*;
import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialFilter;

public class RedirFilter extends ModifiedSequentialFilter {
	
	private String newFileName;

	public RedirFilter(String subCommand) {
		components = subCommand.split(" ");
		input = new LinkedList<>();
		cont = false;
	}
	
	
	@Override
	public void process() {
		if (input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(components[0]));
			return;
		} else if (components.length < 2) {
			System.out.print(Message.REQUIRES_PARAMETER.with_parameter(components[0]));
			return;
		} else {
			newFileName = components[1];
			try {
				PrintStream out = new PrintStream(new File(newFileName));
				writeToOutPut(out);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void writeToOutPut(PrintStream out) {
		for(String line: input) {
			System.out.println(line);
			out.println(line);
		}
	}
	
	@Override
	protected String processLine(String line) {
		return null;
	}

}
