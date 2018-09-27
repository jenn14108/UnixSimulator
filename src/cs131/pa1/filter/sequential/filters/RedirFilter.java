package cs131.pa1.filter.sequential.filters;

import java.util.LinkedList;
import java.io.*;
import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.SequentialFilter;

public class RedirFilter extends SequentialFilter {
	private String[] components;
	private static String name;
	private String newFileName;

	public RedirFilter(String subCommand) {
		components = subCommand.split(" ");
		input = new LinkedList<>();
		this.name = components[0];
	}
	
	
	@Override
	public void process() {
		if (input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(this.name));
		} else if (components.length < 2) {
			System.out.println(Message.REQUIRES_PARAMETER.with_parameter(this.name));
		} else {
			newFileName = components[1];
			PrintStream out;
			try {
				out = new PrintStream(new File(newFileName));
				writeToOutPut(out);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void writeToOutPut(PrintStream out) {
		for(String line: input) {
			out.println(line);
		}
	}
	
	@Override
	protected String processLine(String line) {
		return null;
	}

}
