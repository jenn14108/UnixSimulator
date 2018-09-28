package cs131.pa1.filter.sequential.filters;

import java.util.LinkedList;

import cs131.pa1.filter.Message;
import cs131.pa1.filter.sequential.ModifiedSequentialFilter;
import cs131.pa1.filter.sequential.SequentialFilter;

public class WcFilter extends ModifiedSequentialFilter{
	private int lines;
	private int words;
	private int characters; 

	public WcFilter(String subCommand) {
		output = new LinkedList<>();
		input = new LinkedList<>();
		cont = false;
		this.components = subCommand.split(" ");	
		this.lines = 0;
		this.words = 0;
		this.characters = 0;
	}
	
	@Override
	public void process() {
		if (input.isEmpty()) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter(this.components[0]));
			return;
		} else if (input.size()==1 && input.poll().equals("noContent")) {
			output.add("0 0 0");
		} else {
			super.process();
			output.add(this.lines + " " + this.words + " " + this.characters);
		}

		cont = true;
	}
	
	@Override
	protected String processLine(String line) {
		this.lines++;
		this.words += line.split(" ").length;
		this.characters += line.length();
		return null;
	}

}

